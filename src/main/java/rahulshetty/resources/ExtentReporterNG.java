package rahulshetty.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObject() {
		
		// Add ExtentsReport defedencies
		String file = System.getProperty("user.dir")+"//report//extent.html";
		ExtentSparkReporter report = new ExtentSparkReporter(file);
		report.config().setDocumentTitle("Arun testing");
		report.config().setReportName("Web Application test");
		
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Arun", "Testre");
		return extent;
	}

}
