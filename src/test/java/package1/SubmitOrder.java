package package1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Package1.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder {

	public static void main(String[] args) {
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().clearDriverCache().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		//create object to Landing Page
		LandingPage landingPage = new LandingPage(driver);
		//login page
		driver.findElement(By.id("userEmail")).sendKeys("Tw@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Tw@12345");
		driver.findElement(By.id("login")).click();
		
		//Find common locator for products and put in into a list
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		
		//To find the element what we want we can use streams
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		//here we don't need to say which product coz above section we identify the 2nd one as prod
		//then we want 2nd ones add to card button details
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		// after adding success message will appear = "Added to cart successfully"
		//so we have to write it it will disappear quickly so we have add explicit wait
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
		
		//have to wait the loading screen disappear to click the cart button otherwise it is disabled
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//Find add to card button
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		//Get list of the cart and if any product match zero coat 3
		List <WebElement>  cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
	    Boolean match=	cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	    Assert.assertTrue(match);
	    driver.findElement(By.cssSelector(".totalRow button")).click();
	    
	    //send values to country textbox using actions without using normal way 
	    Actions a = new Actions (driver);
	    a.sendKeys(driver.findElement(By.cssSelector(".form-group ")), "India").build().perform();
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	    driver.findElement(By.cssSelector(".ta-results button:last-child")).click();
	    driver.findElement(By.cssSelector(".actions a")).click();
	    String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	   
	    
		
		//if (products.size() >= 2) {
		    // 2. Select the SECOND element (index 1 in a 0-based list)
		    //WebElement secondProduct = products.get(1);

		    // 3. Find the "Add To Cart" button within that specific product's scope.
		    // The selector ".card-body button:last-of-type" is usually robust for the Add to Cart button.
		    // Based on the HTML you provided earlier, a more direct selector is:
		    //WebElement addToCartButton = secondProduct.findElement(By.cssSelector("button[style*='float: right']"));
		    
		    // 4. Click the button
		   // addToCartButton.click();
		//} else {
		   // System.out.println("Error: The product list contains fewer than 2 items.");
		//}
		


	}

}

