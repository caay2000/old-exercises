package com.schibsted.spain.friends.application.security;

import com.schibsted.spain.friends.application.ApplicationException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityProvider {

    public String md5(String value) {
        return value;
//        try {
//            return new String(MessageDigest.getInstance("MD5").digest(value.getBytes()));
//        } catch (NoSuchAlgorithmException e) {
//            throw new ApplicationException("Error creating SecurityProvider instance", e);
//        }
    }
}
