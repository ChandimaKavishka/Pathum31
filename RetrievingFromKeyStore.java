import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStore.ProtectionParameter;
import java.security.KeyStore.SecretKeyEntry;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class RetrievingFromKeyStore {
    public static void main(String args[]) throws Exception {
        // Creating the KeyStore object
        KeyStore keyStore = KeyStore.getInstance("JCEKS");

        // Loading the KeyStore object
        char[] password = "changeit".toCharArray();
        FileInputStream fis = new FileInputStream("D:\\Lap\\Campus\\3rd year 1st semester\\NST 31051 Practical for Cryptography\\Lab report\\lab 5\\Key.jceks");
        keyStore.load(fis, password);

        // Creating the KeyStore.ProtectionParameter object
        ProtectionParameter protectionParam = new KeyStore.PasswordProtection(password);

        // Creating SecretKey object
        SecretKey mySecretKey = new SecretKeySpec("myPassword".getBytes(), "DSA");

        // Creating SecretKeyEntry object
        SecretKeyEntry secretKeyEntry = new SecretKeyEntry(mySecretKey);
        keyStore.setEntry("secretKeyAlias", secretKeyEntry, protectionParam);

        // Storing the KeyStore object
        FileOutputStream fos = new FileOutputStream("Key.jceks");
        keyStore.store(fos, password);

        // Retrieving the SecretKeyEntry object from KeyStore
        SecretKeyEntry secretKeyEnt = (SecretKeyEntry) keyStore.getEntry("secretKeyAlias", protectionParam);

        // Creating SecretKey object
        SecretKey mySecretKeyFromStore = secretKeyEnt.getSecretKey();
        System.out.println("Algorithm used to generate key: " + mySecretKeyFromStore.getAlgorithm());
        System.out.println("Format used for the key: " + mySecretKeyFromStore.getFormat());
    }
}
