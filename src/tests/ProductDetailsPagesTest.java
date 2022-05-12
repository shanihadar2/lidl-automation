package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.DP;
import pageobjects.HomePage;
import pageobjects.ProductDetailsPage;
import pageobjects.ProductsPage;

public class ProductDetailsPagesTest extends BaseTest {

	@Test(dataProvider = "getDataFromExcel", dataProviderClass = DP.class)
	public void tc08_tc09_tc10_productDetails(String categoryName, String subCategoryName, String productName,
			String section) {
		HomePage hp = new HomePage(driver);
		hp.goToCategory(categoryName);
		ProductsPage pp = new ProductsPage(driver);
		pp.closePopUp();
		pp.goToSubCategory(subCategoryName);
		pp = new ProductsPage(driver);
		String price1 = pp.getProductPrice(productName);
		pp.goToProductDetailPage(productName);
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		String name2 = pdp.getProductName().toLowerCase();
		String price2 = pdp.getprice();
		String categoryName2 = pdp.getSectionName().toLowerCase();
		Assert.assertEquals(price2, price1);
		Assert.assertEquals(name2, productName);
		Assert.assertEquals(categoryName2, section);
	}
}
