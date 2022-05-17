package hcmute.edu.vn.leafnote.service;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomString {
    public static String randomPassword() {
        String input = "abcdefghijkmnplyuqxyzt0123456789";
        return RandomStringUtils.random(8, input);
    }
}
