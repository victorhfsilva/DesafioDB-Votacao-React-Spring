package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//public class CadastrarPautaPageOld extends BasePage {
//    @FindBy(xpath = "//input[@name='titulo']")
//    private WebElement titulo;
//
//    @FindBy(xpath = "//textarea[@name='resumo']")
//    private WebElement resumo;
//
//    @FindBy(xpath = "//textarea[@name='descricao']")
//    private WebElement descricao;
//
//    @FindBy(xpath = "//select[@name='categoria']")
//    private WebElement categoria;
//
//    @FindBy(xpath = "//button[@type='submit']")
//    private WebElement cadastrar;
//
//    public CadastrarPautaPageOld(WebDriver driver) {
//        super(driver);
//        PageFactory.initElements(driver, this);
//    }
//
//    public CadastrarPautaPageOld preencherTitulo(String titulo) {
//        this.titulo.sendKeys(titulo);
//        return this;
//    }
//
//    public CadastrarPautaPageOld limparTitulo() {
//        this.titulo.clear();
//        return this;
//    }
//
//    public CadastrarPautaPageOld preencherResumo(String resumo) {
//        this.resumo.sendKeys(resumo);
//        return this;
//    }
//
//    public CadastrarPautaPageOld limparResumo() {
//        this.resumo.clear();
//        return this;
//    }
//
//    public CadastrarPautaPageOld preencherDescricao(String descricao) {
//        this.descricao.sendKeys(descricao);
//        return this;
//    }
//
//    public CadastrarPautaPageOld limparDescricao() {
//        this.descricao.clear();
//        return this;
//    }
//
//    public CadastrarPautaPageOld selecionarCategoria(String categoria) {
//        Select dropdown = new Select(this.categoria);
//        dropdown.selectByVisibleText(categoria);
//        return this;
//    }
//
//    public CadastrarPautaPageOld limparCategoria() {
//        Select dropdown = new Select(this.categoria);
//        dropdown.deselectAll();
//        return this;
//    }
//
//    public BasePage cadastrar() {
//        this.cadastrar.click();
//        return this;
//    }
//
//    public CadastrarPautaPageOld limparCampos() {
//        limparTitulo();
//        limparResumo();
//        limparDescricao();
//        limparCategoria();
//        return this;
//    }
//
//    public WebElement getTitulo() {
//        return titulo;
//    }
//
//    public WebElement getResumo() {
//        return resumo;
//    }
//
//    public WebElement getDescricao() {
//        return descricao;
//    }
//
//    public WebElement getCategoria() {
//        return categoria;
//    }
//
//    public WebElement getCadastrar() {
//        return cadastrar;
//    }
//}
