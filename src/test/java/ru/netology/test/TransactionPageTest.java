package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransactionPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.data.DataHelper.cardNumber;

class TransactionPageTest {
    private DashboardPage dashboard;
    private int amount = 5_200;

    @BeforeEach
    void setUp(){
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboard = verificationPage.validVerify(verificationCode);
        dashboard.equalizeBalance();
    }

    @AfterEach
    void ending(){
        dashboard.equalizeBalance();
    }

    @Test
    void shouldTransferToFirstCard() {
        int expect1 = dashboard.getCardBalance(0) + amount;
        int expect2 = dashboard.getCardBalance(1) - amount;
        TransactionPage transactionPage = dashboard.transferMoney(0);
        transactionPage.transaction(Integer.toString(amount), cardNumber(1));
        int actual1 = dashboard.getCardBalance(0);
        int actual2 = dashboard.getCardBalance(1);
        assertEquals(expect1,actual1);
        assertEquals(expect2,actual2);
    }

    @Test
    void shouldTransferToSecondCard() {
        int expect1 = dashboard.getCardBalance(0) - amount;
        int expect2 = dashboard.getCardBalance(1) + amount;
        TransactionPage transactionPage = dashboard.transferMoney(1);
        transactionPage.transaction(Integer.toString(amount), cardNumber(0));
        int actual1 = dashboard.getCardBalance(0);
        int actual2 = dashboard.getCardBalance(1);
        assertEquals(expect2,actual2);
        assertEquals(expect1,actual1);
    }
}