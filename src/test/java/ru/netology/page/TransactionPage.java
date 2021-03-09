package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {
    private SelenideElement amountField = $("[data-test-id = 'amount'] .input__control");
    private SelenideElement sourceCardField = $("[data-test-id = 'from'] .input__control");
    private SelenideElement addButton = $("[data-test-id = 'action-transfer'] .button__text");
    private SelenideElement cancelButton = $("[data-test-id = 'action-cancel'] .button__text");

    public void transaction(String value, String source) {
        amountField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, value.replace(" ", ""));
        sourceCardField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, source.replace(" ", ""));

        addButton.click();
    }
}
