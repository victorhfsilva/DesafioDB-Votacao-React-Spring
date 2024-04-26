import actions.CadastrarPautaAction;
import actions.LoginAction;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CadastrarPautaTest extends BaseTest {

    private static final LoginAction loginAction = new LoginAction();
    private static final CadastrarPautaAction cadastrarPautaAction = new CadastrarPautaAction();
    private static final Faker faker = new Faker();

    @BeforeEach
    void setUp() {
       loginAction.ralizarLoginValido();
        loginAction.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Dado uma pauta válida, quando o usuário cadastrá-la, então o usuário deve ser encaminhado para página AbrirPautas.")
    void deveCadastrarPautaValidaComSucesso() {
        cadastrarPautaAction.clicarMenuAdmin();
        cadastrarPautaAction.clicarSubMenuCadastrarPauta();

        String categoria = new String("Educação".getBytes(), StandardCharsets.UTF_8);

        cadastrarPautaAction.preencherTitulo(faker.lorem().sentence(5))
                .preencherResumo(faker.lorem().sentence(20))
                .preencherDescricao(faker.lorem().sentence(60))
                .selecionarCategoria(categoria)
                .cadastrar();

        cadastrarPautaAction.esperaUrlSer("http://localhost:5173/abrirPauta", Duration.ofSeconds(10));
        assertEquals("http://localhost:5173/abrirPauta", cadastrarPautaAction.gettUrlAtual());
    }

    @Test
    @DisplayName("Dado uma pauta com título inválido, quando o usuário cadastrá-la, então o usuário deve receber um erro.")
    void deveExibirErroCadastrandoPautaComTituloInvalido() {
        cadastrarPautaAction.clicarMenuAdmin();
        cadastrarPautaAction.clicarSubMenuCadastrarPauta();

        String categoria = new String("Educação".getBytes(), StandardCharsets.UTF_8);

       cadastrarPautaAction.preencherTitulo("")
                .preencherResumo(faker.lorem().sentence(20))
                .preencherDescricao(faker.lorem().sentence(60))
                .selecionarCategoria(categoria)
                .cadastrar();

        cadastrarPautaAction.esperar(Duration.ofSeconds(2));

        WebElement toast = cadastrarPautaAction.encontrarToast();
        String tituloToast = cadastrarPautaAction.obterTituloDoToast(toast);
        String descricaoToast = cadastrarPautaAction.obterDescricaoDoToast(toast);

        String tituloEsperadoToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaToast = new String("O campo titulo é inválido.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(toast.isDisplayed());
        assertEquals(tituloEsperadoToast, tituloToast);
        assertEquals(descricaoEsperadaToast, descricaoToast);
        assertEquals("http://localhost:5173/cadastrarPauta", cadastrarPautaAction.gettUrlAtual());
    }

    @Test
    @DisplayName("Dado uma pauta com resumo inválido, quando o usuário cadastrá-la, então o usuário deve receber um erro.")
    void deveExibirErroCadastrandoPautaComResumoInvalido() {
        cadastrarPautaAction.clicarMenuAdmin();
        cadastrarPautaAction.clicarSubMenuCadastrarPauta();

        String categoria = new String("Educação".getBytes(), StandardCharsets.UTF_8);

        cadastrarPautaAction.preencherTitulo(faker.lorem().sentence(5))
                .preencherResumo("")
                .preencherDescricao(faker.lorem().sentence(60))
                .selecionarCategoria(categoria)
                .cadastrar();

        cadastrarPautaAction.esperar(Duration.ofSeconds(2));

        WebElement toast = cadastrarPautaAction.encontrarToast();
        String tituloToast = cadastrarPautaAction.obterTituloDoToast(toast);
        String descricaoToast = cadastrarPautaAction.obterDescricaoDoToast(toast);

        String tituloEsperadoToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaToast = new String("O campo resumo é inválido.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(toast.isDisplayed());
        assertEquals(tituloEsperadoToast, tituloToast);
        assertEquals(descricaoEsperadaToast, descricaoToast);
        assertEquals("http://localhost:5173/cadastrarPauta", cadastrarPautaAction.gettUrlAtual());
    }

    @Test
    @DisplayName("Dado uma pauta com descrição inválido, quando o usuário cadastrá-la, então o usuário deve receber um erro.")
    void deveExibirErroCadastrandoPautaComDescricaoInvalida() {
        cadastrarPautaAction.clicarMenuAdmin();
        cadastrarPautaAction.clicarSubMenuCadastrarPauta();

        String categoria = new String("Educação".getBytes(), StandardCharsets.UTF_8);

        cadastrarPautaAction.preencherTitulo(faker.lorem().sentence(5))
                .preencherResumo(faker.lorem().sentence(20))
                .preencherDescricao("")
                .selecionarCategoria(categoria)
                .cadastrar();

        cadastrarPautaAction.esperar(Duration.ofSeconds(2));

        WebElement toast = cadastrarPautaAction.encontrarToast();
        String tituloToast = cadastrarPautaAction.obterTituloDoToast(toast);
        String descricaoToast = cadastrarPautaAction.obterDescricaoDoToast(toast);

        String tituloEsperadoToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaToast = new String("O campo descricao é inválido.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(toast.isDisplayed());
        assertEquals(tituloEsperadoToast, tituloToast);
        assertEquals(descricaoEsperadaToast, descricaoToast);
        assertEquals("http://localhost:5173/cadastrarPauta", cadastrarPautaAction.gettUrlAtual());
    }
}
