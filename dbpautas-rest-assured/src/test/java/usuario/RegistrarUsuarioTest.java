package usuario;

import org.example.fixture.usuario.AdminFixture;
import org.example.fixture.usuario.LoginFixture;
import org.example.fixture.usuario.UsuarioFixture;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.model.usuario.LoginRequisicao;
import org.example.model.usuario.UsuarioRequisicao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import static org.hamcrest.Matchers.equalTo;

public class RegistrarUsuarioTest {
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
    @DisplayName("Dado um admin válido, quando o usuário tenta cadastrar, então o sistema deve retornar status 201 e true.")
    void deveCadastrarAdminComSucesso(){
        UsuarioRequisicao admin = AdminFixture.builderValido();
        request.body(admin)
                .post("/usuario/registrar")
                .then().statusCode(201)
                .and().assertThat().body(equalTo("true"));
    }

    @Test
    @DisplayName("Dado um admin válido, quando o usuário tenta cadastrar, então o sistema deve retornar status 201 e true.")
    void deveCadastrarUsuarioComSucesso(){
        UsuarioRequisicao usuario = UsuarioFixture.builderValido();
        request.body(usuario)
                .post("/usuario/registrar")
                .then().statusCode(201)
                .and().assertThat().body(equalTo("true"));
    }

    @ParameterizedTest
    @MethodSource("adminBuilders")
    void deveRetornarStatus400QuandoCampoInvalido(UsuarioRequisicao admin, String mensagem) {
        String mensagemEsperada = new String(mensagem.getBytes(), StandardCharsets.UTF_8);
        request.body(admin)
                .post("/usuario/registrar")
                .then().statusCode(400)
                .and().assertThat().body(equalTo(mensagemEsperada));
    }

    public static Stream<Arguments> adminBuilders() {
        return Stream.of(
                Arguments.of(AdminFixture.builderNomeInvalido(), "O campo nome é inválido."),
                Arguments.of(AdminFixture.builderNomeNulo(), "O campo nome é inválido."),
                Arguments.of(AdminFixture.builderSobrenomeInvalido(), "O campo sobrenome é inválido."),
                Arguments.of(AdminFixture.builderSobrenomeNulo(), "O campo sobrenome é inválido."),
                Arguments.of(AdminFixture.builderCpfInvalido(), "O campo cpf é inválido."),
                Arguments.of(AdminFixture.builderCpfNulo(), "O campo cpf é inválido."),
                Arguments.of(AdminFixture.builderEmailInvalido(), "O campo email é inválido."),
                Arguments.of(AdminFixture.builderEmailNulo(), "O campo email é inválido."),
                Arguments.of(AdminFixture.builderSenhaFraca(), "O campo senha é inválido."),
                Arguments.of(AdminFixture.builderSenhaNula(), "O campo senha é inválido."),
                Arguments.of(AdminFixture.builderPapelInvalido(), "Ocorreu um erro ao converter sua mensagem."),
                Arguments.of(AdminFixture.builderPapelNulo(), "O campo papel é inválido."),

                Arguments.of(UsuarioFixture.builderNomeInvalido(), "O campo nome é inválido."),
                Arguments.of(UsuarioFixture.builderNomeNulo(), "O campo nome é inválido."),
                Arguments.of(UsuarioFixture.builderSobrenomeInvalido(), "O campo sobrenome é inválido."),
                Arguments.of(UsuarioFixture.builderSobrenomeNulo(), "O campo sobrenome é inválido."),
                Arguments.of(UsuarioFixture.builderCpfInvalido(), "O campo cpf é inválido."),
                Arguments.of(UsuarioFixture.builderCpfNulo(), "O campo cpf é inválido."),
                Arguments.of(UsuarioFixture.builderEmailInvalido(), "O campo email é inválido."),
                Arguments.of(UsuarioFixture.builderEmailNulo(), "O campo email é inválido."),
                Arguments.of(UsuarioFixture.builderSenhaFraca(), "O campo senha é inválido."),
                Arguments.of(UsuarioFixture.builderSenhaNula(), "O campo senha é inválido.")
        );
    }
}
