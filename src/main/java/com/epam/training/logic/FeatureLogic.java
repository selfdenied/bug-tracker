package com.epam.training.logic;

import static com.epam.training.dao.factory.DAOFactoryType.MYSQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.bean.Feature;
import com.epam.training.connection.ConnectionPool;
import com.epam.training.dao.AbstractDAO;
import com.epam.training.dao.DAOException;
import com.epam.training.dao.factory.AbstractDAOFactory;

/**
 * Class {@code FeatureLogic} contains various methods that use DAO layer to
 * retrieve information about the Feature(s) from a database, add or update
 * Feature data. These methods will be further used by Command layer.
 * 
 * @author Vasili Andreev
 * @version 1.0
 */
public class FeatureLogic {
	private ConnectionPool pool;
	private Connection connection;
	private AbstractDAOFactory factory;

	/**
	 * Constructs FeatureLogic object.
	 */
	public FeatureLogic() {
		pool = ConnectionPool.getInstance();
	}

	/**
	 * Method returns the list of Features of selected type.
	 * 
	 * @param featureType
	 *            the selected type of Features
	 * @return The list of Features of selected type
	 * @throws LogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public List<Feature> featuresList(FeatureType featureType)
			throws LogicException {
		List<Feature> listOfFeatures = new ArrayList<>();

		try {
			listOfFeatures = obtainFeatureDAO(featureType).findAll();
		} catch (DAOException ex) {
			throw new LogicException(
					"Error. Unable to retrieve the list of Features!", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return listOfFeatures;
	}

	/**
	 * Method returns the Feature with the given ID.
	 * 
	 * @param featureType
	 *            the selected type of a Feature
	 * @param id
	 *            the Feature's ID
	 * @return The Feature with the given ID
	 * @throws LogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public Feature findFeature(FeatureType featureType, int id)
			throws LogicException {
		Feature feature = new Feature();

		try {
			feature = obtainFeatureDAO(featureType).findEntityByID(id);
		} catch (DAOException ex) {
			throw new LogicException("Error. Unable to retrieve a Feature!", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return feature;
	}

	/**
	 * Method adds new Feature to the database.
	 * 
	 * @param featureType
	 *            the selected type of Feature
	 * @param feature
	 *            new Feature
	 * @return {@code true} when new Feature was successfully added and
	 *         {@code false} otherwise
	 * @throws LogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public boolean addNewFeature(Feature ft, FeatureType featureType)
			throws LogicException {
		boolean isAdded = false;
		AbstractDAO<Feature> featureDAO = obtainFeatureDAO(featureType);

		try {
			isAdded = featureDAO.addNewEntity(ft);
		} catch (DAOException ex) {
			throw new LogicException(
					"Error. Unable to add Feature to the database!", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return isAdded;
	}

	/**
	 * Method updated existing Feature data.
	 * 
	 * @param featureType
	 *            the selected type of Feature
	 * @param feature
	 *            new Feature
	 * @param id
	 *            Feature's ID
	 * @return {@code true} when new Feature data was successfully updated and
	 *         {@code false} otherwise
	 * @throws LogicException
	 *             If a Logic exception of some sort has occurred
	 */
	public boolean updateFeature(Feature ft, FeatureType featureType, int id)
			throws LogicException {
		boolean isUpdated = false;
		AbstractDAO<Feature> featureDAO = obtainFeatureDAO(featureType);

		try {
			isUpdated = featureDAO.updateEntity(ft, id);
		} catch (DAOException ex) {
			throw new LogicException("Error. Unable to update Feature's data!", ex);
		} finally {
			pool.releaseConnection(connection);
		}
		return isUpdated;
	}

	/* Method returns a proper FeatureDAO depending on the selected Feature type */
	private AbstractDAO<Feature> obtainFeatureDAO(FeatureType featureType) {
		AbstractDAO<Feature> featureDAO;

		switch (featureType) {
		case STATUS:
			featureDAO = initDAOFactory().getStatusDAO();
			break;
		case RESOLUTION:
			featureDAO = initDAOFactory().getResolutionDAO();
			break;
		case TYPE:
			featureDAO = initDAOFactory().getTypeDAO();
			break;
		case PRIORITY:
			featureDAO = initDAOFactory().getPriorityDAO();
			break;
		default:
			throw new EnumConstantNotPresentException(FeatureType.class,
					featureType.name());
		}
		return featureDAO;
	}

	/* supplementary method that initializes connection and DAO factory */
	private AbstractDAOFactory initDAOFactory() {
		connection = pool.getConnection();
		factory = AbstractDAOFactory.getDAOFactory(connection, MYSQL);
		return factory;
	}
}
