package StepDefinition;		

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

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
	private final String SUBJECT = "ecse 428 a2 test";

    WebDriver driver;			
    		
    @Given("^I am logged in$")					
    public void open_the_Firefox_and_launch_the_application() throws Throwable							
    {		
    	if (driver == null) {
            System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);
            driver = new ChromeDriver();
            System.out.print("ChromeDriver has been setted up\n");
        }
    	driver.manage().window().maximize();			
    	driver.get("https://mail.google.com/");
    	
    	driver.findElement(By.id("identifierId")).sendKeys(USER_NAME);
        driver.findElement(By.id("identifierNext")).click();        
        
        WebElement pwdIn = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        pwdIn.sendKeys(PASSWORD);
        WebElement pwdButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("passwordNext")));        
        pwdButton.click();	
    }		

    @When("^I select 'Compose'$")
    public void select_compose() throws Throwable 							
    {	
        Boolean ifInbox = (new WebDriverWait(driver, 10)).until(ExpectedConditions.urlMatches("https://mail.google.com/mail/#inbox"));
        if(ifInbox) {
        	driver.get("https://mail.google.com/mail/#inbox?compose=new");
        }
    }	
    
    @And("^I enter the recipient's \"(.*)\" email address$")
    public void enter_the_recipient_email_address(String recipient) throws Throwable
    {
    	WebElement to = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("to")));
        to.sendKeys(recipient);
    }
    
    @And("^I enter the subject$")
    public void enter_the_subject() throws Throwable
    {
    	WebElement subject = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("subjectbox")));
        subject.sendKeys(SUBJECT);
    }
    
    @And("^I attach a file \"(.*)\" to the email$")
    public void attach_a_file_to_the_email(String number) throws Throwable
    {
    	WebElement attach = driver.findElement(By.cssSelector("div[class='a1 aaA aMZ']"));
        attach.click();
        int id = Integer.parseInt(number);
        
        
        type(Integer.parseInt(number));
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.name("attach")));
    }
    
    @And("^I select 'Send'$")
    public void select_send() throws Throwable
    {
    	WebElement send = (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Send']")));
        send.click();
    }
    
    private void type(int fileID) {//type different file name
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
    }
}		