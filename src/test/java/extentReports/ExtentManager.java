package extentReports;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.util.ReadPropertyFile;

public class ExtentManager {
	static ExtentReports extent;
	public static Properties prop;
	public static String reportDirectory;
	static ReadPropertyFile readPro = new ReadPropertyFile();
	
	public static synchronized ExtentReports createInstance() {
		DateFormat dateformat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
		
		reportDirectory = System.getProperty("user.dir");
		File reportFolder = new File(reportDirectory + "\\Report");
		if (!reportFolder.exists()) {
			reportFolder.mkdirs();
		}
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportDirectory+
				"\\Report\\"+"New_Report-"+dateformat.format(date)+".html");
//		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportDirectory + "\\latest\\" + "\\extent.html");
		sparkReporter.config().setReportName("Automation Testing");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		return extent;

	}

	public static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}
}