package com.block.food.util;

import java.util.regex.Pattern;

public class NicknameValidator {

    public static boolean isValidInput(String input) {
        String regex = "^[가-힣a-zA-Z0-9]{2,20}$";
        return Pattern.matches(regex, input);
    }
}
