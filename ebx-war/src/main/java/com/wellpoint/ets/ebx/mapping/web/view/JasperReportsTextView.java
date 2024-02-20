package com.wellpoint.ets.ebx.mapping.web.view;

import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsSingleFormatView;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

public class JasperReportsTextView extends AbstractJasperReportsSingleFormatView{

	public JasperReportsTextView() {
		setContentType("text/plain");
	}
	
	@Override
	protected void onInit() {
		java.util.Properties headers = new java.util.Properties();
		setHeaders(headers);
	} 

	protected JRExporter createExporter() {
		JRTextExporter exporter = new JRTextExporter();
		exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(10));
		exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(10));
		exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Integer(1700));
		exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Integer(300));
		return exporter;
	}

	protected boolean useWriter() {
		
		return false;
	}
}
