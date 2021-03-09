package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.data.DataHelper.cardNumber;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    //private SelenideElement fillFirstCardBtn = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    //private SelenideElement fillSecondCardBtn = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(int cardNumber) {
        val text = cards.get(cardNumber).text();

        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransactionPage transferMoney(int destCardNumber) {
        cards.get(destCardNumber).$("button").click();
        return new TransactionPage();
    }

    public void equalizeBalance() {
        int firstCardBalance = getCardBalance(0);
        int secondCardBalance = getCardBalance(1);
        int inequlity = firstCardBalance - secondCardBalance;
        int balancedSum = inequlity/2;
        if (inequlity > 0) {
            transferMoney(1).transaction(Integer.toString(balancedSum), cardNumber(0));
        }

        if (inequlity < 0) {
            inequlity = inequlity * (-1);
            transferMoney(0).transaction(Integer.toString(balancedSum), cardNumber(1));
        }

    }

}