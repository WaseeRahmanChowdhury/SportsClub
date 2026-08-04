package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.util.regex.Pattern;

// Package-private: only meant for reuse by this package's own controllers, not exposed outside it.
class ValidationUtils {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^01\\d{9}$");

    static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    static boolean isValidPhone(String phoneNumber) {
        return phoneNumber != null && PHONE_PATTERN.matcher(phoneNumber).matches();
    }
}
