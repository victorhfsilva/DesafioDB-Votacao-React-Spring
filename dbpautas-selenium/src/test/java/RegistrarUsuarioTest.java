import actions.CadastrarPautaAction;
import actions.LoginAction;
import actions.RegistrarUsuarioAction;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import util.GeradorCpf;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrarUsuarioTest {

    private static final LoginAction loginAction = new LoginAction();
    private static final RegistrarUsuarioAction registrarUsuarioAction = new RegistrarUsuarioAction();
    private static final Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        loginAction.ralizarLoginValido();
        loginAction.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Dado uma usuário válido, quando cadastrado, então deve ser possível fazer login com este usuário.")
    void deveCadastrarUsuarioValidoComSucesso() {
        registrarUsuarioAction.clicarMenuAdmin();
        registrarUsuarioAction.clicarSubMenuRegistrarUsuario();

        String papel = new String("Usuário".getBytes(), StandardCharsets.UTF_8);
        String cpf = GeradorCpf.cpf();
        String senha = "Senha@123";

        registrarUsuarioAction.preencherNome(faker.name().firstName())
                .preencherSobrenome(faker.name().lastName())
                .preencherEmail(faker.internet().emailAddress())
                .preencherCpf(cpf)
                .preencherSenha(senha)
                .selecionarPapel(papel)
                .registrar();

        registrarUsuarioAction.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));

        assertEquals(registrarUsuarioAction.getUrlInicio(), registrarUsuarioAction.gettUrlAtual());

        loginAction.realizarLoginUsuario(cpf, senha);

        loginAction.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
        assertEquals(loginAction.getUrlInicio(), loginAction.gettUrlAtual());
    }

    @Test
    @DisplayName("Dado uma admin válido, quando cadastrado, então deve ser possível fazer login com este usuário.")
    void deveCadastrarAdminValidoComSucesso() {
        registrarUsuarioAction.clicarMenuAdmin();
        registrarUsuarioAction.clicarSubMenuRegistrarUsuario();

        String papel = new String("Administrador".getBytes(), StandardCharsets.UTF_8);
        String cpf = GeradorCpf.cpf();
        String senha = "Senha@123";

        registrarUsuarioAction.preencherNome(faker.name().firstName())
                .preencherSobrenome(faker.name().lastName())
                .preencherEmail(faker.internet().emailAddress())
                .preencherCpf(cpf)
                .preencherSenha(senha)
                .selecionarPapel(papel)
                .registrar();

        registrarUsuarioAction.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));

        assertEquals(registrarUsuarioAction.getUrlInicio(), registrarUsuarioAction.gettUrlAtual());

        loginAction.realizarLoginUsuario(cpf, senha);

        loginAction.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
        assertEquals(loginAction.getUrlInicio(), loginAction.gettUrlAtual());
    }

    @Test
    @DisplayName("Dado uma admin com nome inválido, quando cadastrado, então deve ser exibido erro.")
    void deveExibirErroAoCadastrarAdminComNomeInvalido() {
        registrarUsuarioAction.clicarMenuAdmin();
        registrarUsuarioAction.clicarSubMenuRegistrarUsuario();

        String papel = new String("Administrador".getBytes(), StandardCharsets.UTF_8);
        String cpf = GeradorCpf.cpf();
        String senha = "Senha@123";

        registrarUsuarioAction.preencherNome("")
                .preencherSobrenome(faker.name().lastName())
                .preencherEmail(faker.internet().emailAddress())
                .preencherCpf(cpf)
                .preencherSenha(senha)
                .selecionarPapel(papel)
                .registrar();

        registrarUsuarioAction.esperar(Duration.ofSeconds(2));

        WebElement registrarPautaToast = registrarUsuarioAction.encontrarToast();
        String tituloRegistrarPautaToast = registrarUsuarioAction.obterTituloDoToast(registrarPautaToast);
        String descricaoRegistrarPautaToast = registrarUsuarioAction.obterDescricaoDoToast(registrarPautaToast);

        String tituloEsperadoRegistrarPautaToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaRegistrarPautaToast = new String("O campo nome é inválido.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(registrarPautaToast.isDisplayed());
        assertEquals(tituloEsperadoRegistrarPautaToast, tituloRegistrarPautaToast);
        assertEquals(descricaoEsperadaRegistrarPautaToast, descricaoRegistrarPautaToast);
        assertEquals("http://localhost:5173/registrarUsuario", registrarUsuarioAction.gettUrlAtual());

        loginAction.realizarLoginUsuario(cpf, senha);

        loginAction.esperar(Duration.ofSeconds(2));

        WebElement loginToast = loginAction.encontrarToast();
        String tituloLoginToast = loginAction.obterTituloDoToast(loginToast);
        String descricaoLoginToast = loginAction.obterDescricaoDoToast(loginToast);

        String tituloEsperadoLoginToast = new String("Recurso não encontrado".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaLoginToast = new String("O recurso solicitado não foi encontrado.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(loginToast.isDisplayed());
        assertEquals(tituloEsperadoLoginToast, tituloLoginToast);
        assertEquals(descricaoEsperadaLoginToast, descricaoLoginToast);
        assertEquals("http://localhost:5173/login", loginAction.gettUrlAtual());
    }

    @Test
    @DisplayName("Dado uma admin com sobrenome inválido, quando cadastrado, então deve ser exibido erro.")
    void deveExibirErroAoCadastrarAdminComSobrenomeInvalido() {
        registrarUsuarioAction.clicarMenuAdmin();
        registrarUsuarioAction.clicarSubMenuRegistrarUsuario();

        String papel = new String("Administrador".getBytes(), StandardCharsets.UTF_8);
        String cpf = GeradorCpf.cpf();
        String senha = "Senha@123";

        registrarUsuarioAction.preencherNome(faker.name().firstName())
                .preencherSobrenome("")
                .preencherEmail(faker.internet().emailAddress())
                .preencherCpf(cpf)
                .preencherSenha(senha)
                .selecionarPapel(papel)
                .registrar();

        registrarUsuarioAction.esperar(Duration.ofSeconds(2));

        WebElement registrarPautaToast = registrarUsuarioAction.encontrarToast();
        String tituloRegistrarPautaToast = registrarUsuarioAction.obterTituloDoToast(registrarPautaToast);
        String descricaoRegistrarPautaToast = registrarUsuarioAction.obterDescricaoDoToast(registrarPautaToast);

        String tituloEsperadoRegistrarPautaToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaRegistrarPautaToast = new String("O campo sobrenome é inválido.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(registrarPautaToast.isDisplayed());
        assertEquals(tituloEsperadoRegistrarPautaToast, tituloRegistrarPautaToast);
        assertEquals(descricaoEsperadaRegistrarPautaToast, descricaoRegistrarPautaToast);
        assertEquals("http://localhost:5173/registrarUsuario", registrarUsuarioAction.gettUrlAtual());

        loginAction.realizarLoginUsuario(cpf, senha);

        loginAction.esperar(Duration.ofSeconds(2));

        WebElement loginToast = loginAction.encontrarToast();
        String tituloLoginToast = loginAction.obterTituloDoToast(loginToast);
        String descricaoLoginToast = loginAction.obterDescricaoDoToast(loginToast);

        String tituloEsperadoLoginToast = new String("Recurso não encontrado".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaLoginToast = new String("O recurso solicitado não foi encontrado.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(loginToast.isDisplayed());
        assertEquals(tituloEsperadoLoginToast, tituloLoginToast);
        assertEquals(descricaoEsperadaLoginToast, descricaoLoginToast);
        assertEquals("http://localhost:5173/login", loginAction.gettUrlAtual());
    }

    @Test
    @DisplayName("Dado uma admin com cpf inválido, quando cadastrado, então deve ser exibido erro.")
    void deveExibirErroAoCadastrarAdminComCpfInvalido() {
        registrarUsuarioAction.clicarMenuAdmin();
        registrarUsuarioAction.clicarSubMenuRegistrarUsuario();

        String papel = new String("Administrador".getBytes(), StandardCharsets.UTF_8);
        String cpf = "12345678901";
        String senha = "Senha@123";

        registrarUsuarioAction.preencherNome(faker.name().firstName())
                .preencherSobrenome(faker.name().lastName())
                .preencherEmail(faker.internet().emailAddress())
                .preencherCpf(cpf)
                .preencherSenha(senha)
                .selecionarPapel(papel)
                .registrar();

        registrarUsuarioAction.esperar(Duration.ofSeconds(2));

        WebElement registrarPautaToast = registrarUsuarioAction.encontrarToast();
        String tituloRegistrarPautaToast = registrarUsuarioAction.obterTituloDoToast(registrarPautaToast);
        String descricaoRegistrarPautaToast = registrarUsuarioAction.obterDescricaoDoToast(registrarPautaToast);

        String tituloEsperadoRegistrarPautaToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaRegistrarPautaToast = new String("O campo cpf é inválido.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(registrarPautaToast.isDisplayed());
        assertEquals(tituloEsperadoRegistrarPautaToast, tituloRegistrarPautaToast);
        assertEquals(descricaoEsperadaRegistrarPautaToast, descricaoRegistrarPautaToast);
        assertEquals("http://localhost:5173/registrarUsuario", registrarUsuarioAction.gettUrlAtual());

        loginAction.realizarLoginUsuario(cpf, senha);

        loginAction.esperar(Duration.ofSeconds(2));

        WebElement loginToast = loginAction.encontrarToast();
        String tituloLoginToast = loginAction.obterTituloDoToast(loginToast);
        String descricaoLoginToast = loginAction.obterDescricaoDoToast(loginToast);

        String tituloEsperadoLoginToast = new String("Recurso não encontrado".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaLoginToast = new String("O recurso solicitado não foi encontrado.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(loginToast.isDisplayed());
        assertEquals(tituloEsperadoLoginToast, tituloLoginToast);
        assertEquals(descricaoEsperadaLoginToast, descricaoLoginToast);
        assertEquals("http://localhost:5173/login", loginAction.gettUrlAtual());
    }

    @Test
    @DisplayName("Dado uma admin com senha fraca, quando cadastrado, então deve ser exibido erro.")
    void deveExibirErroAoCadastrarAdminComSenhaFraca() {
        registrarUsuarioAction.clicarMenuAdmin();
        registrarUsuarioAction.clicarSubMenuRegistrarUsuario();

        String papel = new String("Administrador".getBytes(), StandardCharsets.UTF_8);
        String cpf = GeradorCpf.cpf();
        String senha = "senhaFraca";

        registrarUsuarioAction.preencherNome(faker.name().firstName())
                .preencherSobrenome(faker.name().lastName())
                .preencherEmail(faker.internet().emailAddress())
                .preencherCpf(cpf)
                .preencherSenha(senha)
                .selecionarPapel(papel)
                .registrar();

        registrarUsuarioAction.esperar(Duration.ofSeconds(2));

        WebElement registrarPautaToast = registrarUsuarioAction.encontrarToast();
        String tituloRegistrarPautaToast = registrarUsuarioAction.obterTituloDoToast(registrarPautaToast);
        String descricaoRegistrarPautaToast = registrarUsuarioAction.obterDescricaoDoToast(registrarPautaToast);

        String tituloEsperadoRegistrarPautaToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaRegistrarPautaToast = new String("O campo senha é inválido.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(registrarPautaToast.isDisplayed());
        assertEquals(tituloEsperadoRegistrarPautaToast, tituloRegistrarPautaToast);
        assertEquals(descricaoEsperadaRegistrarPautaToast, descricaoRegistrarPautaToast);
        assertEquals("http://localhost:5173/registrarUsuario", registrarUsuarioAction.gettUrlAtual());

        loginAction.realizarLoginUsuario(cpf, senha);

        loginAction.esperar(Duration.ofSeconds(2));

        WebElement loginToast = loginAction.encontrarToast();
        String tituloLoginToast = loginAction.obterTituloDoToast(loginToast);
        String descricaoLoginToast = loginAction.obterDescricaoDoToast(loginToast);

        String tituloEsperadoLoginToast = new String("Recurso não encontrado".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaLoginToast = new String("O recurso solicitado não foi encontrado.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(loginToast.isDisplayed());
        assertEquals(tituloEsperadoLoginToast, tituloLoginToast);
        assertEquals(descricaoEsperadaLoginToast, descricaoLoginToast);
        assertEquals("http://localhost:5173/login", loginAction.gettUrlAtual());
    }

}
