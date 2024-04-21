package pauta;

import org.example.fixture.pauta.PautaFixture;
import org.example.fixture.usuario.LoginFixture;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.model.pauta.PautaEmAndamentoResposta;
import org.example.model.pauta.PautaRequisicao;
import org.example.model.usuario.LoginRequisicao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class VotarPautaTest {

    public static RequestSpecification request;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/";
    }

    @BeforeEach
    void setRequest() {
        request = RestAssured.given().contentType("application/json; charset=UTF-8");

        LoginRequisicao login = LoginFixture.builderValido();
        Response response = request.body(login).post("/login");
        String token = response.jsonPath().getString("token");

        request = request.header("Authorization", "Bearer " + token);
    }

    @Test
    void naoDeveVotarPautaFechada(){
        PautaRequisicao pauta = PautaFixture.builderValido();
        request.body(pauta)
                .post("/pauta/registrar")
                .then().statusCode(201);

        Response response = request.get("/pauta/fechada/");
        Assertions.assertEquals(200, response.getStatusCode());
        List<PautaEmAndamentoResposta> pautas = response.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        request.queryParam("voto", "SIM")
                .patch("/pauta/votar/" + pautas.get(pautas.size()-1).getId())
                .then().statusCode(409);
    }

    @Test
    void deveVotarEmPautaAbertaComSucesso(){
        PautaRequisicao pauta = PautaFixture.builderValido();
        request.body(pauta)
                .post("/pauta/registrar")
                .then().statusCode(201);

        Response pautasFechadasResponse = request.get("/pauta/fechada/");
        Assertions.assertEquals(200, pautasFechadasResponse.getStatusCode());
        List<PautaEmAndamentoResposta> pautasFechadas = pautasFechadasResponse.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        request.patch("/pauta/abrir/" + pautasFechadas.get(pautasFechadas.size()-1).getId())
                .then().statusCode(200);

        Response pautasAbertasResponse = request.get("/pauta/aberta/");
        Assertions.assertEquals(200, pautasAbertasResponse.getStatusCode());
        List<PautaEmAndamentoResposta> pautasAbertas = pautasAbertasResponse.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        request.queryParam("voto", "SIM")
                .patch("/pauta/votar/" + pautasAbertas.get(pautasAbertas.size()-1).getId())
                .then().statusCode(200)
                .and().assertThat().body(equalTo("true"));
    }

    @Test
    void naoDeveVotarEmPautaDuasVezes() throws InterruptedException {
        PautaRequisicao pauta = PautaFixture.builderValido();
        request.body(pauta)
                .post("/pauta/registrar")
                .then().statusCode(201);

        Response pautasFechadasResponse = request.get("/pauta/fechada/");
        Assertions.assertEquals(200, pautasFechadasResponse.getStatusCode());
        List<PautaEmAndamentoResposta> pautasFechadas = pautasFechadasResponse.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        request.patch("/pauta/abrir/" + pautasFechadas.get(pautasFechadas.size()-1).getId())
                .then().statusCode(200);

        Response pautasAbertasResponse = request.get("/pauta/aberta/");
        Assertions.assertEquals(200, pautasAbertasResponse.getStatusCode());
        List<PautaEmAndamentoResposta> pautasAbertas = pautasAbertasResponse.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        request.queryParam("voto", "SIM")
                .patch("/pauta/votar/" + pautasAbertas.get(pautasAbertas.size()-1).getId())
                .then().statusCode(200);

        String mensagemEsperada = new String("Você já votou nesta pauta.".getBytes(), StandardCharsets.UTF_8);

        request.queryParam("voto", "SIM")
                .patch("/pauta/votar/" + pautasAbertas.get(pautasAbertas.size()-1).getId())
                .then().statusCode(400)
                .and().assertThat().body(equalTo(mensagemEsperada));
    }

    @Test
    void naoDeveVotarEmPautaComVotoInvalido(){
        PautaRequisicao pauta = PautaFixture.builderValido();
        request.body(pauta)
                .post("/pauta/registrar")
                .then().statusCode(201);

        Response pautasFechadasResponse = request.get("/pauta/fechada/");
        Assertions.assertEquals(200, pautasFechadasResponse.getStatusCode());
        List<PautaEmAndamentoResposta> pautasFechadas = pautasFechadasResponse.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        request.patch("/pauta/abrir/" + pautasFechadas.get(pautasFechadas.size()-1).getId())
                .then().statusCode(200);

        Response pautasAbertasResponse = request.get("/pauta/aberta/");
        Assertions.assertEquals(200, pautasAbertasResponse.getStatusCode());
        List<PautaEmAndamentoResposta> pautasAbertas = pautasAbertasResponse.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        String mensagemEsperada = new String("Esperava-se uma entrada com tipo diferente.".getBytes(), StandardCharsets.UTF_8);

        request.queryParam("voto", "VOTO_INVALIDO")
                .patch("/pauta/votar/" + pautasAbertas.get(pautasAbertas.size()-1).getId())
                .then().statusCode(400)
                .and().assertThat().body(equalTo(mensagemEsperada));
    }

    @Test
    void naoDeveVotarEmPautaComVotoNulo(){
        PautaRequisicao pauta = PautaFixture.builderValido();
        request.body(pauta)
                .post("/pauta/registrar")
                .then().statusCode(201);

        Response pautasFechadasResponse = request.get("/pauta/fechada/");
        Assertions.assertEquals(200, pautasFechadasResponse.getStatusCode());
        List<PautaEmAndamentoResposta> pautasFechadas = pautasFechadasResponse.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        request.patch("/pauta/abrir/" + pautasFechadas.get(pautasFechadas.size()-1).getId())
                .then().statusCode(200);

        Response pautasAbertasResponse = request.get("/pauta/aberta/");
        Assertions.assertEquals(200, pautasAbertasResponse.getStatusCode());
        List<PautaEmAndamentoResposta> pautasAbertas = pautasAbertasResponse.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        String mensagemEsperada = new String("Parâmetro ausente.".getBytes(), StandardCharsets.UTF_8);

        request.patch("/pauta/votar/" + pautasAbertas.get(pautasAbertas.size()-1).getId())
                .then().statusCode(400)
                .and().assertThat().body(equalTo(mensagemEsperada));
    }

}
