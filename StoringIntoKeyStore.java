import java.io.FileOutputStream;
import java.security.KeyStore;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class StoringIntoKeyStore {

  public static void main(String args[]) throws Exception {

    // Create a new KeyStore object
    KeyStore keyStore = KeyStore.getInstance("JCEKS");
    keyStore.load(null, null); // No existing keystore, initialize empty

    // Creating the SecretKey object
    SecretKey mySecretKey = new SecretKeySpec("myPassword".getBytes(), "DSA");

    // Creating SecretKeyEntry with password protection
    KeyStore.ProtectionParameter protectionParam = new KeyStore.PasswordProtection("changeit".toCharArray());
    KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(mySecretKey);

    // Custom path for the keystore file
    String keystoreFilePath = "C:\\Users\\Administrator\\Desktop\\labsheet 3.1\\CRYPTO\\5\\Key.jceks"; // Adding a file name

    // Add the secret key entry to the keystore
    keyStore.setEntry("secretKeyAlias", secretKeyEntry, protectionParam);

    // Store the keystore to the custom file path
    FileOutputStream fos = new FileOutputStream(keystoreFilePath);
    keyStore.store(fos, "changeit".toCharArray());

    System.out.println("Secret key stored in " + keystoreFilePath);
  }
}