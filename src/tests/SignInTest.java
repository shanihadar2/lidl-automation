package tests;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.SignInPage;
import utils.Utils;

public class SignInTest extends BaseTest {

	@Test
	public void tc01_signInWrongDetails() {
		HomePage hp = new HomePage(driver);
		hp.goToSignIn();
		SignInPage sip = new SignInPage(driver);
		sip.closePopUp();
		sip.signIn("aaa@gmail.com", "bbb");
		Assert.assertEquals(sip.countErrorMsgs(), 1);
		Assert.assertEquals(sip.getErrorMsgs().get(0), "credentials are not valid");
	}

	@Test
	public void tc02_signInInvalidMail() {
		SignInPage sip = new SignInPage(driver);
		sip.signIn("abc", "abc");
		List<String> expectedErrorMsgs = new ArrayList<String>();
		expectedErrorMsgs.add("credentials are not valid");
		expectedErrorMsgs.add("enter a valid email address.");
		List<String> actualErrorMsgs = sip.getErrorMsgs();
		Assert.assertEquals(sip.countErrorMsgs(), 2);
		Assert.assertEquals(actualErrorMsgs, expectedErrorMsgs);
	}

	@Test
	public void tc03_signInRightDetails() {
		SignInPage sip = new SignInPage(driver);
		sip.signIn(Utils.readProperty("email"), Utils.readProperty("password"));
		HomePage hp = new HomePage(driver);
		String actualUsername = hp.getUserName();
		Assert.assertEquals(actualUsername, Utils.readProperty("userName"));
	}
}
