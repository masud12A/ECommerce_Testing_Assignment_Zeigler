package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {
	
	//Constructor..
	
	public RegistrationPage(WebDriver driver) 
	{
        super(driver);
    }
	
	//Locators..
	
	@FindBy(id = "AccountFrm_firstname")
	 WebElement firstName;

	@FindBy(id = "AccountFrm_lastname")
	 WebElement lastName;

	@FindBy(id = "AccountFrm_email")
	 WebElement email;

	@FindBy(id = "AccountFrm_password")
	 WebElement password;

	@FindBy(css = "button[title='Continue']")
	 WebElement continueBtn;

	@FindBy(css = ".alert.alert-error")
	 List<WebElement> errorMsg;

	//Action Methods..
	
	
	 //Fills the registration form with the given data
	 
	public void fillRegistrationForm(String fname, String lname, String mail, String pass)
	{
	    firstName.sendKeys(fname);
	    lastName.sendKeys(lname);
	    email.sendKeys(mail);
	    password.sendKeys(pass);
	}

	
	 //Submits the registration form
	 
	public void submitForm() 
	{
	    continueBtn.click();
	}

	
	 // Checks if any error message is displayed
	 
	public boolean isErrorDisplayed() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        return wait.until(ExpectedConditions.visibilityOfAllElements(errorMsg)).size() > 0;
	    } catch (Exception e) {
	        return false;
	    }
	}


	

}
