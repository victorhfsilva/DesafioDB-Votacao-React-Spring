package pauta;

import org.example.fixture.pauta.PautaFixture;
import org.example.fixture.usuario.LoginFixture;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.model.pauta.PautaRequisicao;
import org.example.model.usuario.LoginRequisicao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CadastrarPautaTest {

    public static RequestSpecification request;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/";
    }


    @BeforeEach
    public void setRequest() {
        request = RestAssured.given().contentType("application/json");

        LoginRequisicao login = LoginFixture.builderValido();
        Response response = request.body(login).post("/login");
        String token = response.jsonPath().getString("token");

        request = request.header("Authorization", "Bearer " + token);
    }


    @Test
    @DisplayName("Dado uma pauta válida, quando o usuário tenta cadastrar, então o sistema deve retornar status 201 e true.")
    public void deveCadastrarPautaComSucesso() {
        PautaRequisicao pauta = PautaFixture.builderValido();
        request.body(pauta)
                .post("/pauta/registrar")
                .then().statusCode(201)
                .and().assertThat().body(equalTo("true"));
    }

    @ParameterizedTest
    @MethodSource("pautaBuilders")
    @DisplayName("Dada uma pauta com campo inválido, quando o usuário tenta cadastrar, então o sistema deve retornar status 400.")
    void deveRetornarStatus400QuandoCampoInvalido(PautaRequisicao pauta) {
        request.body(pauta)
                .post("/pauta/registrar")
                .then().statusCode(400);
    }

    public static Stream<Arguments> pautaBuilders() {
        return Stream.of(
                Arguments.of(PautaFixture.builderTituloInvalido()),
                Arguments.of(PautaFixture.builderTituloNulo()),
                Arguments.of(PautaFixture.builderResumoInvalido()),
                Arguments.of(PautaFixture.builderResumoNulo()),
                Arguments.of(PautaFixture.builderDescricaoInvalida()),
                Arguments.of(PautaFixture.builderDescricaoNula()),
                Arguments.of(PautaFixture.builderCategoriaInvalida()),
                Arguments.of(PautaFixture.builderCategoriaNula())
        );
    }
}
