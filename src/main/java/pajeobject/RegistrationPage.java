package pajeobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {
    @FindBy(how = How.XPATH, using = ".//input[@class='text input__textfield text_type_main-default']")
    public ElementsCollection nameEmailPasswordFields;
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registryButton;
    @FindBy(how = How.XPATH, using = "//*[@href='/login']")
    private SelenideElement entryButton;
     @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordSign;

    @Step("Ввод имени")
    public void setName(String name) {
        nameEmailPasswordFields.get(0).setValue(name);
    }

    @Step("Ввод почты")
    public void setEmail(String email) {
        nameEmailPasswordFields.get(1).setValue(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        nameEmailPasswordFields.get(2).setValue(password);
    }

    @Step("Клик по кнопке регистрации")
    public void clickRegistrationButton() {
        registryButton.click();
    }

    @Step("Регистрация")
    public void registration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
    }

    @Step("Проверка надписи некорректного пароля")
    public boolean checkIncorrectPasswordSign() {
        return incorrectPasswordSign.isDisplayed();
    }

     @Step("Ожидание после регистрации")
    public void waitAfterRegistration() {
        registryButton.shouldBe(Condition.hidden);
    }

    @Step("Клик по кнопке Войти")
    public void clickTheEntryButton() {
        entryButton.scrollTo().click();
    }
}