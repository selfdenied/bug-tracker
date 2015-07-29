package com.epam.training.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.epam.training.bean.Feature;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.dao.DAOException;

/**
 * Abstract class {@code MySQLFeatureDAO} contains methods allowing to extract
 * information about abstract Features of Issues reported, add new Features and
 * update them. The list of Features include: Status, Priority, Type, and
 * Resolution.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.bean.Feature
 * @see com.epam.training.dao.AbstractDAO
 */
public abstract class MySQLFeatureDAO extends AbstractDAO<Feature> {
	protected ConcurrentHashMap<FeatureField, String> queryMap = new ConcurrentHashMap<>();

	/**
	 * Constructs MySQLFeatureDAO object
	 * 
	 * @param connection
	 *            java.sql.Connection
	 */
	public MySQLFeatureDAO(Connection connection) {
		super(connection);
	}

	/* finds all Features in the application */
	@Override
	public List<Feature> findAll() throws DAOException {
		List<Feature> featuresList = new ArrayList<Feature>();
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(queryMap.get(FeatureField.FIND_FEATURES));
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Feature and initializing its fields */
				Feature feature = new Feature();
				feature.setId(rs.getInt(queryMap.get(FeatureField.FEATURE_ID)));
				feature.setFeatureName(rs.getString(queryMap.get(FeatureField.FEATURE_NAME)));
				featuresList.add(feature);
			}
		} catch (SQLException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			close(prepStatement);
		}
		return featuresList;
	}

	/* returns Feature with the given ID */
	@Override
	public Feature findEntityByID(int id) throws DAOException {
		Feature feature = null;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(queryMap.get(FeatureField.FIND_FEATURE_BY_ID));
			prepStatement.setInt(1, id);
			ResultSet rs = prepStatement.executeQuery();
			while (rs.next()) {
				/* creating a new Feature and initializing its fields */
				feature = new Feature();
				feature.setId(rs.getInt(queryMap.get(FeatureField.FEATURE_ID)));
				feature.setFeatureName(rs.getString(queryMap.get(FeatureField.FEATURE_NAME)));
			}
		} catch (SQLException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			close(prepStatement);
		}
		return feature;
	}

	/* adds new Feature to the database */
	@Override
	public boolean addNewEntity(Feature feature) throws DAOException {
		boolean isAdded = false;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(queryMap.get(FeatureField.ADD_FEATURE));
			prepStatement.setString(1, feature.getFeatureName());
			prepStatement.executeUpdate();
			isAdded = true;
		} catch (SQLException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			close(prepStatement);
		}
		return isAdded;
	}

	/* updates Feature's data */
	@Override
	public boolean updateEntity(Feature feature, int id)
			throws DAOException {
		boolean isUpdated = false;
		PreparedStatement prepStatement = null;

		try {
			prepStatement = connection.prepareStatement(queryMap.get(FeatureField.UPDATE_FEATURE));
			prepStatement.setString(1, feature.getFeatureName());
			prepStatement.setInt(2, id);
			prepStatement.executeUpdate();
			isUpdated = true;
		} catch (SQLException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			close(prepStatement);
		}
		return isUpdated;
	}

	/**
	 * Enum {@code FeatureField} contains the list of the keys that are used to
	 * retrieve query and field constants from a Map. Each subclass will use
	 * these predefined keys to put its own query and field constants in the
	 * Map. I.e. class methods will operate with different database tables
	 * depending on the specific Feature (Status, Type, Priority or Resolution).
	 * 
	 * @author Vasili Andreev
	 * @version 1.0
	 */
	public static enum FeatureField {
		FIND_FEATURES, FIND_FEATURE_BY_ID, ADD_FEATURE, UPDATE_FEATURE, FEATURE_ID, FEATURE_NAME;
	}
}
