package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.RegistrarUsuarioPage;

import static util.DriverFactory.driver;

public class RegistrarUsuarioAction extends RegistrarUsuarioPage {

    public RegistrarUsuarioAction preencherNome(String nome) {
        this.nome.sendKeys(nome);
        return this;
    }

    public RegistrarUsuarioAction limparNome() {
        this.nome.clear();
        return this;
    }

    public RegistrarUsuarioAction preencherSobrenome(String sobrenome) {
        this.sobrenome.sendKeys(sobrenome);
        return this;
    }

    public RegistrarUsuarioAction limparSobrenome() {
        this.sobrenome.clear();
        return this;
    }

    public RegistrarUsuarioAction preencherEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public RegistrarUsuarioAction limparEmail() {
        this.email.clear();
        return this;
    }

    public RegistrarUsuarioAction preencherCpf(String cpf) {
        this.cpf.sendKeys(cpf);
        return this;
    }

    public RegistrarUsuarioAction limparCpf() {
        this.cpf.clear();
        return this;
    }

    public RegistrarUsuarioAction preencherSenha(String senha) {
        this.senha.sendKeys(senha);
        return this;
    }

    public RegistrarUsuarioAction limparSenha() {
        this.senha.clear();
        return this;
    }

    public RegistrarUsuarioAction selecionarPapel(String papel) {
        Select dropdown = new Select(this.papel);
        dropdown.selectByVisibleText(papel);
        return this;
    }

    public RegistrarUsuarioAction limparPapel() {
        Select dropdown = new Select(this.papel);
        dropdown.deselectAll();
        return this;
    }

    public RegistrarUsuarioAction limparCampos() {
        limparNome();
        limparSobrenome();
        limparEmail();
        limparCpf();
        limparSenha();
        limparPapel();
        return this;
    }

    public void registrar() {
        this.registrar.click();
    }

    public void clicarMenuAdmin() {
        this.menuAdmin.click();
    }

    public void clicarSubMenuRegistrarUsuario() {
        this.subMenuRegistrarUsuario.click();
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
