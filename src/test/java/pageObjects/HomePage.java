package pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	//constructor..
	
		public HomePage(WebDriver driver) {
	        super(driver);
	    }
		
    // Locators..
		
		@FindBy(css = "#categorymenu > nav > ul > li > a")List<WebElement> categoryList;
		
		@FindBy(css = ".cart_total")
		WebElement cartIcon;
		
	// Action methods..
		
		public List<WebElement> getAllMainCategories() 
		{
		    return categoryList;
		}
		
		
		public void clickRandomCategory()
		{
	        List<WebElement> categories = getAllMainCategories();
	        Random rand = new Random();
	        int randomIndex = rand.nextInt(categories.size());
	        categories.get(randomIndex).click();
	    }
		
		
		

		public void clickCartIcon() {
		    cartIcon.click();
		}



}
