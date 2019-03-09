package StepDefinition;		

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
    	driver.findElement(By.id("identifierId")).sendKeys("williamtestuse");
        driver.findElement(By.id("identifierNext")).click();        
        
        WebElement pwdIn = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        pwdIn.sendKeys("!1qaz@2wsx");
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
    }		

    @Then("^Reset the credential$")					
    public void	Reset_the_credential() throws Throwable 							
    {		
    	//driver.findElement(By.name("btnReset")).click();
    	driver.close();
    } 
}		