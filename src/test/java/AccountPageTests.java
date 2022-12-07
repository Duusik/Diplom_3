import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pajeobject.AccountPage;
import pajeobject.LoginPage;
import pajeobject.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class AccountPageTests {

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
        MainPage main = open(Constants.MAIN_PAGE_URL, MainPage.class);
        main.clickAccountEntryButton();
        LoginPage login = page(LoginPage.class);
        login.entry(Constants.BASIC_EMAIL, Constants.BASIC_PASSWORD);
    }

    @After
    public void cleanUp(){
        browser.tearDown();
    }

    @Test
    @DisplayName("Переход на страницу личного кабинета с главной страницы")
    public void checkTheTransitionToThePersonalAccountPage() {
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();
        AccountPage personalPage = page(AccountPage.class);
        personalPage.waitAfterTransition();
        assertEquals("После нажатия на кнопку пользователь должен попасть на страницу личного кабинета!",
                url(), Constants.ACCOUNT_PAGE_URL);
    }

    @Test
    @DisplayName("Переход на главную страницу после нажатия кнопки конструктора")
    public void checkTheTransitionAfterClickConstructorButton() {
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();
        AccountPage personalPage = page(AccountPage.class);
        personalPage.waitAfterTransition();
        personalPage.clickTheConstructorButton();
        assertEquals("После нажатия на кнопку конструктор пользователь должен попасть на главную страницу!",
                url(), Constants.MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход на главную страницу после нажатия на логотип")
    public void checkTheTransitionAfterClickLogo() {
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();
        AccountPage personalPage = page(AccountPage.class);
        personalPage.waitAfterTransition();
        personalPage.clickTheLogo();
        assertEquals("После нажатия на логотип пользователь должен попасть на главную страницу!",
                url(), Constants.MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход на страницу входа после нажатия на кнопку выхода из системы")
    public void checkTheTransitionAfterLogOut() {
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();
        AccountPage personalPage = page(AccountPage.class);
        personalPage.waitAfterTransition();
        personalPage.clickTheLogOutButton();
        personalPage.waitAfterLogOut();
        assertEquals("После нажатия на кнопку выхода пользователь должен попасть на страницу входа!",
                url(), Constants.LOGIN_PAGE_URL);
    }

}