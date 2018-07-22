package io.vscale;

import com.codeborne.selenide.*;
import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FirstTest {

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/zkhwtf/Downloads/chromedriver_win32/chromedriver.exe");
        Configuration.browser = "chrome";
        open("https://vscale.io/panel/login/");
    }

    @Test
    public void firstTest() {
        $(byName("email_or_login")).val("login"); //login
        $(byName("pass")).val("password"); //password

        $(byCssSelector("[stl='enter_to_panel']")).shouldBe(enabled).click();

        $(byCssSelector("[stl='create_new_scalet']")).shouldBe(enabled).click();

        $(byCssSelector("[stl='btn_create_server']")).shouldBe(enabled).click();

        $(byText("ОК. Перейти к cерверам")).shouldBe(enabled).click();
        Selenide.sleep(30000);

        $(byClassName("scalet-in")).shouldBe(enabled).click();

        String statusServerWork = $(byClassName("scalet-status")).getText();
        Assert.assertTrue(statusServerWork.equals("Запущен"));

        $(byText("Удалить")).shouldBe(enabled).click();

        $(byClassName("checkbox_accept_delete")).shouldBe(enabled).click();

        $(byCssSelector("[stl='captcha_input']")).val($(byClassName("dialog-scalet-name")).getText());

        $(byCssSelector("[stl='modal_delete_scalet_action']")).shouldBe(enabled).click();
        Selenide.sleep(10000);

        $(byClassName("scalet-in")).shouldBe(enabled).click();

        String statusServerDel = $(byClassName("scalet-status")).getText();
        Assert.assertTrue(statusServerDel.equals("Удалён"));
    }

    @AfterClass
    public static void logout(){
        $(byCssSelector("[stl='menu_logout']")).shouldBe(visible).click();
    }

}