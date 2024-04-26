package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='cpf']")
    protected WebElement cpf;

    @FindBy(xpath = "//input[@name='senha']")
    protected WebElement senha;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement entrar;
}
