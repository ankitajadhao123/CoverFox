package coverFoxUsingTestNg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoverFoxHealthPlanPage
{
   @FindBy(xpath = "//div[@class='next-btn']")private WebElement next_button;
	
   public CoverFoxHealthPlanPage(WebDriver driver)
   {
	   PageFactory.initElements(driver, this);
   }
   
   public void click_on_Nextbutton()
   {
	   next_button.click();
   }
}
