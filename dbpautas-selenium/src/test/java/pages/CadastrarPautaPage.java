package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static util.DriverFactory.driver;

public class CadastrarPautaPage {

    @FindBy(xpath = "//input[@name='titulo']")
    protected WebElement titulo;

    @FindBy(xpath = "//textarea[@name='resumo']")
    protected WebElement resumo;

    @FindBy(xpath = "//textarea[@name='descricao']")
    protected WebElement descricao;

    @FindBy(xpath = "//select[@name='categoria']")
    protected WebElement categoria;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement cadastrar;

    public CadastrarPautaPage(){
    }


}
