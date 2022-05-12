package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.DP;
import pageobjects.HomePage;
import pageobjects.ProductsPage;

public class CategoriesTest extends BaseTest {

	@Test(dataProvider = "getDataFromExcel", dataProviderClass = DP.class)
	public void tc04_tc05_tc06_openCategory(String categoryName, String numOfSubCategories) {
		HomePage hp = new HomePage(driver);
		hp.goToCategory(categoryName);
		hp.closePopUp();
		ProductsPage pp = new ProductsPage(driver);
		int intNumOfSubCategories = Integer.parseInt(numOfSubCategories);
		Assert.assertEquals(pp.getNumOfSubCategories(), intNumOfSubCategories);
		Assert.assertEquals(pp.getSubCategoryName(1), "all " + categoryName);
	}

	@Test
	public void tc07_toatlProductsInSubCategory() {
		HomePage hp = new HomePage(driver);
		hp.closePopUp();
		hp.goToCategory("bakery");
		ProductsPage pp = new ProductsPage(driver);
		hp.closePopUp();
		pp.goToSubCategory("bread");
		pp = new ProductsPage(driver);
		pp.loadAllProducts();
		Assert.assertEquals(pp.getNumOfProductsOnPage(), pp.getNumOfProductsOnSubCategory("bread"));
	}
}
