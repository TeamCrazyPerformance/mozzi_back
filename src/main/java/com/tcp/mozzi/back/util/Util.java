package com.tcp.mozzi.back.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Util {

    public static String sha256(String arg) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(arg.getBytes());

        return String.format("%040x", new BigInteger(1, md.digest()));
    }
}
