package com.epam.training.command.feature;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Feature;
import com.epam.training.command.ICommand;
import com.epam.training.exception.GeneralLogicException;
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
	private static final String URL = "jsp/feature/editFeature.jsp";
	private static final String PARAM_FEATURE = "feature";
	private static final String PARAM_FEATURE_NAME = "featureName";
	private static final String PARAM_FEATURE_ID = "featureID";

	@Override
	public String execute(HttpServletRequest request) {
		String feature = request.getParameter(PARAM_FEATURE);
		String featureID = request.getParameter(PARAM_FEATURE_ID);
		String featureName = request.getParameter(PARAM_FEATURE_NAME);

		if (featureName != null) {
			FeatureType type = FeatureType.valueOf(feature.toUpperCase());
			Feature ft = new Feature();
			ft.setFeatureName(featureName);
			updateFeature(request, ft, type, Integer.parseInt(featureID));
			request.setAttribute("featureNameUpdated", true);
			request.setAttribute("formNotFilled", false);
		} else {
			request.setAttribute("feature", feature);
			request.setAttribute("featureID", featureID);
			request.setAttribute("formNotFilled", true);
		}
		return URL;
	}

	/* method updates Feature's data */
	private void updateFeature(HttpServletRequest request, Feature ft,
			FeatureType type, int id) {
		boolean errorFree = false;
		FeatureLogic fl = new FeatureLogic();

		try {
			errorFree = fl.updateFeature(ft, type, id);
		} catch (GeneralLogicException ex) {
			LOG.error(ex.getMessage());
		}
		if (!errorFree) {
			String feature = request.getParameter(PARAM_FEATURE);
			String featureID = request.getParameter(PARAM_FEATURE_ID);
			request.setAttribute("feature", feature);
			request.setAttribute("featureID", featureID);
			request.setAttribute("featureNameChangeError", true);
			request.setAttribute("formNotFilled", true);
		}
	}
}