import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pajeobject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTests {

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
    @DisplayName("Проверка добавления элемента из раздела Булки в корзину")
    public void checkTransitionOfBunsButton() {
        MainPage main = open(Constants.MAIN_PAGE_URL, MainPage.class);
        assertTrue("После перемещения булка в корзине заказа должна быть видна", main.clickBunsButtonCheckTheSign());
    }

    @Test
    @DisplayName("Проверка добавления элемента из раздела Соусы в корзину")
    public void checkTransitionOfSaucesButton() {
        MainPage main = open(Constants.MAIN_PAGE_URL, MainPage.class);
        assertTrue("После перемещения соус в корзине заказа должен быть виден", main.clickSaucesButtonAndCheckTheSign());
    }

    @Test
    @DisplayName("Проверка добавления элемента из раздела Начинки в корзину")
    public void checkTransitionOfFillingButton() {
        MainPage main = open(Constants.MAIN_PAGE_URL, MainPage.class);
        assertTrue("После перемещения ингредиента должно быть видно наполнение в корзине заказа", main.clickFillingButtonAndCheckTheSign());
    }

}