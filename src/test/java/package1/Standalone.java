package package1;

import java.io.File;
import java.io.IOException;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Package1.pageobjects.CartPage;
import Package1.pageobjects.CheckOutPage;
import Package1.pageobjects.ConfirmationPage;
import Package1.pageobjects.LandingPage;
import Package1.pageobjects.OrderPage;
import Package1.pageobjects.ProductCatelog;
import io.github.bonigarcia.wdm.WebDriverManager;
import package1.TestComponents.BaseTest;
import org.openqa.selenium.TakesScreenshot;

public class Standalone extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		ProductCatelog productCatelog =landingPage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=productCatelog.getProductList();
		productCatelog.addProductToCart(input.get("products"));
		CartPage cartPage= productCatelog.gotoCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay(input.get("products"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
	    String confirmMessage = confirmationPage.getConfirmationMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	 
	}
	
	//To verify zara coat 3 is displaying in orders page
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatelog productCatelog =landingPage.LoginApplication("Tw@gmail.com", "Tw@12345");
		OrderPage ordersPage=productCatelog.gotoOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
	}
	
	
	//get screenshots
	
	//Extent Reports
	
	
	
	
	// if you want to run the login page with 2 different data sets use DataProvider annotation
	@DataProvider
	public Object[][] getData() throws IOException
	{
	
		
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//package1\\data\\PurchaseOrder.js");
		return new Object[] [] {{data.get(0)}, {data.get(1)}};
	}
	
	
	
	

	//HashMap<String,String> map = new HashMap<String,String>();
	//map.put("email", "Tw@gmail.com");
	//map.put("password", "Tw@12345");
	//map.put("product", "ZARA COAT 3");
	
	//HashMap<String,String> map1 = new HashMap<String,String>();
	//map1.put("email", "anshika@gmail.com");
	//map1.put("password", "Iamking@00");
	//map1.put("product", "ADIDAS ORIGINAL");
}
