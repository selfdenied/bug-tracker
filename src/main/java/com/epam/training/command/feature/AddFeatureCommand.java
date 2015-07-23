package com.epam.training.command.feature;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Feature;
import com.epam.training.command.ICommand;
import com.epam.training.exception.GeneralLogicException;
import com.epam.training.logic.FeatureLogic;
import com.epam.training.logic.featuretype.FeatureType;

/**
 * Class {@code AddFeatureCommand} allows to add new Features (Resolutions,
 * Types and Priorities).
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class AddFeatureCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(AddFeatureCommand.class);
	private static final String PARAM_FEATURE = "feature";
	private static final String PARAM_FEATURE_NAME = "featureName";
	private String url;

	@Override
	public String execute(HttpServletRequest request) {
		url = resBundle.getString("add_feature");
		String feature = request.getParameter(PARAM_FEATURE);
		String featureName = request.getParameter(PARAM_FEATURE_NAME);
		FeatureType type = FeatureType.valueOf(feature.toUpperCase());

		if (featureName != null) {
			if (checkNameFree(featureName, type)) {
				Feature ft = new Feature();
				ft.setFeatureName(featureName);
				addNewFeature(request, ft, type);
				request.setAttribute("newFeatureAdded", true);
				request.setAttribute("formNotFilled", false);
			} else {
				request.setAttribute("feature", feature);
				request.setAttribute("featureNameExists", true);
				request.setAttribute("formNotFilled", true);
			}
		} else {
			request.setAttribute("feature", feature);
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}

	/* method adds new feature to the database */
	private void addNewFeature(HttpServletRequest request, Feature ft,
			FeatureType type) {
		boolean errorFree = false;
		FeatureLogic fl = new FeatureLogic();

		try {
			errorFree = fl.addNewFeature(ft, type);
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		if (!errorFree) {
			String feature = request.getParameter(PARAM_FEATURE);
			request.setAttribute("feature", feature);
			request.setAttribute("featureAddError", true);
			request.setAttribute("formNotFilled", true);
		}
	}

	/* method checks if such Feature name already exists */
	private boolean checkNameFree(String featureName, FeatureType type) {
		boolean nameFree = true;
		FeatureLogic fl = new FeatureLogic();

		try {
			for (Feature feature : fl.featuresList(type)) {
				if (feature.getFeatureName().equals(featureName)) {
					nameFree = false;
				}
			}
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		return nameFree;
	}
}
