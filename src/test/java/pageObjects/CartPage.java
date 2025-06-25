package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
	
	//Constructor...
	
	public CartPage(WebDriver driver) 
	{
        super(driver);
    }
	
	//locators..
	
	@FindBy(css = ".table-striped")
	 WebElement cartTable;

	@FindBy(css = ".pull-right [title='Checkout']")
	 WebElement checkoutBtn;

	//Action Methods..
	
	public boolean isCartTableDisplayed() {
	    return cartTable.isDisplayed();
	}

	
	public void clickCheckout() {
	    checkoutBtn.click();
	}
	
}
