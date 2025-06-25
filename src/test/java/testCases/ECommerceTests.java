package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import pageObjects.CartPage;
import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class ECommerceTests extends BaseClass{
	
	public ExtentTest test;
	
	
	HomePage homePage;
    CategoryPage categoryPage;
    ProductPage productPage;
    CartPage cartPage;
    RegistrationPage registrationPage;
    
    @Test(priority = 1)
    public void verifyHomeAndCategory() 
    {
        homePage = new HomePage(driver);
        List<WebElement> categories = homePage.getAllMainCategories();
        
        System.out.println("Main Categories:");
        
        for (WebElement category : categories) {
            System.out.println("- " + category.getText().trim());
        }
        
        Assert.assertTrue(categories.size() > 0, "No categories found on homepage");

        homePage.clickRandomCategory();
        
        categoryPage = new CategoryPage(driver);
        
        int productCount = categoryPage.getVisibleProductCount();
        Assert.assertTrue(productCount >= 3, "Not enough products in selected category");
    }
	
    
    
    
    @Test(priority = 2)
    public void selectAndAddProductsToCart() {
    	
    	test = extent.createTest("selectAndAddProductsToCart");

        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            // Re-initialize the category page before selecting
            categoryPage = new CategoryPage(driver);

            // Wait for products to appears...
            
            categoryPage.waitForProductsToBeVisible();

            List<WebElement> products = categoryPage.getProducts();

            if (products == null || products.isEmpty()) {
                Assert.fail("No products found in category to select.");
                return;
            }

            int index = random.nextInt(products.size());
            categoryPage.clickProduct(index);


            productPage = new ProductPage(driver);

            System.out.println("Product " + (i + 1) + " Name: " + productPage.getProductName());
            System.out.println("Product " + (i + 1) + " Price: " + productPage.getProductPrice());
            System.out.println("Product " + (i + 1) + " URL: " + productPage.getProductURL());

            if (productPage.isAddToCartAvailable()) 
            {
                productPage.clickAddToCart();
            }
            else 
            {
            	String msg = "Product " + (i + 1) + " is out of stock or missing Add to Cart button.";
                
                test.log(Status.WARNING, msg); // Extent Report
                logToFile(msg);                // report.txt file
                System.out.println(msg);       // Console
            }

            // Go back twice: from cart popup → product page → category page
            
            driver.navigate().back(); // back to product page
            driver.navigate().back(); // back to category page

            // Wait for products to load again
            
            categoryPage.waitForProductsToBeVisible();

            
        }
    }
    
    @Test(priority = 3)
    public void verifyCartAndCheckout() {
    	
        // Navigating to cart page..
    	
    	homePage = new HomePage(driver);
    	homePage.clickCartIcon();     


        cartPage = new CartPage(driver);

        // Assert cart table is visible
        Assert.assertTrue(cartPage.isCartTableDisplayed(), "Cart table is not visible");

        // Proceed to checkout
        cartPage.clickCheckout();
    }

    
    /*
    @Test(priority = 3)
    public void verifyCartAndCheckout() {
    	
    	homePage = new HomePage(driver);
    	homePage.clickCartIcon();
    	
        cartPage = new CartPage(driver);

        // Ensure cart table is displayed
        Assert.assertTrue(cartPage.isCartTableDisplayed(), "Cart table is not visible");

        // Locate all rows in cart (excluding header)
        List<WebElement> items = driver.findElements(By.cssSelector("table.table-striped tbody tr"));
        Assert.assertEquals(items.size(), 2, "Expected 2 items in the cart");

        double expectedTotal = 0.0;

        for (WebElement item : items) {
            // Get product price and quantity for each row
            String priceText = item.findElement(By.cssSelector("td:nth-child(6)")).getText(); // Unit price
            String totalText = item.findElement(By.cssSelector("td:nth-child(7)")).getText(); // Total per item

            // Remove currency symbols and commas
            double unitPrice = Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));
            double itemTotal = Double.parseDouble(totalText.replaceAll("[^\\d.]", ""));

            // Calculate quantity (itemTotal / unitPrice)
            int quantity = (int)(itemTotal / unitPrice);

            System.out.println("Item: Unit Price = " + unitPrice + ", Qty = " + quantity + ", Total = " + itemTotal);

            // Verify total = price * quantity
            Assert.assertEquals(itemTotal, unitPrice * quantity, 0.01, "Mismatch in item total calculation");

            expectedTotal += itemTotal;
        }

        // Verify cart total displayed at bottom
        String displayedTotal = driver.findElement(By.cssSelector("div.row.total-wrapper .pull-right strong")).getText();
        double cartTotal = Double.parseDouble(displayedTotal.replaceAll("[^\\d.]", ""));

        System.out.println("Expected Total: " + expectedTotal + ", Displayed Total: " + cartTotal);

        Assert.assertEquals(cartTotal, expectedTotal, 0.01, "Cart total mismatch");

        // Proceed to checkout
        cartPage.clickCheckout();
    }
    
    */
    
    @Test(priority = 4, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void registrationNegativeScenario(String fname, String lname, String email, String pass) {
    	
    	test = extent.createTest("Registration Negative Scenario - ");

    	
    	registrationPage = new RegistrationPage(driver);
        try {
            // Open registration page
            registrationPage.submitForm(); 
            
            // Fill form with data (e.g., leave one required field blank)
            registrationPage.fillRegistrationForm(fname, lname, email, pass);
            
            // Submit form
            registrationPage.submitForm();
            
            // Check if error is displayed
            boolean isError = registrationPage.isErrorDisplayed();
            
            if (isError) {
                // Take screenshot when error is visible
                String screenshotPath = captureScreen("ValidationError");
                
                String msg = "Validation failed as expected for: " + fname + ", " + email;
                
                logToFile(msg+" ,"+"Screenshot saved at: " + screenshotPath); //report.txt File
                test.log(Status.WARNING, msg); //extent report
                
                System.out.println("Validation error displayed, screenshot saved at: " + screenshotPath); //console..
            }
            else {
                String msg = "Expected error NOT shown for: " + fname + ", " + email;
                logToFile(msg);
                test.log(Status.FAIL, msg);
                Assert.fail("Validation error was expected but not shown.");
            } 
            
        } catch (Exception e)  {
            // If exception occurs, take screenshot and fail the test
            try {
                captureScreen("Registration_Exception");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Assert.fail("Registration test failed due to exception: " + e.getMessage());
        }
    }


}
