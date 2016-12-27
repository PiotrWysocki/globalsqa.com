package multiformpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormInterestsPage {
	
	WebDriver driver;
	
	// Find the xbox radio button
	@FindBy(xpath=".//input[contains(@value, 'xbox')]")
	WebElement xbox;
	
	// Find the playstation radio button
	@FindBy(xpath=".//div[2]/label")
	WebElement playstation;
	
	// Find the "Next Section" button
	@FindBy(xpath=".//form//a[contains(@href, '#/form/payment')]")
	WebElement nextSection;
	
	public FormInterestsPage(WebDriver driver){
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	// Click "xbox" radio button
	public void clickXbox(){
		xbox.click();
	}
	
	// Click "playstation" radio button
	public void clickPlaystation(){
		playstation.click();
	}
	
	// Click "Next Section" button
	public void clickNextSection(){
		nextSection.click();
	}
	
	// Get WebElement nextSection
	public WebElement getNextSection(){
		return nextSection;
	}

}
