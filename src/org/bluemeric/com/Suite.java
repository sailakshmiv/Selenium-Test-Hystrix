package org.bluemeric.com;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.bluemeric.common.XmltoJava;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Suite implements ITestListener {

	//static ChromeDriver driver;
	static WebDriver driver;
	static boolean status = true;
	static String projectHome = System.getProperty("user.dir");
	static String url = System.getProperty("accessUrl");
	static Logger log = Logger.getLogger(Utility.class);
	static ExtentReports report;
	static ExtentTest test;
	static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	List<XmlSuite> xmlSuites;
	static String workspace=System.getProperty("user.dir");
	
	public Suite() {
		xmlSuites = new ArrayList<XmlSuite>();
	}

	public WebDriver newDriver() {
		Capabilities caps = ((RemoteWebDriver)driver).getCapabilities();
		String browser = caps.getBrowserName();
		if (driver == null) {
			if(browser.equals("firefox")){
				return new FirefoxDriver();
			}else{
		//	return new PhantomJSDriver();//return new PhantomJSDriver();
			}
		}
		return driver;
	}
	
	
	public void method(boolean stat) {
		status = stat;
	}

	public static void deleteFile() throws IOException {
		
		try{
			report = new ExtentReports(projectHome+"/test-output"+"/ExtendedReports"+timeStamp+".html");
			report.loadConfig(new File(projectHome+"/extent-config.xml"));
            File file = new File(workspace+"file.Properties");
	    	
			if(file.delete()){
				System.out.println(file.getName() + " is deleted!");
			}else{
				System.out.println("Delete operation is failed.");
			}
			file.createNewFile();
		} catch (Exception e) {
		e.printStackTrace();
	  }
	}
	
	
	private void runTests(TestNG tng) throws JAXBException, Exception {

		XmltoJava xmltojava = (XmltoJava) org.bluemeric.common.GenericClass.unmarshallClass(
				projectHome + "/" +"config.xml", XmltoJava.class);
		
		for (int k = 0; k < xmltojava.getSuite().length; k++) {
			
			XmlSuite suite = new XmlSuite();
			ArrayList<XmlTest> tests = new ArrayList<XmlTest>();				
			
			for (int l = 0; l < xmltojava.getSuite()[k].getParameter().length; l++) {
				
				Map<String, String> parameters = new HashMap<String, String>();
				suite.setName(xmltojava.getSuite()[k].getSuitename());
				List<XmlTest> xmlTest = new ArrayList<XmlTest>();
				
				XmlTest test = new XmlTest(suite);
				test.setName(xmltojava.getSuite()[k].getParameter()[l].getTestcase());
	
				parameters.put("suiteName", xmltojava.getSuite()[k].getParameter()[l].getName());
				parameters.put("Testcase", xmltojava.getSuite()[k].getParameter()[l].getTestcase());
				parameters.put("param", xmltojava.getSuite()[k].getParameter()[l].getParam());
				parameters.put("param1", xmltojava.getSuite()[k].getParameter()[l].getParam1());
				parameters.put("param2", xmltojava.getSuite()[k].getParameter()[l].getParam2());
				parameters.put("Depends", xmltojava.getSuite()[k].getParameter()[l].getDependson());
				test.setParameters(parameters);

				ArrayList<XmlInclude> methodsToRun = new ArrayList<XmlInclude>();
				ArrayList<XmlClass> classes1 = new ArrayList<XmlClass>();
				XmlClass classes = new XmlClass();
				
				
					classes.setName("org.bluemeric.com." + xmltojava.getSuite()[k].getClassname());
				
				
				methodsToRun.add(new XmlInclude(xmltojava.getSuite()[k].getParameter()[l].getTestcase()));
				classes.setIncludedMethods(methodsToRun);
				classes1.add(classes);
				test.setXmlClasses(classes1);
				xmlTest.add(test);
				tests.addAll(xmlTest);
			}
			suite.setTests(tests);
			xmlSuites.add(suite);
		}
		tng.setXmlSuites(xmlSuites);
		tng.run();
	}
	public static void main(String[] args) throws JAXBException, Exception {
		//System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		System.setProperty("file.log","./log/log.log");
		PropertyConfigurator.configure("./log4j.properties");
		//driver=new PhantomJSDriver(); //driver=new PhantomJSDriver();
		driver = new FirefoxDriver();
		Suite rtest = new Suite();
		System.out.println("Test started.......");
		deleteFile();
		
		System.out.println("Test started.......");
		TestNG tng = new TestNG();
		tng.addListener(rtest);
		rtest.runTests(tng);
		driver.quit();
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}

