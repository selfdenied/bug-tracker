package com.epam.training.command.feature;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.training.bean.Feature;
import com.epam.training.command.ICommand;
import com.epam.training.logic.FeatureLogic;
import com.epam.training.logic.FeatureType;
import com.epam.training.logic.LogicException;

/**
 * Class {@code ListFeaturesCommand} allows to view the list of Features
 * (Statuses, Resolutions, Types and Priorities) available in the application.
 * 
 * @author Vasili Andreev
 * @version 1.0
 * @see com.epam.training.command.ICommand
 */
public class ListFeaturesCommand implements ICommand {
	private static final Logger LOG = Logger.getLogger(ListFeaturesCommand.class);
	private static final String PARAM_FEATURE = "feature";

	@Override
	public String execute(HttpServletRequest request) {
		String url = BUNDLE.getString("list_features");
		FeatureLogic fl = new FeatureLogic();
		List<Feature> listOfFeatures = new ArrayList<>();
		String feature = request.getParameter(PARAM_FEATURE);
		FeatureType featureType = FeatureType.valueOf(feature.toUpperCase());

		try {
			listOfFeatures = fl.featuresList(featureType);
		} catch (LogicException ex) {
			LOG.error(ex);
			request.setAttribute("exception", ex);
			url = BUNDLE.getString(ERROR);
		}
		request.setAttribute("feature", feature);
		request.setAttribute("listOfFeatures", listOfFeatures);
		return url;
	}
}
