package szwedo.bookhubapp.secure;

import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESCipher {
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    private static SecretKeySpec getKey(String myKey) {
        MessageDigest sha = null;
        try {
            byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            return new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            Log.d("SECURE", "Error while creating key: " + e.toString());
        }
        return null;
    }

    public static String encrypt(String information, String secretKey) {
        try {
            @SuppressLint("GetInstance") Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getKey(secretKey));
            return Base64.encodeToString(cipher.doFinal(information.getBytes()), Base64.DEFAULT);
        } catch (Exception e) {
            Log.d("SECURE",  "Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String information, String secretKey) {
        try {
            @SuppressLint("GetInstance") Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getKey(secretKey));
            return new String(cipher.doFinal(Base64.decode(information, Base64.NO_PADDING)));
        } catch (Exception e) {
            Log.d("SECURE", "Error while decrypting: " + e.toString());
        }
        return null;
    }
}
