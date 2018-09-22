package com.tlimskech.marketplace.core.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class Utils {

    public static String[] fromCommaStringToArray(String s) {
        if (s == null) {
            return new String[] {};
        }
        return Optional.of(s).map(t -> t.split(",")).orElse(new String[] {});
    }

    public static String hash(String principal) {
        return DigestUtils.md5DigestAsHex(principal.getBytes());
    }

    public static String nullSafeString(String str) {
        return StringUtils.isEmpty(str) ? "" : str;
    }
}
