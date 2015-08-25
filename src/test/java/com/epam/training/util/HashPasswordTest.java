package com.epam.training.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Class {@code HashPasswordTest} is used for testing methods of
 * com.epam.training.util.HashPassword class.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.util.HashPassword
 */
public class HashPasswordTest {
	private static final Logger LOG = Logger.getLogger(HashPasswordTest.class);
	private static final String password = "fdrt7980";

	/**
	 * Tests the correctness of password hash creation and password validation
	 * (compares the hash of entered password and the hash stored in the
	 * database) method.
	 */
	@Test
	public void createHashValidatePasswordTest()
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String hash = HashPassword.createHash(password);
		boolean passwordOk = HashPassword.validatePassword(password, hash);
		LOG.info("Hash \"" + hash + "\" corresponds to password " + password + ": " + passwordOk);
		Assert.assertTrue(passwordOk);
	}
}
