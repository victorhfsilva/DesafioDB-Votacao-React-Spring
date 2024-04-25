package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='cpf']")
    private WebElement cpf;

    @FindBy(xpath = "//input[@name='senha']")
    private WebElement senha;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement entrar;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage navegarPara() {
        driver.get(super.getUrlInicio() + "login");
        return this;
    }

    public LoginPage preencherCpf(String cpf) {
        this.cpf.sendKeys(cpf);
        return this;
    }

    public LoginPage limparCpf() {
        this.cpf.clear();
        return this;
    }

    public LoginPage preencherSenha(String senha) {
        this.senha.sendKeys(senha);
        return this;
    }

    public LoginPage limparSenha() {
        this.senha.clear();
        return this;
    }

    public LoginPage limparCampos() {
        limparCpf();
        limparSenha();
        return this;
    }

    public BasePage clicarEntrar() {
        this.entrar.click();
        return this;
    }

    public WebElement getCpf() {
        return cpf;
    }

    public WebElement getSenha() {
        return senha;
    }

    public WebElement getEntrar() {
        return entrar;
    }
}
