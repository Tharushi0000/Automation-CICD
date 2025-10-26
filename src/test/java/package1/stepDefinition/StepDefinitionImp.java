package package1.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Package1.pageobjects.CartPage;
import Package1.pageobjects.CheckOutPage;
import Package1.pageobjects.ConfirmationPage;
import Package1.pageobjects.LandingPage;
import Package1.pageobjects.ProductCatelog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import package1.TestComponents.BaseTest;

public class StepDefinitionImp extends BaseTest{
    
	public LandingPage landingPage;
	public ProductCatelog productCatelog;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecomerce Page")
	public  void I_landed_on_Ecomerce_Page() throws IOException
	{
		landingPage= luanchApplication();
	}
	
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void looged_in_username_and_password(String username,String passsword)
	{
		productCatelog  =landingPage.LoginApplication(username,passsword);
		
	}
	@When ("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products=productCatelog.getProductList();
		productCatelog.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName )
	{
        CartPage cartPage= productCatelog.gotoCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
	    confirmationPage=checkOutPage.submitOrder();
	}
	
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable{
		Assert.assertEquals(strArg1,landingPage.getErrorMessage());
	}
}
