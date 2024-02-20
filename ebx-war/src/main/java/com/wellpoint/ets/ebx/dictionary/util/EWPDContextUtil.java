package com.wellpoint.ets.ebx.dictionary.util;

import org.springframework.context.ApplicationContext;

public class EWPDContextUtil {
	
	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		EWPDContextUtil.context = context;
	}
}
