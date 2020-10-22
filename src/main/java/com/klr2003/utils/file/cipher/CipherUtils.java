package com.klr2003.utils.file.cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CipherUtils {

  private static final String ALGORITHM = "AES";
  private static final String TRANSFORMATION = "AES";

  /**
   * Encrypt files using AES Algorithm
   *
   * @param key The 'secret' key
   * @param inputFile File to encrypt
   * @param outputFile Output
   * @throws CipherException It throws exception if there is a problem in encrypting
   */
  public static void encrypt(String key, File inputFile, File outputFile) throws CipherException {
    doCipher(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
  }

  /**
   * Encrypt files using AES Algorithm
   *
   * @param key The 'secret' key
   * @param inputFile File to decrypt
   * @param outputFile Output
   * @throws CipherException It throws exception if there is a problem in decrypting
   */
  public static void decrypt(String key, File inputFile, File outputFile) throws CipherException {
    doCipher(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
  }

  private static void doCipher(int cipherMode, String key, File inputFile, File outputFile)
      throws CipherException {
    try {
      Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
      Cipher cipher = Cipher.getInstance(TRANSFORMATION);
      cipher.init(cipherMode, secretKey);

      FileInputStream inputStream = new FileInputStream(inputFile);
      byte[] inputBytes = new byte[(int) inputFile.length()];
      inputStream.read(inputBytes);

      byte[] outputBytes = cipher.doFinal(inputBytes);

      FileOutputStream outputStream = new FileOutputStream(outputFile);
      outputStream.write(outputBytes);

      inputStream.close();
      outputStream.close();

    } catch (NoSuchPaddingException
        | NoSuchAlgorithmException
        | InvalidKeyException
        | BadPaddingException
        | IllegalBlockSizeException
        | IOException ex) {
      throw new CipherException("Error encrypting/decrypting file", ex);
    }
  }
}
