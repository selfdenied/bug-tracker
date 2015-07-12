package com.epam.training.dao.mysqldao;

import java.sql.Connection;
import static com.epam.training.constant.DBFieldsConstants.STATUS_ID;
import static com.epam.training.constant.DBFieldsConstants.STATUS_NAME;
import static com.epam.training.dao.mysqldao.MySQLFeatureDAO.FeatureField.*;

/**
 * Class {@code MySQLStatusDAO} contains methods allowing to extract information
 * about Statuses of Issues reported and update existent Statuses.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.bean.Feature
 * @see com.epam.training.dao.mysqldao.MySQLFeatureDAO
 */
public class MySQLStatusDAO extends MySQLFeatureDAO {
	private static final String FIND_STATUSES = "SELECT * FROM STATUS";
	private static final String FIND_STATUS_BY_ID = "SELECT * FROM STATUS WHERE STATUS_ID = ?";
	private static final String ADD_STATUS = "INSERT INTO STATUS (STATUS_NAME) VALUES (?)";
	private static final String UPDATE_STATUS = "UPDATE STATUS SET STATUS_NAME = ? WHERE STATUS_ID = ?";

	/* here we put proper queries and field names to the Map */
	{
		queryMap.put(FEATURE_ID, STATUS_ID);
		queryMap.put(FEATURE_NAME, STATUS_NAME);
		queryMap.put(FIND_FEATURES, FIND_STATUSES);
		queryMap.put(FIND_FEATURE_BY_ID, FIND_STATUS_BY_ID);
		queryMap.put(ADD_FEATURE, ADD_STATUS);
		queryMap.put(UPDATE_FEATURE, UPDATE_STATUS);
	}

	/**
	 * Constructs MySQLStatusDAO object
	 * 
	 * @param connection
	 *            java.sql.Connection
	 */
	public MySQLStatusDAO(Connection connection) {
		super(connection);
	}
}
