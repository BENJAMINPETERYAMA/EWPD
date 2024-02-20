package com.wellpoint.ets.ebx.mapping.web.view;

import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsSingleFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import net.sf.jasperreports.engine.export.JRPdfExporter;

public class JasperReportPdfView extends AbstractJasperReportsSingleFormatView{

	public JasperReportPdfView() {
		
	}
	
	@Override
	protected void onInit() {
		java.util.Properties headers = new java.util.Properties();
		setHeaders(headers);
	} 
	
	@Override
	protected net.sf.jasperreports.engine.JRExporter createExporter() {
		return new JRPdfExporter();
	}

	@Override
	protected boolean useWriter() {
		return false;
	}
}
