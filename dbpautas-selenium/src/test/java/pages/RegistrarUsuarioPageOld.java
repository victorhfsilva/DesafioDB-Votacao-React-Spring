package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//public class RegistrarUsuarioPageOld extends BasePage{
//
//    @FindBy(xpath = "//input[@name='nome']")
//    private WebElement nome;
//
//    @FindBy(xpath = "//input[@name='sobrenome']")
//    private WebElement sobrenome;
//
//    @FindBy(xpath = "//input[@name='email']")
//    private WebElement email;
//
//    @FindBy(xpath = "//input[@name='cpf']")
//    private WebElement cpf;
//
//    @FindBy(xpath = "//input[@name='senha']")
//    private WebElement senha;
//
//    @FindBy(xpath = "//select[@name='papel']")
//    private WebElement papel;
//
//    @FindBy(xpath = "//button[@type='submit']")
//    private WebElement registrar;
//
//    public RegistrarUsuarioPageOld(WebDriver driver) {
//        super(driver);
//        PageFactory.initElements(driver, this);
//    }
//
//    public RegistrarUsuarioPageOld preencherNome(String nome) {
//        this.nome.sendKeys(nome);
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld limparNome() {
//        this.nome.clear();
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld preencherSobrenome(String sobrenome) {
//        this.sobrenome.sendKeys(sobrenome);
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld limparSobrenome() {
//        this.sobrenome.clear();
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld preencherEmail(String email) {
//        this.email.sendKeys(email);
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld limparEmail() {
//        this.email.clear();
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld preencherCpf(String cpf) {
//        this.cpf.sendKeys(cpf);
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld limparCpf() {
//        this.cpf.clear();
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld preencherSenha(String senha) {
//        this.senha.sendKeys(senha);
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld limparSenha() {
//        this.senha.clear();
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld selecionarPapel(String papel) {
//        Select dropdown = new Select(this.papel);
//        dropdown.selectByVisibleText(papel);
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld limparPapel() {
//        Select dropdown = new Select(this.papel);
//        dropdown.deselectAll();
//        return this;
//    }
//
//    public RegistrarUsuarioPageOld limparCampos() {
//        limparNome();
//        limparSobrenome();
//        limparEmail();
//        limparCpf();
//        limparSenha();
//        limparPapel();
//        return this;
//    }
//
//    public BasePage registrar() {
//        this.registrar.click();
//        return this;
//    }
//
//    public WebElement getNome() {
//        return nome;
//    }
//
//    public WebElement getSobrenome() {
//        return sobrenome;
//    }
//
//    public WebElement getEmail() {
//        return email;
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
//    public WebElement getPapel() {
//        return papel;
//    }
//
//    public WebElement getRegistrar() {
//        return registrar;
//    }
//}
