package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AbrirPautasPage {

    @FindBy(xpath = "//input[@name='cpf']")
    List<WebElement> pautas;
}
