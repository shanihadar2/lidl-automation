package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import data.DP;
import pageobjects.FindStorePage;
import pageobjects.HomePage;

public class SearchStoreTest extends BaseTest {

	@Test(dataProvider = "getDataFromExcel", dataProviderClass = DP.class)
	public void tc24_tc25_searchStore(String cityOrZipText, String expectedResults, String expectedMessage) {
		HomePage hp = new HomePage(driver);
		hp.goToFindStore();
		FindStorePage fsp = new FindStorePage(driver);
		fsp.closePopUp();
		fsp.searchForStore(cityOrZipText);
		fsp = new FindStorePage(driver);
		int actualNumOfResults = fsp.getNumOfResults();
		int expectedNumOfResults = Integer.parseInt(expectedResults);
		String actualMsg = fsp.getMsg();

		Assert.assertEquals(actualNumOfResults, expectedNumOfResults);
		Assert.assertEquals(actualMsg, expectedMessage);
	}
}
