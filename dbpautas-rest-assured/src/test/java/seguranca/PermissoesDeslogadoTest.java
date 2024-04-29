package seguranca;

import org.example.fixture.usuario.AdminFixture;
import org.example.fixture.usuario.UsuarioFixture;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.example.model.usuario.UsuarioRequisicao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PermissoesDeslogadoTest {

    public static RequestSpecification request;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/";
    }

    @BeforeEach
    void setRequest() {
        request = RestAssured.given().contentType("application/json; charset=UTF-8");
    }

    @Test
    @DisplayName("Dado um usuário comum, quando o usuário tenta cadastrar um usuário, então o sistema deve retornar status 403.")
    void deveRetornarStatus403QuandoUsuarioTentaCadastrarAdmin(){
        UsuarioRequisicao admin = AdminFixture.builderValido();
        request.body(admin)
                .post("/usuario/registrar")
                .then().statusCode(403);
    }

    @Test
    @DisplayName("Dado um usuário comum, quando o usuário tentar registrar uma pauta, então o sistema deve retornar status 403.")
    void deveRetornarStatus403QuandoUsuarioTentaRegistrarPauta(){
        UsuarioRequisicao usuario = UsuarioFixture.builderValido();
        request.body(usuario)
                .post("/usuario/registrar")
                .then().statusCode(403);
    }

    @Test
    @DisplayName("Dado um usuário comun, quando o usuário tenta obter as pautas abertas, então o sistema deve retornar com status 200.")
    void deveRetornarStatus403QuandoUsuarioTentaObterPautasAbertas(){
        request.get("/pautas/abertas/")
                .then().statusCode(403);
    }

    @Test
    @DisplayName("Dado um usuário comun, quando o usuário tenta obter as pautas fechadas, então o sistema deve retornar com status 200.")
    void deveRetornarStatus403QuandoUsuarioTentaObterPautasFechadas(){
        request.get("/pautas/fechadas/")
                .then().statusCode(403);
    }

    @Test
    @DisplayName("Dado um usuário comun, quando o usuário tenta obter as pautas finalizadas, então o sistema deve retornar com status 200.")
    void deveRetornarStatus403QuandoUsuarioTentaObterPautasFinalizadas(){
        request.get("/pautas/finalizadas/")
                .then().statusCode(403);
    }

}
