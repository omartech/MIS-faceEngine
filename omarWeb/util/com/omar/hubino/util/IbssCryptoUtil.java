package com.omar.hubino.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * The Class IbssCryptoUtil.
 * 
 * @author Vijayaraja Gnansambandan
 * @version 1.0.0 - The Class IbssCryptoUtil Created
 * 
 */

public class IbssCryptoUtil {

	private static String secretString = "";

	private static final String ENCRYPTION_ALGORITHM = "AES";

	private static final String CHARACTERENCODING = "ASCII";

	private static final BASE64Encoder BASE64ENCODER = new BASE64Encoder();

	private static final BASE64Decoder BASE64DECODER = new BASE64Decoder();

	private static Cipher g_encryptCipher;

	private static Cipher g_decryptCipher;

	private static IbssCryptoUtil instance = null;

	public static synchronized IbssCryptoUtil getInstance(String securityString) {
		IbssCryptoUtil.secretString = securityString;
		if (instance == null) {
			instance = new IbssCryptoUtil();
		}
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(
					IbssCryptoUtil.secretString.getBytes(),
					ENCRYPTION_ALGORITHM);

			g_encryptCipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
			g_encryptCipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			g_decryptCipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
			g_decryptCipher.init(Cipher.DECRYPT_MODE, skeySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	public synchronized String encrypt(String s) throws Exception {
		String encodedEncryptedPassword = "";
		try {
			byte[] passwordBytes = s.getBytes(CHARACTERENCODING);
			byte[] encryptedPasswordBytes = g_encryptCipher
					.doFinal(passwordBytes);
			encodedEncryptedPassword = BASE64ENCODER
					.encode(encryptedPasswordBytes);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedEncryptedPassword;
	}

	public synchronized String decrypt(String s) throws Exception {

		String recoveredPassword = "";
		try {
			byte[] encryptedPasswordBytes = BASE64DECODER.decodeBuffer(s);
			byte[] passwordBytes = g_decryptCipher
					.doFinal(encryptedPasswordBytes);
			recoveredPassword = new String(passwordBytes, CHARACTERENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recoveredPassword;
	}
}
