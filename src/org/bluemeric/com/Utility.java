package org.bluemeric.com;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

@Listeners(org.uncommons.reportng.HTMLReporter.class)
public class Utility implements ITestListener {
	static long millis = System.currentTimeMillis();
	Suite suite = new Suite();
	String screenload = System.getProperty("user.dir") + "/test-output/html/";
	String screenget = "";
	static String url = "http://" + System.getenv("APP_ENDPOINT")+":"+ System.getenv("APP_PORT");	
	//static String url = "http://" + System.getProperty("APP_ENDPOINT");
	WebDriver driver = suite.newDriver();
	static Logger log = Logger.getLogger(Utility.class);
	
	
	public void title(String username) throws IOException {
		driver.get(url);
		driver.manage().window().maximize();
		System.out.println("hi sample");
		/*try{
			WebDriverWait wait=new WebDriverWait(driver,1000);
			driver.get(url);
			driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//a[text()='Sign up']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model='new_user.email']"))).sendKeys(username+millis+"@trov.co.in");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model='new_user.password']"))).sendKeys("trov-it123");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Sign up']"))).click();
		Thread.sleep(10000);
		String val = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div[2]/div/div/div/div/h4"))).getText();
		System.out.println(val);
		Assert.assertTrue(true, val);
		Reporter.log("Url = " + url);
		}catch(Exception e){
			e.getMessage();
		}*/
		}
	public void login(String username) throws IOException {
		try{
			WebDriverWait wait=new WebDriverWait(driver,1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model='user.email']"))).sendKeys(username+millis+"@trov.co.in");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model='user.password']"))).sendKeys("trov-it123");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Log in']"))).click();
		
		String val1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div[2]/div/h3"))).getText();
		System.out.println(val1);
		Assert.assertTrue(true, val1);
		Reporter.log("Url = " + url);
		}catch(Exception e){
			e.getMessage();
		}
		}
	public void CreatePoll(String question,String option1,String option2) throws IOException {
		try{
			WebDriverWait wait=new WebDriverWait(driver,1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Create Poll']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@ng-model='poll.question']"))).sendKeys(question);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("option1"))).sendKeys(option1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("option2"))).sendKeys(option2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Create']"))).click();
		Reporter.log("Url = " + url);
		Thread.sleep(10000);
		}catch(Exception e){
			e.getMessage();
		}
	}
	public void CreatePollvalidation(String question) throws IOException {
		try{
        WebDriverWait wait=new WebDriverWait(driver,1000);
		String val1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div[2]/div/div/div[2]"))).getText();
		System.out.println(val1);
		String val2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+question+"']"))).getText();
		Assert.assertTrue(true, val2);
		Thread.sleep(10000);
		Reporter.log("Url = " + url);
		}catch(Exception e){
			e.getMessage();
		}
	}
	public void logout() throws IOException {
		try{
		WebDriverWait wait=new WebDriverWait(driver,1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log out']"))).click();
		String val=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Sign up']"))).getText();
		System.out.println(val);
		Assert.assertEquals("Sign up", val);
		Thread.sleep(2000);
		Reporter.log("Url = " + url);
		//driver.close();
		}catch(Exception e){
			e.getMessage();
		}
	}
	 
	public String screenshot(String screenshot) throws IOException {
		
		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(screenload +screenshot + ".png"));
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String image = screenget + screenshot + ".png";
		Reporter.log("<a title= \"title\" href=\"" + image + "\">" + "<img width=\"700\" height=\"550\" src=\"" + image
				+ "\"></a>");
		return image;

	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
}
