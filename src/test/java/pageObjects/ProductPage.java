package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage{
	
	//Constructor..
	
	public ProductPage(WebDriver driver) 
	{
        super(driver);
    }

	
	//Locators...
	
	@FindBy(css = "h1.productname")
	 WebElement productName;

	@FindBy(css = ".productfilneprice")
	 WebElement price;

	@FindBy(css = ".cart")
	 WebElement addToCart;

	//Action methods...
	
	public String getProductName() {
		
		new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOf(productName));
		
        return productName.getText();
    }

    public String getProductPrice() {
        return price.getText();
    }

    public String getProductURL() {
        return driver.getCurrentUrl();
    }

    
    public boolean isAddToCartAvailable() {
        try {
            return addToCart.isDisplayed() && addToCart.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    

    public void clickAddToCart() {
        addToCart.click();
    }

}
