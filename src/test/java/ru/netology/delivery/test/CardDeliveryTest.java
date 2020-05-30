package ru.netology.delivery.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.delivery.data.DataHelper.DeliveryInfo;
import ru.netology.delivery.page.DeliveryPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Configuration.browser;

class CardDeliveryTest {

    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Should plan meeting and replan meeting")
    void shouldPLanMeeting() {
        int daysToAdd = 3;
        val deliveryPage = open("http:\\localhost:9999", DeliveryPage.class);
        val deliveryInfo = DeliveryInfo.getDeliveryInfo(daysToAdd);
        deliveryPage.planMeeting(deliveryInfo);
        deliveryPage.shouldSuccessNotificationVisibleAndHaveText(deliveryInfo);
        Selenide.refresh();
        deliveryPage.rePlanMeeting(deliveryInfo);
        deliveryPage.shouldSuccessNotificationVisibleAndHaveText(deliveryInfo);
    }
}
