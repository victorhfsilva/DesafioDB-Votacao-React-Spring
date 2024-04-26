package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static util.DriverFactory.driver;

public class CadastrarPautaPage extends BasePage {

    @FindBy(name = "titulo")
    protected WebElement titulo;

    @FindBy(name = "resumo")
    protected WebElement resumo;

    @FindBy(name = "descricao")
    protected WebElement descricao;

    @FindBy(name = "categoria")
    protected WebElement categoria;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement cadastrar;

    @FindBy(xpath = "//button[@data-testid='menu-admin']")
    protected WebElement menuAdmin;

    @FindBy(xpath = "//button[@data-testid='menu-cadastrar-pauta']")
    protected WebElement subMenuCadastrarPauta;
}
