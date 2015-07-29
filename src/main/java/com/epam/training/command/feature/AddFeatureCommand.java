package com.epam.training.command.feature;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Feature;
import com.epam.training.command.ICommand;
import com.epam.training.logic.FeatureLogic;
import com.epam.training.logic.FeatureType;
import com.epam.training.logic.LogicException;
import com.epam.training.util.Validator;

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

	@Override
	public String execute(HttpServletRequest request) {
		String url = BUNDLE.getString("add_feature");
		String feature = request.getParameter(PARAM_FEATURE);
		String featureName = request.getParameter(PARAM_FEATURE_NAME);
		FeatureType type = FeatureType.valueOf(feature.toUpperCase());

		if (featureName != null) {
			try {
				if (checkNameFree(request, featureName, type)) {
					Feature ft = new Feature();
					ft.setFeatureName(featureName);
					request.setAttribute("newFeatureAdded", true);
					request.setAttribute("formNotFilled", false);
					addNewFeature(request, ft, type);
				} else {
					request.setAttribute("feature", feature);
					request.setAttribute("featureNameExists", true);
					request.setAttribute("formNotFilled", true);
				}
			} catch (LogicException ex) {
				LOG.error(ex);
				request.setAttribute("exception", ex);
				url = BUNDLE.getString(ERROR);
			}
		} else {
			request.setAttribute("feature", feature);
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}

	/* method adds new feature to the database */
	private void addNewFeature(HttpServletRequest request, Feature ft,
			FeatureType type) throws LogicException {
		if (Validator.validateFeature(ft)) {
			FeatureLogic fl = new FeatureLogic();
			fl.addNewFeature(ft, type);
		} else {
			String feature = request.getParameter(PARAM_FEATURE);
			request.setAttribute("feature", feature);
			request.setAttribute("newFeatureAdded", false);
			request.setAttribute("featureAddError", true);
			request.setAttribute("formNotFilled", true);
		}
	}

	/* method checks if such Feature name already exists */
	private boolean checkNameFree(HttpServletRequest request,
			String featureName, FeatureType type) throws LogicException {
		boolean nameFree = true;
		FeatureLogic fl = new FeatureLogic();

		for (Feature feature : fl.featuresList(type)) {
			if (feature.getFeatureName().equals(featureName)) {
				nameFree = false;
			}
		}
		return nameFree;
	}
}
