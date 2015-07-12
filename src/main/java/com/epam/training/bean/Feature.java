package com.epam.training.bean;

/**
 * Class {@code Feature} is a Java Bean that stores the data of all issues'
 * features available in the application (status, type, priority, resolution).
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class Feature extends Entity {
	private static final long serialVersionUID = -5182850656269573010L;
	private String featureName;

	/* no need to use setters with validation (we take data from DB) */
	
	/**
	 * Returns the name of a feature available in the application.
	 * 
	 * @return Feature Name
	 */
	public String getFeatureName() {
		return featureName;
	}

	/**
	 * Sets the name of a feature.
	 * 
	 * @param featureName the feature's name
	 */
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
}
