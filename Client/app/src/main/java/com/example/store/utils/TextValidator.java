package com.example.store.utils;

import android.text.Editable;

public class TextValidator {

    private static final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static boolean emailValidation(Editable email) {
        return email.toString().matches(emailPattern) && email.length() > 0;
    }

    public static boolean passwordValidation(Editable password) {
        return password.length() > 0;
    }
}
