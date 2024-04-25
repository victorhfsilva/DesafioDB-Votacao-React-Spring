package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PautasFinalizadasPage extends BasePage {

    @FindBy(className = "pauta")
    List<WebElement> pautas;

    public PautasFinalizadasPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getPautas() {
        return pautas;
    }
}
