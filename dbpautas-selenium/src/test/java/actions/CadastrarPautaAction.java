package actions;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;
import pages.CadastrarPautaPage;
import pages.LoginPage;

import java.time.Duration;

import static util.DriverFactory.driver;

public class CadastrarPautaAction extends CadastrarPautaPage {

    public CadastrarPautaAction preencherTitulo(String novoTitulo) {
        titulo.sendKeys(novoTitulo);
        return this;
    }

    public CadastrarPautaAction limparTitulo() {
        titulo.clear();
        return this;
    }

    public CadastrarPautaAction preencherResumo(String resumo) {
        this.resumo.sendKeys(resumo);
        return this;
    }

    public CadastrarPautaAction limparResumo() {
        this.resumo.clear();
        return this;
    }

    public CadastrarPautaAction preencherDescricao(String novoDescricao) {
        descricao.sendKeys(novoDescricao);
        return this;
    }

    public CadastrarPautaAction limparDescricao() {
        this.descricao.clear();
        return this;
    }

    public CadastrarPautaAction selecionarCategoria(String categoria) {
        Select dropdown = new Select(this.categoria);
        dropdown.selectByVisibleText(categoria);
        return this;
    }

    public CadastrarPautaAction limparCategoria() {
        Select dropdown = new Select(this.categoria);
        dropdown.deselectAll();
        return  this;
    }

    public void cadastrar() {
        this.cadastrar.click();
    }

    public CadastrarPautaAction limparCampos() {
        limparTitulo();
        limparResumo();
        limparDescricao();
        limparCategoria();
        return this;
    }

    public void clicarMenuAdmin() {
        this.menuAdmin.click();
    }

    public void clicarSubMenuCadastrarPauta() {
        this.subMenuCadastrarPauta.click();
    }

    public WebElement encontrarToast(){
        return driver.findElement(By.className("chakra-alert"));
    }

    public String obterTituloDoToast(WebElement toast){
        return toast.findElement(By.className("chakra-alert__title")).getText();
    }

    public String obterDescricaoDoToast(WebElement toast){
        return toast.findElement(By.className("chakra-alert__desc")).getText();
    }

}
