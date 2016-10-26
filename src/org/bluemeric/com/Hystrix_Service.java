package org.bluemeric.com;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.bluemeric.com.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

@Listeners(org.uncommons.reportng.HTMLReporter.class)
	public class Hystrix_Service implements ITestListener{

		
	Utility utility = new Utility();
	String workspace=System.getProperty("user.dir");
	Properties prop = new Properties();
	ExtentReports report = Suite.report;
	ExtentTest test = Suite.test;
	Suite suite = new Suite();
	WebDriver driver = suite.newDriver();
	//static String url = "http://" + System.getProperty("APP_ENDPOINT");
	static String url = "http://104.199.224.192:7979/";
	@Parameters({"suiteName"})
	@BeforeMethod
	 public void createreport(ITestContext arg0,@Optional String suiteName){
		
		try{		
		Thread.sleep(10000);
		
		test = report.startTest(arg0.getName());
	    test.assignCategory(suiteName);
		}
		catch(Exception e){
		e.printStackTrace();
		}
	 }
	
	@Parameters({"param","param1"})
	@Test
	public void title(@Optional String param,@Optional String param1) throws IOException{

		driver.get(url);
		driver.manage().window().maximize();
		Assert.assertEquals("Hystrix Dashboard", driver.getTitle());
		System.out.println("=====>Hystrix Dashboard");
	}
	
	@Parameters({"param","param1","param2"})
	@Test
	public void hystrix_stream(@Optional String param,@Optional String param1,@Optional String param2) {
		
		WebDriverWait wait =new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(".//*[@id='stream']")))).sendKeys(param+"hystrix.stream");
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(".//*[@id='delay']")))).sendKeys(param1);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(".//*[@id='title']")))).sendKeys(param2);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("html/body/div[1]/center/button")))).click();
		String Hystrix_Stream_Name = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(".//*[@id='title_name']")))).getText();
		Assert.assertEquals("Hystrix Stream: "+param2, Hystrix_Stream_Name);
		System.out.println("=====>Hystrix Stream");
		
	}
	
	@Parameters({"param","param1"})
	@Test
	public void monitor(@Optional String param,@Optional String param1) throws IOException{
		
		WebDriverWait wait =new WebDriverWait(driver,100);
		Assert.assertEquals("Circuit Closed", wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(".//*[@id='CIRCUIT_findAll']/div[3]/div/div[4]")))).getText());
		System.out.println("=====>Hystrix Stream Monitor");
	}
	
	
	@Parameters({"suiteName"})
	@AfterMethod
		public void screenshot(ITestResult arg0,@Optional String suiteName) {
		
		Capabilities caps = ((RemoteWebDriver)driver).getCapabilities();
		String screenshotname =suiteName+arg0.getName().toString(); 
		try {
		     String screenshot =  utility.screenshot(screenshotname);;
			 System.setProperty("org.uncommons.reportng.escape-output", "false");
			   Reporter.setCurrentTestResult(arg0); //// output gets lost without this entry
			   String browser = "Browser: "+caps.getBrowserName().toUpperCase();
			   String platform = "Platform: "+caps.getPlatform().toString();
			   Reporter.log("<b> <font color='blue'>"+url+"\n"+browser+"\n"+platform+"</font></b>");
			   Reporter.log(
				  "<a title= \"title\" href=\"" + screenshot + "\">" +
				 "<img width=\"700\" height=\"550\" src=\"" + screenshot +
				  "\"></a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			int result=arg0.getStatus();
		      String testcase =suiteName+arg0.getName().toString(); 
		     Properties prop = new Properties();
		     if(result ==1){
		      prop.put(testcase, "PASS");
		      test.log(LogStatus.PASS, arg0.getName()+"Screencast below:" + test.addScreenCapture("./html/"+screenshotname+".png"),suiteName+arg0.getName());
		     }else if(result ==2){
		      prop.put(testcase, "FAIL"); 
		      test.log(LogStatus.FAIL, arg0.getName()+"Screencast below:" + test.addScreenCapture("./html/"+screenshotname+".png"),arg0.getThrowable());
		     }else if(result ==3){
		      prop.put(testcase, "SKIP"); 
		      test.log(LogStatus.SKIP, arg0.getName()+"Screencast below:" + test.addScreenCapture("./html/"+screenshotname+".png"),arg0.getThrowable());
		     }
		     try {
		      FileInputStream fs = new FileInputStream(workspace + "file.Properties");
		      prop.load(fs);
		      fs.close();
		      FileOutputStream fos = new FileOutputStream(workspace + "file.Properties");
		      prop.store(fos, "Test Result");
	
		      fos.flush();
		     } catch (IOException e) { 
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		     }
			
			report.endTest(test);
			report.flush();
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
	
