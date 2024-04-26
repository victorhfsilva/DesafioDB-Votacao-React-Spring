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
import util.GeradorCpf;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//public class RegistrarUsuarioTestOld {
//
//    public static final WebDriver driver = new ChromeDriver();
//    private static final LoginPageOld loginPage = new LoginPageOld(driver);
//    private static final RegistrarUsuarioPageOld registrarUsuarioPage = new RegistrarUsuarioPageOld(driver);
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
//    @DisplayName("Dado uma usuário válido, quando cadastrado, então deve ser possível fazer login com este usuário.")
//    void deveCadastrarUsuarioValidoComSucesso() {
//        inicio.clicar(By.xpath("//button[@data-testid='menu-admin']"));
//        inicio.clicar(By.xpath("//button[@data-testid='menu-registrar-usuario']"));
//
//        String papel = new String("Usuário".getBytes(), StandardCharsets.UTF_8);
//        String nome = faker.name().firstName();
//        String sobrenome = faker.name().lastName();
//        String email = faker.internet().emailAddress();
//        String cpf = GeradorCpf.cpf();
//        String senha = "Senha@123";
//
//        BasePage inicio = registrarUsuarioPage.preencherNome(nome)
//                .preencherSobrenome(sobrenome)
//                .preencherEmail(email)
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .selecionarPapel(papel)
//                .registrar();
//
//        inicio.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals(inicio.getUrlInicio(), urlAtual);
//
//        BasePage novoInicio = loginPage.navegarPara()
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .entrar();
//
//        novoInicio.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
//        urlAtual = inicio.gettUrlAtual();
//        assertEquals(inicio.getUrlInicio(), urlAtual);
//    }
//
//    @Test
//    @DisplayName("Dado uma admin válido, quando cadastrado, então deve ser possível fazer login com este usuário.")
//    void deveCadastrarAdminValidoComSucesso() {
//        inicio.clicar(By.xpath("//button[@data-testid='menu-admin']"));
//        inicio.clicar(By.xpath("//button[@data-testid='menu-registrar-usuario']"));
//
//        String papel = new String("Administrador".getBytes(), StandardCharsets.UTF_8);
//        String nome = faker.name().firstName();
//        String sobrenome = faker.name().lastName();
//        String email = faker.internet().emailAddress();
//        String cpf = GeradorCpf.cpf();
//        String senha = "Senha@123";
//
//        BasePage inicio = registrarUsuarioPage.preencherNome(nome)
//                .preencherSobrenome(sobrenome)
//                .preencherEmail(email)
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .selecionarPapel(papel)
//                .registrar();
//
//        inicio.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals(inicio.getUrlInicio(), urlAtual);
//
//        BasePage novoInicio = loginPage.navegarPara()
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .entrar();
//
//        novoInicio.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
//        urlAtual = inicio.gettUrlAtual();
//        assertEquals(inicio.getUrlInicio(), urlAtual);
//    }
//
//    @Test
//    @DisplayName("Dado uma admin com nome inválido, quando cadastrado, então deve ser exibido erro.")
//    void deveExibirErroAoCadastrarAdminComNomeInvalido() {
//        inicio.clicar(By.xpath("//button[@data-testid='menu-admin']"));
//        inicio.clicar(By.xpath("//button[@data-testid='menu-registrar-usuario']"));
//
//        String papel = new String("Administrador".getBytes(), StandardCharsets.UTF_8);
//        String nome = faker.name().firstName();
//        String sobrenome = faker.name().lastName();
//        String email = faker.internet().emailAddress();
//        String cpf = GeradorCpf.cpf();
//        String senha = "Senha@123";
//
//        BasePage registarUsuario = registrarUsuarioPage.preencherNome("")
//                .preencherSobrenome(sobrenome)
//                .preencherEmail(email)
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .selecionarPapel(papel)
//                .registrar();
//
//        String tituloEsperadoToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
//        String descricaoEsperadaToast = new String("O campo nome é inválido.".getBytes(), StandardCharsets.UTF_8);
//
//        registarUsuario.esperar(Duration.ofSeconds(2));
//
//        WebElement toast = registarUsuario.encontrarElemento(By.className("chakra-alert"));
//        WebElement toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        WebElement toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals("http://localhost:5173/registrarUsuario", urlAtual);
//
//        tituloEsperadoToast = new String("Recurso não encontrado".getBytes(), StandardCharsets.UTF_8);
//        descricaoEsperadaToast = new String("O recurso solicitado não foi encontrado.".getBytes(), StandardCharsets.UTF_8);
//
//
//        BasePage novoInicio = loginPage.navegarPara()
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .entrar();
//
//        novoInicio.esperar(Duration.ofSeconds(2));
//
//        toast = novoInicio.encontrarElemento(By.className("chakra-alert"));
//        toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//    }
//
//    @Test
//    @DisplayName("Dado uma admin com sobrenome inválido, quando cadastrado, então deve ser exibido erro.")
//    void deveExibirErroAoCadastrarAdminComSobrenomeInvalido() {
//        inicio.clicar(By.xpath("//button[@data-testid='menu-admin']"));
//        inicio.clicar(By.xpath("//button[@data-testid='menu-registrar-usuario']"));
//
//        String papel = new String("Administrador".getBytes(), StandardCharsets.UTF_8);
//        String nome = faker.name().firstName();
//        String sobrenome = faker.name().lastName();
//        String email = faker.internet().emailAddress();
//        String cpf = GeradorCpf.cpf();
//        String senha = "Senha@123";
//
//        BasePage registarUsuario = registrarUsuarioPage.preencherNome(nome)
//                .preencherSobrenome("")
//                .preencherEmail(email)
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .selecionarPapel(papel)
//                .registrar();
//
//        String tituloEsperadoToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
//        String descricaoEsperadaToast = new String("O campo sobrenome é inválido.".getBytes(), StandardCharsets.UTF_8);
//
//        registarUsuario.esperar(Duration.ofSeconds(2));
//
//        WebElement toast = registarUsuario.encontrarElemento(By.className("chakra-alert"));
//        WebElement toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        WebElement toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals("http://localhost:5173/registrarUsuario", urlAtual);
//
//        tituloEsperadoToast = new String("Recurso não encontrado".getBytes(), StandardCharsets.UTF_8);
//        descricaoEsperadaToast = new String("O recurso solicitado não foi encontrado.".getBytes(), StandardCharsets.UTF_8);
//
//
//        BasePage novoInicio = loginPage.navegarPara()
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .entrar();
//
//        novoInicio.esperar(Duration.ofSeconds(2));
//
//        toast = novoInicio.encontrarElemento(By.className("chakra-alert"));
//        toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//    }
//
//    @Test
//    @DisplayName("Dado uma admin com sobrenome inválido, quando cadastrado, então deve ser exibido erro.")
//    void deveExibirErroAoCadastrarAdminComCpfInvalido() {
//        inicio.clicar(By.xpath("//button[@data-testid='menu-admin']"));
//        inicio.clicar(By.xpath("//button[@data-testid='menu-registrar-usuario']"));
//
//        String papel = new String("Administrador".getBytes(), StandardCharsets.UTF_8);
//        String nome = faker.name().firstName();
//        String sobrenome = faker.name().lastName();
//        String email = faker.internet().emailAddress();
//        String cpf = "12345678901";
//        String senha = "Senha@123";
//
//        BasePage registarUsuario = registrarUsuarioPage.preencherNome(nome)
//                .preencherSobrenome(sobrenome)
//                .preencherEmail(email)
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .selecionarPapel(papel)
//                .registrar();
//
//        String tituloEsperadoToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
//        String descricaoEsperadaToast = new String("O campo cpf é inválido.".getBytes(), StandardCharsets.UTF_8);
//
//        registarUsuario.esperar(Duration.ofSeconds(2));
//
//        WebElement toast = registarUsuario.encontrarElemento(By.className("chakra-alert"));
//        WebElement toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        WebElement toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals("http://localhost:5173/registrarUsuario", urlAtual);
//
//        tituloEsperadoToast = new String("Recurso não encontrado".getBytes(), StandardCharsets.UTF_8);
//        descricaoEsperadaToast = new String("O recurso solicitado não foi encontrado.".getBytes(), StandardCharsets.UTF_8);
//
//
//        BasePage novoInicio = loginPage.navegarPara()
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .entrar();
//
//        novoInicio.esperar(Duration.ofSeconds(2));
//
//        toast = novoInicio.encontrarElemento(By.className("chakra-alert"));
//        toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//    }
//
//    @Test
//    @DisplayName("Dado uma admin com sobrenome inválido, quando cadastrado, então deve ser exibido erro.")
//    void deveExibirErroAoCadastrarAdminComSenhaFraca() {
//        inicio.clicar(By.xpath("//button[@data-testid='menu-admin']"));
//        inicio.clicar(By.xpath("//button[@data-testid='menu-registrar-usuario']"));
//
//        String papel = new String("Administrador".getBytes(), StandardCharsets.UTF_8);
//        String nome = faker.name().firstName();
//        String sobrenome = faker.name().lastName();
//        String email = faker.internet().emailAddress();
//        String cpf = GeradorCpf.cpf();
//        String senha = "senha";
//
//        BasePage registarUsuario = registrarUsuarioPage.preencherNome(nome)
//                .preencherSobrenome(sobrenome)
//                .preencherEmail(email)
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .selecionarPapel(papel)
//                .registrar();
//
//        String tituloEsperadoToast = new String("Requisição Inválida".getBytes(), StandardCharsets.UTF_8);
//        String descricaoEsperadaToast = new String("O campo senha é inválido.".getBytes(), StandardCharsets.UTF_8);
//
//        registarUsuario.esperar(Duration.ofSeconds(2));
//
//        WebElement toast = registarUsuario.encontrarElemento(By.className("chakra-alert"));
//        WebElement toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        WebElement toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//
//        String urlAtual = inicio.gettUrlAtual();
//        assertEquals("http://localhost:5173/registrarUsuario", urlAtual);
//
//        tituloEsperadoToast = new String("Recurso não encontrado".getBytes(), StandardCharsets.UTF_8);
//        descricaoEsperadaToast = new String("O recurso solicitado não foi encontrado.".getBytes(), StandardCharsets.UTF_8);
//
//
//        BasePage novoInicio = loginPage.navegarPara()
//                .preencherCpf(cpf)
//                .preencherSenha(senha)
//                .entrar();
//
//        novoInicio.esperar(Duration.ofSeconds(2));
//
//        toast = novoInicio.encontrarElemento(By.className("chakra-alert"));
//        toastTitulo = toast.findElement(By.className("chakra-alert__title"));
//        toastDescricao = toast.findElement(By.className("chakra-alert__desc"));
//
//        assertTrue(toast.isDisplayed());
//        assertEquals(tituloEsperadoToast, toastTitulo.getText());
//        assertEquals(descricaoEsperadaToast, toastDescricao.getText());
//    }
//}
