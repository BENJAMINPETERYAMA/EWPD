package com.wellpoint.ets.ebx.simulation.util;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;

public class SimulationResourceValueLoader {

	private static List eb03VariableListForE030 = null;
	private static List eb03VariableListForE028 = null;
	private static List eb06VariableListForE031 = null;
	private static List variableFormatForE032 = null;
	private static List eb03VariableListForE018 = null;

	public static List getEB03ForE030() {
		if (eb03VariableListForE030 == null) {
			eb03VariableListForE030 = SimulationResourceBundle
					.getResourceBundle(
							DomainConstants.EB03_VARIABLES_LIST_E030,
							DomainConstants.PROPERTY_FILE_NAME);
		}
		return eb03VariableListForE030;
	}

	public static List getEB03ForE028() {
		if (eb03VariableListForE028 == null) {
			eb03VariableListForE028 = SimulationResourceBundle
					.getResourceBundle(
							DomainConstants.EB03_VARIABLES_LIST_E028,
							DomainConstants.PROPERTY_FILE_NAME);
		}
		return eb03VariableListForE028;
	}
	
	public static List getEB03ForE018() {
		if (eb03VariableListForE018 == null) {
			eb03VariableListForE018 = SimulationResourceBundle
					.getResourceBundle(
							DomainConstants.EB03FORE018,
							DomainConstants.PROPERTY_FILE_NAME);
		}
		return eb03VariableListForE018;
	}

	public static List getEB06ForE031() {
		if (eb06VariableListForE031 == null) {
			eb06VariableListForE031 = SimulationResourceBundle
					.getResourceBundle(
							DomainConstants.EB06_VARIABLES_LIST_E031,
							DomainConstants.PROPERTY_FILE_NAME);
		}
		return eb06VariableListForE031;
	}

	public static List getVariableFormatForE032() {
		if (variableFormatForE032 == null) {
			variableFormatForE032 = SimulationResourceBundle.getResourceBundle(
					DomainConstants.VARIABLES_FORMAT_LIST_E032,
					DomainConstants.PROPERTY_FILE_NAME);
		}
		return variableFormatForE032;
	}

}
