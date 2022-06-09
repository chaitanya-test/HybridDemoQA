package TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

public class firstBasic extends BaseClass.Base 
{
	@Test(priority =0)
	public void verifyUrl() throws InterruptedException
	{
		String expectedURL = "https://demoqa.com/elements";
		Thread.sleep(4000);
		clickit("elements_link");
		System.out.print("Problem");
		String actualURL = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualURL, expectedURL);
	}

	@Test(priority =1)
	public void navigateToTextPage()
	{
		String expectedURL = "https://demoqa.com/text-box";
		clickit("textbox_link");
		String actualURL = driver.getCurrentUrl();
		AssertJUnit.assertEquals(expectedURL, actualURL);
	}

	@Test(priority =2)
	public void verifyTestBoxfunctionality()
	{
		sendit("fullName","Chaitanya Sonawane");
		sendit("emailId","sonawane92@gmail.com");
		sendit("currentAddress","Dhankawadi");
		sendit("permanentAddress","Pune");
		clickit("submitbtn");
	}
}
