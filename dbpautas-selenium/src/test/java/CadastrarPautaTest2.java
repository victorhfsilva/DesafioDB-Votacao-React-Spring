import actions.CadastrarPautaAction;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.CadastrarPautaPage;
import pages.CadastrarPautaPage2;
import pages.LoginPage;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.DriverFactory.driver;

public class CadastrarPautaTest2 {

    private static final CadastrarPautaAction cadastrar = new CadastrarPautaAction();
    private static final LoginPage loginPage = new LoginPage(driver);

    private static final Faker faker = new Faker();
    private BasePage inicio;

    @BeforeEach
    void setUp() {
        cadastrar.realizarLogin();
    }

    @Test
    @DisplayName("Dado uma pauta válida, quando o usuário cadastrá-la, então o usuário deve ser encaminhado para página AbrirPautas.")
    void deveCadastrarPautaValidaComSucesso() {
        inicio.clicar(By.xpath("//button[@data-testid='menu-admin']"));
        inicio.clicar(By.xpath("//button[@data-testid='menu-cadastrar-pauta']"));

        String categoria = new String("Educação".getBytes(), StandardCharsets.UTF_8);

        cadastrar.preencherTitulo(faker.lorem().sentence(5));
        cadastrar.preencherResumo(faker.lorem().sentence(20));
        cadastrar.preencherDescricao(faker.lorem().sentence(60));
        cadastrar.selecionarCategoria(categoria);
        cadastrar.cadastrar();

        inicio.esperaUrlSer("http://localhost:5173/abrirPauta", Duration.ofSeconds(10));
        String urlAtual = inicio.gettUrlAtual();
        assertEquals("http://localhost:5173/abrirPauta", urlAtual);
    }

}
