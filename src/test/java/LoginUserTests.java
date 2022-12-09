import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pajeobject.ForgotPasswordPage;
import pajeobject.LoginPage;
import pajeobject.MainPage;
import pajeobject.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginUserTests {
    MainPage main = page(MainPage.class);
    LoginPage login = page(LoginPage.class);
    RegistrationPage register = page(RegistrationPage.class);
    ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);

    Browser browser;

    @Parameterized.Parameter
    public String Browser;

    @Parameterized.Parameters(name = "{0} browser")
    public static Object[][] browserForTest() {
        return new Object[][]{
                {"Chrome"},
                {"Yandex"}
        };
    }

    @Before
    public void setUp() {
        browser = new Browser(Browser);
    }

    @After
    public void cleanUp(){
        browser.tearDown();
    }

    @Test
    @DisplayName("Корректный вход с главной страницы через кнопку Войти в аккаунт")
    public void checkLoginFromMainPageViaEntryButton() {
        open(Constants.MAIN_PAGE_URL);
        main.clickAccountEntryButton();
        login.entry(Constants.BASIC_EMAIL, Constants.BASIC_PASSWORD);
        assertEquals("После успешного входа пользователь должен попасть на главную страницу!",
                Constants.MAIN_PAGE_URL, url());
    }

    @Test
    @DisplayName("Корректный вход с главной страницы через кнопку Личный кабинет")
    public void checkLoginFromMainPageViaPersonalAccountButton() {
        open(Constants.MAIN_PAGE_URL);
        main.clickPersonalAccountButton();
        login.entry(Constants.BASIC_EMAIL, Constants.BASIC_PASSWORD);
        assertEquals("После успешного входа пользователь должен попасть на главную страницу!",
                Constants.MAIN_PAGE_URL, url());
    }

    @Test
    @DisplayName("Корректный вход со страницы регистрации через кнопку Войти")
    public void checkLoginFromRegistrationPageViaPersonalAccountButton() {
        open(Constants.REGISTER_PAGE_URL);
        register.clickTheEntryButton();
        login.entry(Constants.BASIC_EMAIL, Constants.BASIC_PASSWORD);
        assertEquals("После успешного входа пользователь должен попасть на главную страницу!",
                Constants.MAIN_PAGE_URL, url());
    }

    @Test
    @DisplayName("Переход со страницы восстановления пароля через кнопку Войти")
    public void checkLoginFromForgotPasswordPageViaEntryButton() {
        open(Constants.FORGOT_PASSWORD_URL);
        forgotPasswordPage.clickTheEntryButton();
        login.entry(Constants.BASIC_EMAIL, Constants.BASIC_PASSWORD);
        assertEquals("После успешного входа пользователь должен попасть на главную страницу!",
                Constants.MAIN_PAGE_URL, url());
    }

}