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

/**
 * @author Pavel Zakhvatov (zkhwtf@mm.st)
 * @version 1
 */

//Для написания тестов использовал Selenide
public class FirstTest {

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/zkhwtf/Downloads/chromedriver_win32/chromedriver.exe");
        Configuration.browser = "chrome";
        open("https://vscale.io/panel/login/"); //Открывается страница авторизации
    }

    @Test
    public void firstTest() {
        $(byName("email_or_login")).val("login"); //Ввод login
        $(byName("pass")).val("password"); //Ввод password
        $(byCssSelector("[stl='enter_to_panel']")).shouldBe(enabled).click(); //Нажатие кнопки ВОЙТИ
        $(byCssSelector("[stl='create_new_scalet']")).shouldBe(enabled).click(); //Нажатие кнопки СОЗДАТЬ СЕРВЕР
        $(byCssSelector("[stl='btn_create_server']")).shouldBe(enabled).click(); //Нажатие кнопки СОЗДАТЬ СЕРВЕР
        $(byText("ОК. Перейти к cерверам")).shouldBe(enabled).click(); //Нажатие кнопки ОК
        Selenide.sleep(30000);
        /*
        Не всегда происходит обновление статуса сервера без перезагрузки страницы
        Добавил ожидание на установку сервера
        */
        $(byClassName("scalet-in")).shouldBe(enabled).click(); //Нажатие на сервер
        String statusServerWork = $(byClassName("scalet-status")).getText(); //Получение статуса сервера
        Assert.assertTrue(statusServerWork.equals("Запущен")); //Проверка, что статус сервера ЗАПУЩЕН
        $(byText("Удалить")).shouldBe(enabled).click(); //Нажатие кнопки УДАЛИТЬ
        $(byClassName("checkbox_accept_delete")).shouldBe(enabled).click(); //Нажатие на чекбокс для подтверждения
        $(byCssSelector("[stl='captcha_input']")).val($(byClassName("dialog-scalet-name")).getText());
        /*
        Получение имени сервера
        Вставка полученного имени сервера в поле ввода
        */
        $(byCssSelector("[stl='modal_delete_scalet_action']")).shouldBe(enabled).click(); //Нажатие кнопки УДАЛИТЬ
        Selenide.sleep(10000);
        /*
        Не всегда происходит обновление статуса сервера без перезагрузки страницы
        Добавил ожидание на удаление сервера
        */
        $(byClassName("scalet-in")).shouldBe(enabled).click(); //Нажатие на сервер
        String statusServerDel = $(byClassName("scalet-status")).getText(); //Получение статуса сервера
        Assert.assertTrue(statusServerDel.equals("Удалён")); //Проверка, что статус сервера УДАЛЕН
    }

    @AfterClass
    public static void logout() {
        $(byCssSelector("[stl='menu_logout']")).shouldBe(visible).click(); //Нажатие на кнопку ВЫХОД
    }

}