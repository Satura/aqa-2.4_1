package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static String cardNumber (int cardNo){
        String[] cardsNumbers = new String[2];
        cardsNumbers[0] = "5559 0000 0000 0001";
        cardsNumbers[1] = "5559 0000 0000 0002";

        return cardsNumbers[cardNo];
    }

}