package coverFoxTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import coverFoxBase.Base;
import coverFoxUsingTestNg.CoverFoxAddressDetailsPage;
import coverFoxUsingTestNg.CoverFoxHealthPlanPage;
import coverFoxUsingTestNg.CoverFoxHomePage;
import coverFoxUsingTestNg.CoverFoxMemberDetailsPage;
import coverFoxUtility.Utility;

public class CF_TC555_Validate_Error_Msg_for_healthcare_policies extends Base
{
	
	CoverFoxHomePage home;
	CoverFoxHealthPlanPage healthPlan;
	CoverFoxAddressDetailsPage addressDetails;
	CoverFoxMemberDetailsPage memberDetails;
	
	
	@BeforeClass
	public void luanchBrowser() throws InterruptedException
	{
		launchCoverFox();
		home=new CoverFoxHomePage(driver);
		healthPlan=new CoverFoxHealthPlanPage(driver);
		addressDetails=new CoverFoxAddressDetailsPage(driver);
		memberDetails=new CoverFoxMemberDetailsPage(driver);
		
		
	}
	
    @BeforeMethod
    public void enterMemberDetails()throws InterruptedException, IOException {
	  Thread.sleep(1000);
	  home.clickoncheckbox();
	  Thread.sleep(1000);
	  healthPlan.click_on_Nextbutton();
	  Thread.sleep(1000);
	  memberDetails.select_age(Utility.readDataFromProperty("age"));
	  memberDetails.next_after_selecting_age();
	  Thread.sleep(1000);
	  addressDetails.enter_pincode("");
	  addressDetails.enter_mobile(Utility.readDataFromProperty("MobNum"));
	  addressDetails.click_continueButton();
  }
  @Test
  public void validatePincodeErrorMsg() throws InterruptedException, IOException
  {
	  Thread.sleep(1000);
	  //String actualerrorpincodemsg = addressDetails.getErrorMsg();
	  //String expectederrormsg="Please enter a valid pin1";
	  //SoftAssert s=new SoftAssert();
	  //s.assertEquals(actualerrorpincodemsg, expectederrormsg,"Error msg not match,TC is failed");
	  Thread.sleep(1000);
	  Assert.assertTrue(addressDetails.errorMsg()," Error msg didnot display");
	  Utility.takeScreenshot(driver, "TC555_Error");
	  //s.assertAll();
	  
  }

  @AfterMethod
  public void closeBrowser() throws InterruptedException
	{
		
	    Thread.sleep(1000);
	    closeCoverFox();
	}
}
