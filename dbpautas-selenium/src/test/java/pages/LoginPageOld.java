package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


//public class LoginPageOld extends BasePage {
//
//    @FindBy(xpath = "//input[@name='cpf']")
//    private WebElement cpf;
//
//    @FindBy(xpath = "//input[@name='senha']")
//    private WebElement senha;
//
//    @FindBy(xpath = "//button[@type='submit']")
//    private WebElement entrar;
//
//    public LoginPageOld(WebDriver driver) {
//        super(driver);
//        PageFactory.initElements(driver, this);
//    }
//
//    public LoginPageOld navegarPara() {
//        driver.get(super.getUrlInicio() + "login");
//        return this;
//    }
//
//    public LoginPageOld preencherCpf(String cpf) {
//        this.cpf.sendKeys(cpf);
//        return this;
//    }
//
//    public LoginPageOld limparCpf() {
//        this.cpf.clear();
//        return this;
//    }
//
//    public LoginPageOld preencherSenha(String senha) {
//        this.senha.sendKeys(senha);
//        return this;
//    }
//
//    public LoginPageOld limparSenha() {
//        this.senha.clear();
//        return this;
//    }
//
//    public LoginPageOld limparCampos() {
//        limparCpf();
//        limparSenha();
//        return this;
//    }
//
//    public BasePage entrar() {
//        this.entrar.click();
//        return this;
//    }
//
//    public WebElement getCpf() {
//        return cpf;
//    }
//
//    public WebElement getSenha() {
//        return senha;
//    }
//
//    public WebElement getEntrar() {
//        return entrar;
//    }
//}
