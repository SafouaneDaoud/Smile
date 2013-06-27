package tn.edu.espritCs.smile.services;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tn.edu.espritCs.smile.technical.UtilJdbc;

public class ReportingService {

	public void generateUsersReportByRole(String role) {
		try {
			// - Connection to database
			UtilJdbc utilJdbc = new UtilJdbc();
			Connection connection = utilJdbc.GetConnetion();
			// - Loading and compilation of the report
			JasperDesign jasperDesign = JRXmlLoader
					.load("C:\\Users\\Safouane\\git\\Smile\\tn.edu.espritCs.smile\\reports\\usersList.jrxml");
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			// - Parameters to send to report
			Map parameters = new HashMap();
			parameters.put("Role", role);
			// - Execution of the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, connection);
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			System.out.println("Compilation error: " + e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
}
