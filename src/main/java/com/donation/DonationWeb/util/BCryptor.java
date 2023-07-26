package com.donation.DonationWeb.util;

import org.mindrot.jbcrypt.BCrypt;

//비밀번호 암호화
public class BCryptor{

    public static String encrypt(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());

    }

    public static boolean isMatch(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);


    }
}
