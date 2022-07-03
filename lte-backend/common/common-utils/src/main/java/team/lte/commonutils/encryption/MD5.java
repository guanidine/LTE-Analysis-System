package team.lte.commonutils.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String encode(String code) {
        if (code != null) {
            try {
                return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(code.getBytes())).toUpperCase();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder result = new StringBuilder();
        for (byte i : b) {
            result.append(byteToHexString(i));
        }
        return result.toString();
    }

    private static String byteToHexString(byte i) {
        int n = i;
        if (n < 0) {
            n += 256;
        }
        if (n < 16) {
            return "0" + Integer.toHexString(n);
        } else {
            return Integer.toHexString(n);
        }
    }

}
