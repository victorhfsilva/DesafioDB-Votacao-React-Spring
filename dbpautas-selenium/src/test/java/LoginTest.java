import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.LoginPage;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest {

    public static final WebDriver driver = new ChromeDriver();
    private static final LoginPage loginPage = new LoginPage(driver);

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Dado um login válido, quando o usuário tenta logar, então o usuário deve ser encaminhado para página Home.")
    void deveLogarComSucesso() {
        BasePage inicio = loginPage.navegarPara()
                .preencherCpf("admin")
                .preencherSenha("admin")
                .clicarEntrar();

        inicio.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
        String urlAtual = inicio.gettUrlAtual();
        assertEquals(inicio.getUrlInicio(), urlAtual);
    }

    @Test
    @DisplayName("Dado um login com CPF não cadastrado no banco de dados, quando o usuário tenta logar, o usuário deve receber um alerta de recurso não encontrado.")
    void deveLogarComCpfNaoCadastrado() {
        BasePage inicio = loginPage.navegarPara()
                .preencherCpf("12345678901")
                .preencherSenha("senha")
                .clicarEntrar();

        String tituloEsperadoToast = new String("Recurso não encontrado".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaToast = new String("O recurso solicitado não foi encontrado.".getBytes(), StandardCharsets.UTF_8);

        inicio.esperar(Duration.ofSeconds(2));

        WebElement toast = inicio.encontrarElemento(By.className("chakra-alert"));
        WebElement toastTitulo = toast.findElement(By.className("chakra-alert__title"));
        WebElement toastDescricao = toast.findElement(By.className("chakra-alert__desc"));

        assertTrue(toast.isDisplayed());
        assertEquals(tituloEsperadoToast, toastTitulo.getText());
        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
    }

    @Test
    @DisplayName("Dado um login com CPF não cadastrado no banco de dados, quando o usuário tenta logar, o usuário deve receber um alerta de recurso não encontrado.")
    void deveLogarComSenhaErrada() {
        BasePage inicio = loginPage.navegarPara()
                .preencherCpf("admin")
                .preencherSenha("senha errada")
                .clicarEntrar();

        String tituloEsperadoToast = new String("Falha de autenticação".getBytes(), StandardCharsets.UTF_8);
        String descricaoEsperadaToast = new String("Por favor, faça login novamente.".getBytes(), StandardCharsets.UTF_8);

        inicio.esperar(Duration.ofSeconds(2));

        WebElement toast = inicio.encontrarElemento(By.className("chakra-alert"));
        WebElement toastTitulo = toast.findElement(By.className("chakra-alert__title"));
        WebElement toastDescricao = toast.findElement(By.className("chakra-alert__desc"));

        assertTrue(toast.isDisplayed());
        assertEquals(tituloEsperadoToast, toastTitulo.getText());
        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
    }
}
