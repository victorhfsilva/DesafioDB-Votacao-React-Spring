package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class BasePage {

    private String home = "http://localhost:5173";

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage abrirPagina(String url){
        driver.get(url);
        return this;
    }

    public BasePage trocarParaJanela(String title){
        Set<String> windowHandles = driver.getWindowHandles();

        String desiredWindowHandle = windowHandles.stream()
                .filter(handle -> driver.switchTo().window(handle).getTitle().contains(title))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Janela desejada não encontrada"));

        driver.switchTo().window(desiredWindowHandle);
        return this;
    }

    public BasePage maximizar(){
        driver.manage().window().maximize();
        return this;
    }

    public BasePage retornar() {
        driver.navigate().back();
        return this;
    }

    public BasePage atualizarPagina() {
        driver.navigate().refresh();
        return this;
    }

    public BasePage esperarPorElementoEstarClicavel(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return this;
    }


    public BasePage esperarPorTituloConter(String title, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.titleContains(title));
        return this;
    }

    public BasePage esperaUrlSer(String url, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.urlToBe(url));
        return this;
    }

    public BasePage esperarPorVisibilidadeDe(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    public BasePage esperar(Duration duration){
        driver.manage().timeouts().implicitlyWait(duration);
        return this;
    }

    public BasePage tirarScreenshot(String pathName) {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(pathName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getHome() {
        return this.home;
    }

}
