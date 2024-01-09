package coverFoxTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xdgf.util.Util;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import coverFoxBase.Base;
import coverFoxUsingTestNg.CoverFoxAddressDetailsPage;
import coverFoxUsingTestNg.CoverFoxHealthPlanPage;
import coverFoxUsingTestNg.CoverFoxHealthPlanResultsPage;
import coverFoxUsingTestNg.CoverFoxHomePage;
import coverFoxUsingTestNg.CoverFoxMemberDetailsPage;
import coverFoxUtility.Utility;

public class CF_TC555_Validate_search_results_for_healthcare_policies extends Base {
	
	CoverFoxHomePage home;
	CoverFoxHealthPlanPage healthPlan;
	CoverFoxAddressDetailsPage addressDetails;
	CoverFoxMemberDetailsPage memberDetails;
	CoverFoxHealthPlanResultsPage result;
	
	@BeforeClass
	public void luanchBrowser() throws InterruptedException
	{
		launchCoverFox();
		home=new CoverFoxHomePage(driver);
		healthPlan=new CoverFoxHealthPlanPage(driver);
		addressDetails=new CoverFoxAddressDetailsPage(driver);
		memberDetails=new CoverFoxMemberDetailsPage(driver);
		result=new CoverFoxHealthPlanResultsPage(driver);
	}
	@BeforeMethod()
	public  void enterMemberDetails() throws InterruptedException, EncryptedDocumentException, IOException
	{
		Reporter.log("clicking on gender button ", true);
		home.clickoncheckbox();
		Thread.sleep(1000);
		
		Reporter.log("Clicking on next button",true);
		healthPlan.click_on_Nextbutton();
		Thread.sleep(1000);
		
		Reporter.log("Handeling age drop down ", true);
		memberDetails.select_age(Utility.readDataFromExcel(0, 0));
		//memberDetails.select_age(Utility.readDataFromProperty("age"));
		Reporter.log("Clicking on next button ", true);
		memberDetails.next_after_selecting_age();
		Thread.sleep(1000);
		
		Reporter.log("Entering pin code ",true);
		addressDetails.enter_pincode(Utility.readDataFromExcel(0, 1));
		//addressDetails.enter_pincode(Utility.readDataFromProperty("pincode"));
		Reporter.log("Entering mobile num ",true);
		addressDetails.enter_mobile(Utility.readDataFromExcel(0, 2));
		//addressDetails.enter_mobile(Utility.readDataFromProperty("MobNum"));
		Reporter.log("Clicking on Next button",true);
		addressDetails.click_continueButton();
		Thread.sleep(1000);
		
	}
	@Test
	public void validateTestPlansFromTextAndBanners() throws InterruptedException, IOException
	{
		Thread.sleep(1000);
		Reporter.log("Fetching number of results from text ", true);
		Thread.sleep(7000);
		int textResult = result.search_result();
		
		Reporter.log("Fetching number of results from Banners ", true);
		int searchResult = result.availablePlanNumberFromBanners();
		Thread.sleep(1000);
		Assert.assertEquals(textResult, searchResult,"Result not match");
		Reporter.log("TC is passed",true);
		
		//Take Screenshot
		Utility.takeScreenshot(driver, "TC555");
	}
	@Test(enabled=false)
	public void validateTestPlansFromTextBannersIfNotMatch() throws InterruptedException, IOException
	{
		Thread.sleep(1000);
		Reporter.log("Fetching number of results from text ", true);
		Thread.sleep(7000);
		int textResult = result.search_result();
		Reporter.log("Fetching number of results from Banners ", true);
		int searchResult = result.availablePlanNumberFromBanners();
		Thread.sleep(7000);
		Utility.takeScreenshot(driver, "TC555");
		
		Assert.assertNotEquals(textResult, searchResult,"Result are  matching");
		Reporter.log("TC is passed",true);
		
	}
	

	
	@AfterMethod
	public void closeBrowser() throws InterruptedException
	{
		
	    Thread.sleep(1000);
	    closeCoverFox();
	}
	
	
}
