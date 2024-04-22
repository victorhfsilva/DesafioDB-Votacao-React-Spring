package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrarUsuarioPage extends BasePage{

    @FindBy(xpath = "//input[@name='nome']")
    private WebElement nome;

    @FindBy(xpath = "//input[@name='sobrenome']")
    private WebElement sobrenome;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@name='cpf']")
    private WebElement cpf;

    @FindBy(xpath = "//input[@name='senha']")
    private WebElement senha;

    @FindBy(xpath = "//select[@name='papel']")
    private WebElement papel;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement registrar;

    public RegistrarUsuarioPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public RegistrarUsuarioPage preencherNome(String nome) {
        this.nome.sendKeys(nome);
        return this;
    }

    public RegistrarUsuarioPage limparNome() {
        this.nome.clear();
        return this;
    }

    public RegistrarUsuarioPage preencherSobrenome(String sobrenome) {
        this.sobrenome.sendKeys(sobrenome);
        return this;
    }

    public  RegistrarUsuarioPage limparSobrenome() {
        this.sobrenome.clear();
        return this;
    }

    public RegistrarUsuarioPage preencherEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public RegistrarUsuarioPage limparEmail() {
        this.email.clear();
        return this;
    }

    public RegistrarUsuarioPage preencherCpf(String cpf) {
        this.cpf.sendKeys(cpf);
        return this;
    }

    public RegistrarUsuarioPage limparCpf() {
        this.cpf.clear();
        return this;
    }

    public RegistrarUsuarioPage preencherSenha(String senha) {
        this.senha.sendKeys(senha);
        return this;
    }

    public RegistrarUsuarioPage limparSenha() {
        this.senha.clear();
        return this;
    }

    public RegistrarUsuarioPage selecionarPapel(String papel) {
        Select dropdown = new Select(this.papel);
        dropdown.selectByVisibleText(papel);
        return this;
    }

    public RegistrarUsuarioPage limparPapel() {
        Select dropdown = new Select(this.papel);
        dropdown.deselectAll();
        return this;
    }

    public RegistrarUsuarioPage limparCampos() {
        limparNome();
        limparSobrenome();
        limparEmail();
        limparCpf();
        limparSenha();
        limparPapel();
        return this;
    }

    public BasePage clicarRegistrar() {
        this.registrar.click();
        return this;
    }

}
