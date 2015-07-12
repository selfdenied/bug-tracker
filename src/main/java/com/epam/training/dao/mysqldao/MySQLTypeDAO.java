package com.epam.training.dao.mysqldao;

import java.sql.Connection;

import static com.epam.training.constant.DBFieldsConstants.TYPE_ID;
import static com.epam.training.constant.DBFieldsConstants.TYPE_NAME;
import static com.epam.training.dao.mysqldao.MySQLFeatureDAO.FeatureField.*;

/**
 * Class {@code MySQLTypeDAO} contains methods allowing to extract information
 * about Types of Issues reported, add new and update existent Types.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.bean.Feature
 * @see com.epam.training.dao.mysqldao.MySQLFeatureDAO
 */
public class MySQLTypeDAO extends MySQLFeatureDAO {
	private static final String FIND_TYPES = "SELECT * FROM TYPE";
	private static final String FIND_TYPE_BY_ID = "SELECT * FROM TYPE WHERE TYPE_ID = ?";
	private static final String ADD_TYPE = "INSERT INTO TYPE (TYPE_NAME) VALUES (?)";
	private static final String UPDATE_TYPE = "UPDATE TYPE SET TYPE_NAME = ? WHERE TYPE_ID = ?";

	/* here we put proper queries and field names to the Map */
	{
		queryMap.put(FEATURE_ID, TYPE_ID);
		queryMap.put(FEATURE_NAME, TYPE_NAME);
		queryMap.put(FIND_FEATURES, FIND_TYPES);
		queryMap.put(FIND_FEATURE_BY_ID, FIND_TYPE_BY_ID);
		queryMap.put(ADD_FEATURE, ADD_TYPE);
		queryMap.put(UPDATE_FEATURE, UPDATE_TYPE);
	}

	/**
	 * Constructs MySQLTypeDAO object
	 * 
	 * @param connection
	 *            java.sql.Connection
	 */
	public MySQLTypeDAO(Connection connection) {
		super(connection);
	}
}
