package pajeobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    public SelenideElement emailField;
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    public SelenideElement passwordField;
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement entryButton;

    @Step("Ввод email")
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    @Step("Ввод password")
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    @Step("Клик по кнопке ввода")
    public void clickEntryButton() {
        entryButton.scrollTo();
        entryButton.click();
    }

    @Step("Вход")
    public void entry(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickEntryButton();
        entryButton.shouldBe(Condition.hidden);
    }

    @Step("Ожидание после входа в систему")
    public void waitAfterEntry() {
        entryButton.shouldBe(Condition.hidden);
    }
}