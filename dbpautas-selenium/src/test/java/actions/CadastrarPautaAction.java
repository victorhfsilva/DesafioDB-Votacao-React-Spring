package actions;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;
import pages.CadastrarPautaPage;
import pages.CadastrarPautaPage2;
import pages.LoginPage;

import java.time.Duration;

import static util.DriverFactory.driver;

public class CadastrarPautaAction extends CadastrarPautaPage2 {

    private static final LoginPage loginPage = new LoginPage(driver);

    private static final Faker faker = new Faker();
    private BasePage inicio;

    public void preencherTitulo(String novoTitulo) {
        titulo.sendKeys(novoTitulo);
    }

    public void limparTitulo() {
        titulo.clear();
    }

    public void preencherResumo(String novoResumo) {
        resumo.sendKeys(novoResumo);
    }

    public void limparResumo() {
        resumo.clear();
    }

    public void preencherDescricao(String novoDescricao) {
        descricao.sendKeys(novoDescricao);
    }

    public void limparDescricao() {
        this.descricao.clear();
    }

    public void selecionarCategoria(String categoria) {
        Select dropdown = new Select(this.categoria);
        dropdown.selectByVisibleText(categoria);
    }

    public void limparCategoria() {
        Select dropdown = new Select(this.categoria);
        dropdown.deselectAll();
    }

    public void cadastrar() {
        this.cadastrar.click();
    }

    public void limparCampos() {
        limparTitulo();
        limparResumo();
        limparDescricao();
        limparCategoria();
    }

    public void realizarLogin() {
        inicio = loginPage.navegarPara()
                .preencherCpf("admin")
                .preencherSenha("admin")
                .entrar();

        inicio.esperaUrlSer("http://localhost:5173/", Duration.ofSeconds(10));
    }
}
