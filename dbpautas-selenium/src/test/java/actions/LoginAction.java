package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;

import static util.DriverFactory.driver;

public class LoginAction extends LoginPage {

    public LoginAction navegarPara() {
        driver.get(super.getUrlInicio() + "login");
        return this;
    }

    public LoginAction preencherCpf(String cpf) {
        this.cpf.sendKeys(cpf);
        return this;
    }

    public LoginAction limparCpf() {
        this.cpf.clear();
        return this;
    }

    public LoginAction preencherSenha(String senha) {
        this.senha.sendKeys(senha);
        return this;
    }

    public LoginAction limparSenha() {
        this.senha.clear();
        return this;
    }

    public LoginAction limparCampos() {
        limparCpf();
        limparSenha();
        return this;
    }

    public void entrar() {
        this.entrar.click();
    }

    public void ralizarLoginValido(){
        this.navegarPara()
                .preencherCpf("admin")
                .preencherSenha("admin")
                .entrar();
    }

    public void realizarLoginUsuario(String cpf, String senha){
        this.navegarPara()
                .preencherCpf(cpf)
                .preencherSenha(senha)
                .entrar();
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
