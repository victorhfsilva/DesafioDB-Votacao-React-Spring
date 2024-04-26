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

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//class CadastrarPautaTestOld {
//
//    public static final WebDriver driver = new ChromeDriver();
//    private static final LoginPageOld loginPage = new LoginPageOld(driver);
//    private static final CadastrarPautaPageOld cadastrarPautaPage = new CadastrarPautaPageOld(driver);
//    private static final Faker faker = new Faker();
//    private BasePage inicio;
//
//    @BeforeEach
//    void setUp() {
//        inicio = loginPage.navegarPara()
//                .preencherCpf("admin")
//                .preencherSenha("admin")
//                .entrar();
//
//        inicio.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
//    }
//
//    @AfterAll
//    static void tearDown() {
//        driver.quit();
//    }
//
//    @Test
//    @DisplayName("Dado uma pauta válida, quando o usuário cadastrá-la, então o usuário deve ser encaminhado para página AbrirPautas.")
//    void deveCadastrarPautaValidaComSucesso() {
//        inicio.clicar(By.xpath("//button[@data-testid='menu-admin']"));
//        inicio.clicar(By.xpath("//button[@data-testid='menu-cadastrar-pauta']"));
//
//        String categoria = new String("Educação".getBytes(), StandardCharsets.UTF_8);
//
//        BasePage abrirPautas = cadastrarPautaPage.preencherTitulo(faker.lorem().sentence(5))
//                .preencherResumo(faker.lorem().sentence(20))
//                .preencherDescricao(faker.lorem().sentence(60))
//                .selecionarCategoria(categoria)
//                .cadastrar();
//
//        abrirPautas.esperaUrlSer("http://localhost:5173/abrirPauta", Duration.ofSeconds(10));
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals("http://localhost:5173/abrirPauta", urlAtual);
//    }
//
//    @Test
//    @DisplayName("Dado uma pauta com título inválido, quando o usuário cadastrá-la, então o usuário deve receber um erro.")
//    void deveExibirErroCadastrandoPautaComTituloInvalido() {
//        inicio.clicar(By.xpath("//button[@data-testid='menu-admin']"));
//        inicio.clicar(By.xpath("//button[@data-testid='menu-cadastrar-pauta']"));
//
//        String categoria = new String("Educação".getBytes(), StandardCharsets.UTF_8);
//
//        BasePage cadastrarPauta = cadastrarPautaPage.preencherTitulo("")
//                .preencherResumo(faker.lorem().sentence(20))
//                .preencherDescricao(faker.lorem().sentence(60))
//                .selecionarCategoria(categoria)
//                .cadastrar();
//
//        String tituloEsperadoToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
//        String descricaoEsperadaToast = new String("O campo titulo é inválido.".getBytes(), StandardCharsets.UTF_8);
//
//        cadastrarPauta.esperar(Duration.ofSeconds(2));
//
//        WebElement toast = cadastrarPauta.encontrarElemento(By.className("chakra-alert"));
//        WebElement toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        WebElement toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals("http://localhost:5173/cadastrarPauta", urlAtual);
//    }
//
//    @Test
//    @DisplayName("Dado uma pauta com resumo inválido, quando o usuário cadastrá-la, então o usuário deve receber um erro.")
//    void deveExibirErroCadastrandoPautaComResumoInvalido() {
//        inicio.clicar(By.xpath("//button[@data-testid='menu-admin']"));
//        inicio.clicar(By.xpath("//button[@data-testid='menu-cadastrar-pauta']"));
//
//        String categoria = new String("Educação".getBytes(), StandardCharsets.UTF_8);
//
//        BasePage cadastrarPauta = cadastrarPautaPage.preencherTitulo(faker.lorem().sentence(5))
//                .preencherResumo("")
//                .preencherDescricao(faker.lorem().sentence(60))
//                .selecionarCategoria(categoria)
//                .cadastrar();
//
//        String tituloEsperadoToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
//        String descricaoEsperadaToast = new String("O campo resumo é inválido.".getBytes(), StandardCharsets.UTF_8);
//
//        cadastrarPauta.esperar(Duration.ofSeconds(2));
//
//        WebElement toast = cadastrarPauta.encontrarElemento(By.className("chakra-alert"));
//        WebElement toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        WebElement toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals("http://localhost:5173/cadastrarPauta", urlAtual);
//    }
//
//    @Test
//    @DisplayName("Dado uma pauta com descrição inválido, quando o usuário cadastrá-la, então o usuário deve receber um erro.")
//    void deveExibirErroCadastrandoPautaComDescricaoInvalida() {
//        inicio.clicar(By.xpath("//button[@data-testid='menu-admin']"));
//        inicio.clicar(By.xpath("//button[@data-testid='menu-cadastrar-pauta']"));
//
//        String categoria = new String("Educação".getBytes(), StandardCharsets.UTF_8);
//
//        BasePage cadastrarPauta = cadastrarPautaPage.preencherTitulo(faker.lorem().sentence(5))
//                .preencherResumo(faker.lorem().sentence(20))
//                .preencherDescricao("")
//                .selecionarCategoria(categoria)
//                .cadastrar();
//
//        String tituloEsperadoToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
//        String descricaoEsperadaToast = new String("O campo descricao é inválido.".getBytes(), StandardCharsets.UTF_8);
//
//        cadastrarPauta.esperar(Duration.ofSeconds(2));
//
//        WebElement toast = cadastrarPauta.encontrarElemento(By.className("chakra-alert"));
//        WebElement toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        WebElement toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals("http://localhost:5173/cadastrarPauta", urlAtual);
//    }
//}
