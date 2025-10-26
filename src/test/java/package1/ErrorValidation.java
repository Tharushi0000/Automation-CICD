package package1;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.Test;



import Package1.pageobjects.CartPage;
import Package1.pageobjects.ProductCatelog;
import package1.TestComponents.BaseTest;
import package1.TestComponents.Retry;

public class ErrorValidation extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer =Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		
	
	    landingPage.LoginApplication("Tw@gmail.com", "Tw@12345");
		
		//When we give wrong credintials
		Assert.assertEquals("Incorrect email or password",landingPage.getErrorMessage());
			
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
	String productName = "ZARA COAT 3";
		
		ProductCatelog productCatelog =landingPage.LoginApplication("Tw@gmail.com", "Tw@12345");
		List<WebElement> products=productCatelog.getProductList();
		productCatelog.addProductToCart(productName);
		CartPage cartPage= productCatelog.gotoCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	 
	}

}
