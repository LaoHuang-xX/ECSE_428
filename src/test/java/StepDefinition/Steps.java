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
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;		

public class Steps {
	
	private final String PATH_TO_CHROME_DRIVER = System.getProperty("user.dir") + "\\driver\\chromedriver.exe";
	private final String USER_NAME = "williamtestuse";
	private final String PASSWORD = "!1qaz@2wsx";

    WebDriver driver;			
    		
    @Given("^Open the Firefox and launch the application$")					
    public void open_the_Firefox_and_launch_the_application() throws Throwable							
    {		
    	System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);
    	driver= new ChromeDriver();					
    	driver.manage().window().maximize();			
    	driver.get("https://mail.google.com/");					
    }		

    @When("^Enter the Username \"(.*)\" and Password \"(.*)\"$")			
    public void enter_the_Username_and_Password(String username,String password) throws Throwable 							
    {		
    	driver.findElement(By.id("identifierId")).sendKeys(USER_NAME);
        driver.findElement(By.id("identifierNext")).click();        
        
        WebElement pwdIn = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        pwdIn.sendKeys(PASSWORD);
        WebElement pwdButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("passwordNext")));        
        pwdButton.click();		
        
        
        Boolean ifInbox = (new WebDriverWait(driver, 10)).until(ExpectedConditions.urlMatches("https://mail.google.com/mail/#inbox"));
        if(ifInbox) {
        	driver.get("https://mail.google.com/mail/#inbox?compose=new");
        }
        
        WebElement to = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("to")));
        to.sendKeys("williamin18@gmail.com");
        
        WebElement subject = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("subjectbox")));
        subject.sendKeys("ecse 428 a2 test");
        
        WebElement attach = driver.findElement(By.cssSelector("div[class='a1 aaA aMZ']"));
        attach.click();
     
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        type(4);
       new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.name("attach")));

        
       WebElement send = (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Send']")));
       send.click();
        

    }		

    @Then("^Reset the credential$")					
    public void	Reset_the_credential() throws Throwable 							
    {		
    	//driver.findElement(By.name("btnReset")).click();
    	driver.close();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}		