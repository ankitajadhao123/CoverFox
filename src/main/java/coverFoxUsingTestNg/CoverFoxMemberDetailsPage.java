package coverFoxUsingTestNg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CoverFoxMemberDetailsPage {
	
	@FindBy(id="Age-You")private WebElement age;
	
	@FindBy(xpath = "//div[@class='next-btn']") private WebElement next_button;
	

	
	public CoverFoxMemberDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void select_age(String age1)
	{
		age.click();
		Select s1=new Select(age);
		s1.selectByVisibleText(age1+" years");
	}
	public void next_after_selecting_age()
	{
		next_button.click();
	}
	
	
}
