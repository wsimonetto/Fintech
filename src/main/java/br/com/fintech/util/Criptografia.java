package br.com.fintech.util;

import java.security.MessageDigest;

public class Criptografia {

	public static String criptografar(String senha) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(senha.getBytes("UTF-8"));
        byte[] digest = md.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

} // FIM
