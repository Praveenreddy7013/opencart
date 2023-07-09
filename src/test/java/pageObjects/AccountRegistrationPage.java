package pageObjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountRegistrationPage {
	
WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-firstname")
	WebElement firstName;
	
	@FindBy(id="input-lastname")
	WebElement lastName;
	
	@FindBy(id="input-email")
	WebElement email;
	
	@FindBy(id="input-password")
	WebElement password;
	
	@FindBy(xpath="//button[.=\"Continue\"]//preceding::input[1]")
	WebElement Checkbox;
	
	@FindBy(xpath="//button[.=\"Continue\"]")
	WebElement ContinueBtn;
	
	@FindBy(xpath="//button[.=\"registartion got successful\"]")
	WebElement ConfirmationMsg;
	
	
	public void setFirstname(String fname) {
		
		firstName.sendKeys(fname);
	}
	
     public void setLastname(String lname) {
		
		lastName.sendKeys(lname);
	}
    
    public void setGmail(String mail) {
		
		email.sendKeys(mail);
	}
    public void setPassword(String password) {
		
		email.sendKeys(password);
	}
   public void selectCheckbox() {
		
		Checkbox.click();;
	}
   public void clickOnContinue() {
	   ContinueBtn.click();
   }
   
  public boolean ConfirmationMsg() {
	  try {
		  
	  return ConfirmationMsg.isDisplayed();
	  }
		  catch(Exception e) {
				return (false);
			}
	  }
    
	
	

}
