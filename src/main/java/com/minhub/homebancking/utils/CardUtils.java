package com.minhub.homebancking.utils;

public final class CardUtils {
    private CardUtils() {

    }
    public static String getCardNumber() {
        String cardNumber = Math.round(Math.random() * (9999 - 1000) + 1000)
                + "-" + Math.round(Math.random() * (9999 - 1000) + 1000)
                + "-" + Math.round(Math.random() * (9999 - 1000) + 1000)
                + "-" + Math.round(Math.random() * (9999 - 1000) + 1000);
        return cardNumber;
    }
    public static int getCvv() {
        int cvv = (int)(Math.random() * (999 - 100) + 100);
        return cvv;
    }

}
