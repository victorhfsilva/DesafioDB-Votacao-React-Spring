package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrarUsuarioPage extends BasePage {
    @FindBy(name = "nome")
    protected WebElement nome;

    @FindBy(name = "sobrenome")
    protected WebElement sobrenome;

    @FindBy(name = "email")
    protected WebElement email;

    @FindBy(name = "cpf")
    protected WebElement cpf;

    @FindBy(name = "senha")
    protected WebElement senha;

    @FindBy(name = "papel")
    protected WebElement papel;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement registrar;

    @FindBy(xpath = "//button[@data-testid='menu-admin']")
    protected WebElement menuAdmin;

    @FindBy(xpath = "//button[@data-testid='menu-registrar-usuario']")
    protected WebElement subMenuRegistrarUsuario;
}
