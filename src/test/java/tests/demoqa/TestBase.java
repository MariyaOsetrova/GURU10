package tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import helpers.Attach;


public class TestBase {
    @BeforeAll
    static void setup() {
        // настройки для селенойда
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability("headless", true);

        Configuration.browserCapabilities = capabilities;
       // Configuration.startMaximized = true;
        Configuration.headless=true;

       // chrome_options.add_argument("--headless")
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
    }


    // добавление видео и скриншоты
    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

}
