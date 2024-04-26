import actions.LoginAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends  BaseTest {

    private static final LoginAction loginAction = new LoginAction();

    @Test
    @DisplayName("Dado um login válido, quando o usuário tenta logar, então o usuário deve ser encaminhado para página Home.")
    void deveLogarComSucesso() {
        loginAction.navegarPara()
                .preencherCpf("admin")
                .preencherSenha("admin")
                .entrar();

        loginAction.esperaUrlSer(loginAction.getUrlInicio(), Duration.ofSeconds(10));
        assertEquals(loginAction.getUrlInicio(), loginAction.gettUrlAtual());
    }

    @Test
    @DisplayName("Dado um login com CPF não cadastrado no banco de dados, quando o usuário tenta logar, o usuário deve receber um alerta de recurso não encontrado.")
    void loginDeveExibirErroComCpfNaoCadastrado() {
        loginAction.navegarPara()
                .preencherCpf("12345678901")
                .preencherSenha("senha")
                .entrar();

        loginAction.esperar(Duration.ofSeconds(2));

        WebElement toast = loginAction.encontrarToast();
        String tituloToast = loginAction.obterTituloDoToast(toast);
        String descricaoToast = loginAction.obterDescricaoDoToast(toast);

        String tituloEsperadoToast = new String("Recurso não encontrado".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaToast = new String("O recurso solicitado não foi encontrado.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(toast.isDisplayed());
        assertEquals(tituloEsperadoToast, tituloToast);
        assertEquals(descricaoEsperadaToast, descricaoToast);
        assertEquals("http://localhost:5173/login", loginAction.gettUrlAtual());
    }

    @Test
    @DisplayName("Dado um login com senha errada, quando o usuário tenta logar, o usuário deve receber um alerta de recurso não encontrado.")
    void loginDeveExibirErroComSenhaErradaCadastrado() {
        loginAction.navegarPara()
                .preencherCpf("admin")
                .preencherSenha("senha errada")
                .entrar();

        loginAction.esperar(Duration.ofSeconds(2));

        WebElement toast = loginAction.encontrarToast();
        String tituloToast = loginAction.obterTituloDoToast(toast);
        String descricaoToast = loginAction.obterDescricaoDoToast(toast);

        String tituloEsperadoToast = new String("Falha de autenticação".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaToast = new String("Por favor, faça login novamente.".getBytes(), StandardCharsets.UTF_8);

        assertTrue(toast.isDisplayed());
        assertEquals(tituloEsperadoToast, tituloToast);
        assertEquals(descricaoEsperadaToast, descricaoToast);
        assertEquals("http://localhost:5173/login", loginAction.gettUrlAtual());
    }
}
