package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage extends BasePage{
	
	
	       //constructor..
	
			public CategoryPage(WebDriver driver) {
		        super(driver);
		    }
			
			// Locators..
			
			@FindBy(css = ".fixed_wrapper .prdocutname") List<WebElement> products;
			
		    // Action methods....
			
			 public int getVisibleProductCount() 
			 {
			    return products.size();
			 }

			 public List<WebElement> getProducts() 
			  {
			        return products;
			  }

			 public void clickProduct(int index) 
			  {
			        products.get(index).click();
			  }
			 
			 
			 
			//Explicit wait....
			 
			 public void waitForProductsToBeVisible() {
			     new WebDriverWait(driver, Duration.ofSeconds(10))
			         .until(ExpectedConditions.visibilityOfAllElements(products));
			 }


}
