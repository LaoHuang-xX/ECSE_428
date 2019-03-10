package StepDefinition;		

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;		

public class Steps {
	
	private final String PATH_TO_CHROME_DRIVER = System.getProperty("user.dir") + "\\driver\\chromedriver.exe";
	private final String USER_NAME = "williamtestuse";
	private final String PASSWORD = "!1qaz@2wsx";

    WebDriver driver;			
    		
    @Given("^I am logged in$")					
    public void open_the_Firefox_and_launch_the_application() throws Throwable							
    {	
    	
    	checkInitialState();
    	
    	driver.manage().window().maximize();			
    	driver.get("https://mail.google.com/");
    	
    	driver.findElement(By.id("identifierId")).sendKeys(USER_NAME);
        driver.findElement(By.id("identifierNext")).click();        
        
        WebElement pwdIn = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        pwdIn.sendKeys(PASSWORD);
        WebElement pwdButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("passwordNext")));        
        pwdButton.click();	
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlMatches("https://mail.google.com/mail/#inbox"));

    }		

    @When("^I select 'Compose'$")
    public void select_compose() throws Throwable 							
    {	  
        driver.get("https://mail.google.com/mail/#inbox?compose=new");        
    }	
    
    @And("^I enter the recipient's \"(.*)\" email address$")
    public void enter_the_recipient_email_address(String recipient) throws Throwable
    {
    	WebElement to = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("to")));
        to.sendKeys(recipient);
    }
    
    @And("^I do not enter the recipient's email address$")
    public void do_not_enter_the_recipient_email_address() throws Throwable
    {
    	WebElement to = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("to")));
        to.sendKeys("");
    }
    
    @And("^I enter the subject \"(.*)\"$")
    public void enter_the_subject(String title) throws Throwable
    {
    	WebElement subject = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("subjectbox")));
        subject.sendKeys(title);
    }
    
    @And("^I do not enter the subject$")
    public void dont_enter_the_subject() throws Throwable
    {
    	WebElement subject = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("subjectbox")));
        subject.sendKeys("");     
    }
    
    
    @And("^I confirm to send the email without a subject or text$")
    public void confirm_to_send_the_email_without_a_subject_or_text() throws Throwable
    {
    	Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    
    @And("^I attach a file \"(.*)\" to the email$")
    public void attach_a_file_to_the_email(String number) throws Throwable
    {
    	WebElement attach = driver.findElement(By.cssSelector("div[class='a1 aaA aMZ']"));
        attach.click();
        
        attach(Integer.parseInt(number));
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.name("attach")));
    }
    
    @And("^I select 'Send'$")
    public void select_send() throws Throwable
    {
    	WebElement send = (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Send']")));
        send.click();        
    }
    
    @Then("^there should be a window saying the email has been sent and I should be able to share my files with others$")
    public void send_successfully() throws Throwable
    {
    	WebElement submitted = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Message sent.']")));
        System.out.println(submitted.getText());
        backToInitialState();
    }
    
    @Then("^there should be a an error saying I can not send an email without a recipient$")
    public void send_uncuccessfully() throws Throwable
    {
    	WebElement failButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("ok")));
    	WebElement errorMessage = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='Kj-JD-Jz']")));
    	System.out.println(errorMessage.getText());
    	failButton.click();
    	
        
    	backToInitialState();
    }
    
    // The helper method to attach files
    private void attach(int fileID) {
    	try {
			Runtime.getRuntime().exec("fileUpload\\fileUpload"+fileID+".exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	/*
    	try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_C);
			r.keyPress(KeyEvent.VK_SEMICOLON);
			r.keyRelease(KeyEvent.VK_SEMICOLON);
			r.keyRelease(KeyEvent.VK_SHIFT);
			r.keyPress(KeyEvent.VK_BACK_SLASH);
			r.keyRelease(KeyEvent.VK_BACK_SLASH);
			r.keyPress(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_A);
			r.keyPress(KeyEvent.VK_2);
			r.keyRelease(KeyEvent.VK_2);
			r.keyPress(KeyEvent.VK_BACK_SLASH);
			r.keyRelease(KeyEvent.VK_BACK_SLASH);
			r.keyPress(KeyEvent.VK_0+fileID);
			r.keyRelease(KeyEvent.VK_0+fileID);
			r.keyPress(KeyEvent.VK_PERIOD);
			r.keyRelease(KeyEvent.VK_PERIOD);
			r.keyPress(KeyEvent.VK_J);
			r.keyRelease(KeyEvent.VK_J);
			r.keyPress(KeyEvent.VK_P);
			r.keyRelease(KeyEvent.VK_P);
			r.keyPress(KeyEvent.VK_G);
			r.keyRelease(KeyEvent.VK_G);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		*/
    }
    
    // The method to confirm the system is in appropriate initial state before tests are run
    private void checkInitialState() throws MalformedURLException {
    	if (driver == null) {
    		System.out.println("Get into the initial state..\n");
    		System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);
            driver = new ChromeDriver();
            System.out.print("Initial setup has been finished!\n");
    	}
    }
    
    // The method to ensure the system is returned to the initial state after tests are run
    private void backToInitialState() throws Throwable {
    	if (driver != null) {
	    	System.out.println("Return the system to the initial state..");
	    	driver.quit();
    	}
    }
}		