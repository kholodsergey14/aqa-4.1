package ru.netology.delivery.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import ru.netology.delivery.data.DataHelper.DeliveryInfo;

import static com.codeborne.selenide.Condition.*;

public class DeliveryPage {
    @FindBy(css = "[data-test-id=city] input.input__control")
    private SelenideElement city;
    @FindBy(css = "[data-test-id=date] .input__control")
    private SelenideElement date;
    @FindBy(css = "[data-test-id=name] input.input__control")
    private SelenideElement name;
    @FindBy(css = "[data-test-id=phone] input.input__control")
    private SelenideElement phone;
    @FindBy(css = "[data-test-id=agreement] span.checkbox__box")
    private SelenideElement agreement;
    @FindBy(css = "[data-test-id=success-notification]")
    private SelenideElement successNotification;
    @FindBy(css = "[data-test-id=success-notification] .notification__content")
    private SelenideElement successNotificationContent;
    @FindBy(css = "button.button")
    private SelenideElement button;
    @FindBy(css = "[data-test-id='replan-notification']")
    private SelenideElement replanNotification;
    @FindBy(css = "[data-test-id='replan-notification'] button.button")
    private SelenideElement replanButton;

    public void shouldSuccessNotificationVisibleAndHaveText(DeliveryInfo deliveryInfo){
        successNotification.waitUntil(visible, 15000);
        successNotificationContent.shouldHave(Condition.matchesText(deliveryInfo.getExpectedResult()));
    }

    public void shouldReplanNotificationVisible(){
        replanNotification.waitUntil(visible, 15000);
    }

    public void planMeeting(DeliveryInfo deliveryInfo){
        city.setValue(deliveryInfo.getCity());
        date.sendKeys(Keys.LEFT_CONTROL + "a" + Keys.BACK_SPACE);
        date.setValue(deliveryInfo.getDate());
        name.setValue(deliveryInfo.getName());
        phone.setValue(deliveryInfo.getPhone());
        agreement.click();
        button.click();
    }

    public void rePlanMeeting(DeliveryInfo deliveryInfo){
        city.setValue(deliveryInfo.getCity());
        date.sendKeys(Keys.LEFT_CONTROL + "a" + Keys.BACK_SPACE);
        date.setValue(deliveryInfo.getDate());
        name.setValue(deliveryInfo.getName());
        phone.setValue(deliveryInfo.getPhone());
        agreement.click();
        button.click();
        shouldReplanNotificationVisible();
        replanButton.click();

    }


}
