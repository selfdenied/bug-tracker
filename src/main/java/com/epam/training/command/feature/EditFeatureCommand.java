package com.epam.training.command.feature;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Feature;
import com.epam.training.command.ICommand;
import com.epam.training.exception.LogicException;
import com.epam.training.logic.FeatureLogic;
import com.epam.training.logic.featuretype.FeatureType;

/**
 * Class {@code EditFeatureCommand} allows to edit Features' (Statuses,
 * Resolutions, Types and Priorities) data.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class EditFeatureCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(EditFeatureCommand.class);
	private static final String PARAM_FEATURE = "feature";
	private static final String PARAM_FEATURE_NAME = "featureName";
	private static final String PARAM_FEATURE_ID = "featureID";
	private String url;

	@Override
	public String execute(HttpServletRequest request) {
		url = resBundle.getString("edit_feature");
		String feature = request.getParameter(PARAM_FEATURE);
		String featureID = request.getParameter(PARAM_FEATURE_ID);
		String featureName = request.getParameter(PARAM_FEATURE_NAME);

		if (featureName != null) {
			FeatureType type = FeatureType.valueOf(feature.toUpperCase());
			Feature ft = new Feature();
			ft.setFeatureName(featureName);
			request.setAttribute("featureNameUpdated", true);
			request.setAttribute("formNotFilled", false);
			updateFeature(request, ft, type, Integer.parseInt(featureID));
		} else {
			request.setAttribute("feature", feature);
			request.setAttribute("featureID", featureID);
			request.setAttribute("formNotFilled", true);
		}
		return url;
	}

	/* method updates Feature's data */
	private void updateFeature(HttpServletRequest request, Feature ft,
			FeatureType type, int id) {
		boolean errorFree = false;
		FeatureLogic fl = new FeatureLogic();

		try {
			errorFree = fl.updateFeature(ft, type, id);
		} catch (LogicException ex) {
			LOG.error(ex.getMessage());
			request.setAttribute("exception", ex);
			url = resBundle.getString("error500");
		}
		if (!errorFree) {
			String feature = request.getParameter(PARAM_FEATURE);
			String featureID = request.getParameter(PARAM_FEATURE_ID);
			setAttrsToRequest(request, feature, featureID);
		}
	}
	
	/* method sets some important attributes to request */
	private void setAttrsToRequest(HttpServletRequest request, 
			String feature, String featureID) {
		request.setAttribute("feature", feature);
		request.setAttribute("featureID", featureID);
		request.setAttribute("featureNameUpdated", false);
		request.setAttribute("featureNameChangeError", true);
		request.setAttribute("formNotFilled", true);
	}
}
