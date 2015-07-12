package com.epam.training.bean;

import java.io.Serializable;

/**
 * Abstract Class {@code Entity} is a Java Bean that is a superclass to all
 * other entity classes (Member, Issue, Project, etc.).
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public abstract class Entity implements Serializable {
	private static final long serialVersionUID = 5485439747802349345L;
	private int id;

	/* no need to use setters with validation (we take data from DB) */

	/**
	 * Returns the unique ID of a given entity.
	 * 
	 * @return Entity ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of a given entity.
	 * 
	 * @param id	the ID of a given entity
	 */
	public void setId(int id) {
		this.id = id;
	}
}
