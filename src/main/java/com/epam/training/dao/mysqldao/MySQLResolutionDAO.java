package com.epam.training.dao.mysqldao;

import java.sql.Connection;

import static com.epam.training.dao.mysqldao.MySQLFeatureDAO.FeatureField.*;

/**
 * Class {@code MySQLResolutionDAO} contains methods allowing to extract
 * information about Resolution of Issues reported, add new and update existent
 * Resolutions.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.bean.Feature
 * @see com.epam.training.dao.mysqldao.MySQLFeatureDAO
 */
public class MySQLResolutionDAO extends MySQLFeatureDAO {
	private static final String RESOLUTION_ID = "RESOLUTION_ID";
	private static final String RESOLUTION_NAME = "RESOLUTION_NAME";
	private static final String FIND_RESOLUTIONS = "SELECT * FROM RESOLUTION";
	private static final String FIND_RESOLUTION_BY_ID = "SELECT * FROM RESOLUTION WHERE RESOLUTION_ID = ?";
	private static final String ADD_RESOLUTION = "INSERT INTO RESOLUTION (RESOLUTION_NAME) VALUES (?)";
	private static final String UPDATE_RESOLUTION = "UPDATE RESOLUTION SET RESOLUTION_NAME = ? WHERE RESOLUTION_ID = ?";

	/* here we put proper queries and field names to the Map */
	{
		queryMap.put(FEATURE_ID, RESOLUTION_ID);
		queryMap.put(FEATURE_NAME, RESOLUTION_NAME);
		queryMap.put(FIND_FEATURES, FIND_RESOLUTIONS);
		queryMap.put(FIND_FEATURE_BY_ID, FIND_RESOLUTION_BY_ID);
		queryMap.put(ADD_FEATURE, ADD_RESOLUTION);
		queryMap.put(UPDATE_FEATURE, UPDATE_RESOLUTION);
	}

	/**
	 * Constructs MySQLResolutionDAO object
	 * 
	 * @param connection
	 *            java.sql.Connection
	 */
	public MySQLResolutionDAO(Connection connection) {
		super(connection);
	}
}
