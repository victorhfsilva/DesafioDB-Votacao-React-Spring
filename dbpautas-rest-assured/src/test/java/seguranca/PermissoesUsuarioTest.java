package seguranca;

import org.example.fixture.usuario.AdminFixture;
import org.example.fixture.usuario.LoginFixture;
import org.example.fixture.usuario.UsuarioFixture;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.model.usuario.LoginRequisicao;
import org.example.model.usuario.UsuarioRequisicao;
import org.junit.jupiter.api.*;

public class PermissoesUsuarioTest {

    public static RequestSpecification request;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/";
    }

    @BeforeEach
    void setRequest() {

        request = RestAssured.given().contentType("application/json; charset=UTF-8");

        LoginRequisicao loginAdmin = LoginFixture.builderValido();
        Response responseAdmin = request.body(loginAdmin).post("/login");
        String tokenAdmin = responseAdmin.jsonPath().getString("token");

        request = request.header("Authorization", "Bearer " + tokenAdmin);

        UsuarioRequisicao usuario = UsuarioFixture.builderValido();
        request.body(usuario)
                .post("/usuario/registrar");

        LoginRequisicao login = LoginFixture.builderDoUsuario(usuario);
        Response response = request.body(login).post("/login");
        String token = response.jsonPath().getString("token");

        request = RestAssured.given().contentType("application/json; charset=UTF-8");
        request = request.header("Authorization", "Bearer " + token);
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
        request.get("/pauta/aberta/")
                .then().statusCode(200);
    }

    @Test
    @DisplayName("Dado um usuário comun, quando o usuário tenta obter as pautas fechadas, então o sistema deve retornar com status 200.")
    void deveRetornarStatus403QuandoUsuarioTentaObterPautasFechadas(){
        request.get("/pauta/fechada/")
                .then().statusCode(403);
    }

    @Test
    @DisplayName("Dado um usuário comun, quando o usuário tenta obter as pautas finalizadas, então o sistema deve retornar com status 200.")
    void deveRetornarStatus403QuandoUsuarioTentaObterPautasFinalizadas(){
        request.get("/pauta/finalizada/")
                .then().statusCode(200);
    }

}
