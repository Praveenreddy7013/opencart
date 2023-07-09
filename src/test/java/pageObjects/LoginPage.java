package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-email")
	WebElement username;
	
	@FindBy(id="input-password")
	WebElement password;
	
	@FindBy(xpath="//button[.=\"Login\"]")
	WebElement Loginbtn;
	
	@FindBy(xpath="//span[normalize-space()='My Accou']")
	WebElement msgHeading;
	
	public void setUsername(String uname) {
		username.sendKeys(uname);
	}
	
	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickLogin() {
		Loginbtn.click();
	}
	
	public boolean isMyAccountPageExists() {
		try {
	
		return msgHeading.isDisplayed();
		}
		catch(Exception e) {
			return (false);
		}
	}
	
}
