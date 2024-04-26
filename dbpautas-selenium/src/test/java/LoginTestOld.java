import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//class LoginTestOld {
//
//    public static final WebDriver driver = new ChromeDriver();
//    private static final LoginPageOld loginPage = new LoginPageOld(driver);
//
//    @AfterAll
//    static void tearDown() {
//        driver.quit();
//    }
//
//    @Test
//    @DisplayName("Dado um login válido, quando o usuário tenta logar, então o usuário deve ser encaminhado para página Home.")
//    void deveLogarComSucesso() {
//        BasePage inicio = loginPage.navegarPara()
//                .preencherCpf("admin")
//                .preencherSenha("admin")
//                .entrar();
//
//        inicio.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals(inicio.getUrlInicio(), urlAtual);
//    }
//
//    @Test
//    @DisplayName("Dado um login com CPF não cadastrado no banco de dados, quando o usuário tenta logar, o usuário deve receber um alerta de recurso não encontrado.")
//    void deveExibirErroComCpfNaoCadastrado() {
//        BasePage inicio = loginPage.navegarPara()
//                .preencherCpf("12345678901")
//                .preencherSenha("senha")
//                .entrar();
//
//        String tituloEsperadoToast = new String("Recurso não encontrado".getBytes(), StandardCharsets.UTF_8);
//        String descricaoEsperadaToast = new String("O recurso solicitado não foi encontrado.".getBytes(), StandardCharsets.UTF_8);
//
//        inicio.esperar(Duration.ofSeconds(2));
//
//        WebElement toast = inicio.encontrarElemento(By.className("chakra-alert"));
//        WebElement toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        WebElement toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals("http://localhost:5173/login", urlAtual);
//    }
//
//    @Test
//    @DisplayName("Dado um login com senha inválida, quando o usuário tenta logar, o usuário deve receber um alerta de recurso não encontrado.")
//    void deveExibirErroComSenhaErrada() {
//        BasePage inicio = loginPage.navegarPara()
//                .preencherCpf("admin")
//                .preencherSenha("senha errada")
//                .entrar();
//
//        String tituloEsperadoToast = new String("Falha de autenticação".getBytes(), StandardCharsets.UTF_8);
//        String descricaoEsperadaToast = new String("Por favor, faça login novamente.".getBytes(), StandardCharsets.UTF_8);
//
//        inicio.esperar(Duration.ofSeconds(2));
//
//        WebElement toast = inicio.encontrarElemento(By.className("chakra-alert"));
//        WebElement toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        WebElement toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals("http://localhost:5173/login", urlAtual);
//
//    }
//}
