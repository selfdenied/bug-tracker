package com.epam.training.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Class {@code ValidatorTest} is used for testing methods of
 * com.epam.training.util.Validator class.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.util.Validator
 */
public class ValidatorTest {
	private static final Logger LOG = Logger.getLogger(ValidatorTest.class);
	private List<String> correctPasswords = new ArrayList<>();
	private List<String> incorrectPasswords = new ArrayList<>();
	
	/* initializes the list of correct and incorrect passwords */
	{ 
		correctPasswords.add("fdrt7980");
		correctPasswords.add("12qwaszx@d");
		correctPasswords.add("dfre.123_gfty");
		correctPasswords.add("gf45-ghyt");
		incorrectPasswords.add("%*&&**()()");
		incorrectPasswords.add(null);
		incorrectPasswords.add("");
		incorrectPasswords.add("gshjbsjkcdscbsdhbcjkdscbhjdsbcjdbsdcb");
		
	}
		
	/**
	 * Tests the correctness of password validation method.
	 */
	@Test
	public void validateCorrectPasswordTest() {
		for (String password : correctPasswords) {
			boolean valid = Validator.validatePassword(password);
			LOG.info("Correct password: " + password + " Validation result: " + valid);
			Assert.assertTrue(valid);
		}
	}
	
	/**
	 * Tests the correctness of password validation method.
	 */
	@Test
	public void validateIncorrectPasswordTest() {
		for (String password : incorrectPasswords) {
			boolean valid = Validator.validatePassword(password);
			LOG.info("Incorrect password: " + password + " Validation result: " + valid);
			Assert.assertFalse(valid);
		}
	}
}
