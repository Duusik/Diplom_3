import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;


public class Browser {

    ChromeDriver driver;
    WebDriverWait wait;

    public Browser(String browser) {
        if (browser.equals("Yandex")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");


        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        setWebDriver(driver);

    }

    @Step("Закрытие браузера")
    public void tearDown() {
        driver.quit();
    }

}