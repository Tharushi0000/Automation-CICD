package Package1.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Package1.AbstarctComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
		
		//Have to enter only login page locators
		//WebElement useremail= driver.findElement(By.id("userEmail"));
		
	    //PageFacctory
		@FindBy(id="userEmail")
		WebElement useremail;
		
		@FindBy(id="userPassword")
		WebElement passwordEle;
		
		@FindBy(id="login")
		WebElement submit;
		
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;
		
		
		//Actions
	    public ProductCatelog LoginApplication(String email,String password) {
	    	useremail.sendKeys(email);
	    	passwordEle.sendKeys(password);
	    	submit.click();
            ProductCatelog ProductCatelog = new ProductCatelog(driver);
	    	return ProductCatelog;
	    }
	    
	    public String getErrorMessage()
	    {
	    	waitForWebElementToAppear(errorMessage);
	    	return errorMessage.getText();
	    }
		 public void goTo() {
			 
			 driver.get("https://rahulshettyacademy.com/client");
		 }
		
		
		
		

	}


