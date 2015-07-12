package com.epam.training.dao.mysqldao;

import java.sql.Connection;

import static com.epam.training.constant.DBFieldsConstants.PRIORITY_ID;
import static com.epam.training.constant.DBFieldsConstants.PRIORITY_NAME;
import static com.epam.training.dao.mysqldao.MySQLFeatureDAO.FeatureField.*;

/**
 * Class {@code MySQLPriorityDAO} contains methods allowing to extract
 * information about Priorities of Issues reported, add new and update existent
 * Priorities.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.bean.Feature
 * @see com.epam.training.dao.mysqldao.MySQLFeatureDAO
 */
public class MySQLPriorityDAO extends MySQLFeatureDAO {
	private static final String FIND_PRIORITIES = "SELECT * FROM PRIORITY";
	private static final String FIND_PRIORITY_BY_ID = "SELECT * FROM PRIORITY WHERE PRIORITY_ID = ?";
	private static final String ADD_PRIORITY = "INSERT INTO PRIORITY (PRIORITY_NAME) VALUES (?)";
	private static final String UPDATE_PRIORITY = "UPDATE PRIORITY SET PRIORITY_NAME = ? WHERE PRIORITY_ID = ?";

	/* here we put proper queries and field names to the Map */
	{
		queryMap.put(FEATURE_ID, PRIORITY_ID);
		queryMap.put(FEATURE_NAME, PRIORITY_NAME);
		queryMap.put(FIND_FEATURES, FIND_PRIORITIES);
		queryMap.put(FIND_FEATURE_BY_ID, FIND_PRIORITY_BY_ID);
		queryMap.put(ADD_FEATURE, ADD_PRIORITY);
		queryMap.put(UPDATE_FEATURE, UPDATE_PRIORITY);
	}

	/**
	 * Constructs MySQLPriorityDAO object
	 * 
	 * @param connection
	 *            java.sql.Connection
	 */
	public MySQLPriorityDAO(Connection connection) {
		super(connection);
	}
}
