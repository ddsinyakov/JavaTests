package step.learning.services.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1HashService implements HashService {

    @Override
    public String hash(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update((data.getBytes()));
            byte[] hashCode = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hashCode) {
                sb.append(Integer.toHexString(b & 0xFF));
            }

            return sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
