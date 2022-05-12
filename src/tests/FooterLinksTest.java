package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.ConditionsPage;

public class FooterLinksTest extends BaseTest {
	
	@Test
	public void tc020_termsOfUse() {
		ConditionsPage cp = new ConditionsPage(driver);
		cp.goToFooterLink("terms of use");
		cp.closePopUp();
		cp = new ConditionsPage(driver);
		String title = cp.getPageTitle();
		Assert.assertTrue((title.toLowerCase().contains("terms of use")));
	}

	@Test
	public void tc021_giftCardTermsAndConditions() {
		ConditionsPage cp = new ConditionsPage(driver);
		cp.goToFooterLink("gift card terms & conditions");
		cp.closePopUp();
		cp = new ConditionsPage(driver);
		String title = cp.getPageTitle();
		Assert.assertTrue((title.toLowerCase().contains("gift card terms and conditions")));
	}
}
