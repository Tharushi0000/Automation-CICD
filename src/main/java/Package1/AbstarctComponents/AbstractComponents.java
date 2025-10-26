package Package1.AbstarctComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Package1.pageobjects.CartPage;
import Package1.pageobjects.OrderPage;

public class AbstractComponents {

	
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy (xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy (css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	

	public void waitForElementToAppear(By findBy)
	{
		//wait element to appear
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		//wait element to appear
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void waitForSpinnerToDisappear() {
	    // Locator for the spinner/overlay (you might need to adjust this locator)
	    By spinnerLocator = By.cssSelector(".ngx-spinner-overlay");
	    
	    // Wait up to, say, 10 seconds for the element to become invisible
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    // This command waits until the spinner element is no longer visible in the DOM
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
	}
	
	
	public CartPage gotoCartPage()
	{
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	    
	}
	
	
	public OrderPage gotoOrdersPage()
	{
		orderHeader.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
}
