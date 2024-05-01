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
import java.util.List;
import static org.hamcrest.Matchers.equalTo;

public class AbrirPautaTest {
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
    void deveAbrirPautaFechadaComSucesso(){
        PautaRequisicao pauta = PautaFixture.builderValido();
        request.body(pauta)
                .post("/pautas")
                .then().statusCode(201);

        Response response = request.get("/pautas/fechadas/");
        Assertions.assertEquals(200, response.getStatusCode());
        List<PautaEmAndamentoResposta> pautas = response.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        request.patch("/pautas/" + pautas.get(pautas.size()-1).getId()+"/status")
                .then().statusCode(200)
                .and().assertThat().body(equalTo("true"));
    }

    @Test
    void naoDeveAbrirPautaJaAberta(){
        PautaRequisicao pauta = PautaFixture.builderValido();
        request.body(pauta)
                .post("/pautas")
                .then().statusCode(201);

        Response pautasFechadasResponse = request.get("/pautas/fechadas/");
        Assertions.assertEquals(200, pautasFechadasResponse.getStatusCode());
        List<PautaEmAndamentoResposta> pautasFechadas = pautasFechadasResponse.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        request.patch("/pautas/" + pautasFechadas.get(pautasFechadas.size()-1).getId()+"/status")
                .then().statusCode(200);

        Response pautasAbertasResponse = request.get("/pautas/abertas/");
        Assertions.assertEquals(200, pautasAbertasResponse.getStatusCode());
        List<PautaEmAndamentoResposta> pautasAbertas = pautasAbertasResponse.jsonPath()
                .getList(".", PautaEmAndamentoResposta.class);

        request.patch("/pautas/" + pautasAbertas.get(pautasAbertas.size()-1).getId()+"/status")
                .then().statusCode(409);
    }
}
