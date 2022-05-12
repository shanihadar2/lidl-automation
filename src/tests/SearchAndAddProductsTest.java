package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import data.DP;
import pageobjects.HomePage;
import pageobjects.MyGroceryListsPage;
import pageobjects.ProductDetailsPage;
import pageobjects.ProductsPage;
import pageobjects.SignInPage;
import utils.Utils;

public class SearchAndAddProductsTest extends BaseTest {

	@Test
	public void tc11_AddProductFromSearch() {
		HomePage hp = new HomePage(driver);
		hp.goToSignIn();
		SignInPage sip = new SignInPage(driver);
		sip.closePopUp();
		sip.signIn(Utils.readProperty("email"), Utils.readProperty("password"));
		hp = new HomePage(driver);
		hp.searchProducts("dozen roses");
		ProductsPage pp = new ProductsPage(driver);
		pp.goToProductDetailPage("dozen roses");
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		pdp.addProductToList("list1");
		pdp.goToMyGroceryLists();
		MyGroceryListsPage glp = new MyGroceryListsPage(driver);
		glp.goToList("list1");
		int quantity = glp.getProductQuantityOnList("dozen roses");
		int totalRows = glp.getTotalRowsInList("list1");
		Assert.assertEquals(quantity, 1);
		Assert.assertEquals(totalRows, 1);
	}

	@Test(dataProvider = "getDataFromExcel", dataProviderClass = DP.class)
	public void tc12_tc13_tc14_addNewRegularProduct(String categoryName, String subCategoryName, String productName,
			String listName) {
		HomePage hp = new HomePage(driver);
		hp.goToSignIn();
		SignInPage sip = new SignInPage(driver);
		sip.closePopUp();
		sip.signIn(Utils.readProperty("email"), Utils.readProperty("password"));
		hp = new HomePage(driver);
		hp.goToMyGroceryLists();
		MyGroceryListsPage glp = new MyGroceryListsPage(driver);
		int totalRowsBefore = glp.getTotalRowsInList(listName);
		glp.goToCategory(categoryName);
		ProductsPage pp = new ProductsPage(driver);
		pp.goToSubCategory(subCategoryName);
		pp = new ProductsPage(driver);
		pp.goToProductDetailPage(productName);
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		pdp.addProductToList(listName);
		pdp = new ProductDetailsPage(driver);
		pdp.goToMyGroceryLists();
		glp = new MyGroceryListsPage(driver);
		int totalRowsAfter = glp.getTotalRowsInList(listName);
		glp.goToList(listName);
		glp = new MyGroceryListsPage(driver);
		int productQuantityAfter = glp.getProductQuantityOnList(productName);
		Assert.assertEquals(productQuantityAfter, 1);
		Assert.assertEquals(totalRowsAfter, totalRowsBefore + 1);
	}

	@Test
	public void tc15_addAlreadyExistProduct() {
		HomePage hp = new HomePage(driver);
		hp.goToSignIn();
		SignInPage sip = new SignInPage(driver);
		sip.closePopUp();
		sip.signIn(Utils.readProperty("email"), Utils.readProperty("password"));
		hp = new HomePage(driver);
		hp.goToMyGroceryLists();
		MyGroceryListsPage glp = new MyGroceryListsPage(driver);
		glp.goToList("list1");
		glp = new MyGroceryListsPage(driver);
		int productQuantityBefore = glp.getProductQuantityOnList("dozen roses");
		int totalRowsBefore = glp.getTotalRowsInList("list1");
		glp.goToCategory("floral");
		ProductsPage pp = new ProductsPage(driver);
		pp.goToProductDetailPage("dozen roses");
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		pdp.addProductToList("list1");
		pdp = new ProductDetailsPage(driver);
		pdp.goToMyGroceryLists();
		glp = new MyGroceryListsPage(driver);
		int totalRowsAfter = glp.getTotalRowsInList("list1");
		glp.goToList("list1");
		int productQuantityAfter = glp.getProductQuantityOnList("dozen roses");
		Assert.assertEquals(productQuantityAfter, productQuantityBefore + 1);
		Assert.assertEquals(totalRowsAfter, totalRowsBefore);
	}

	@Test
	public void tc16_addMultipleTypesProduct() {
		// Add one type, the other type is not on the list
		HomePage hp = new HomePage(driver);
		hp.goToSignIn();
		SignInPage sip = new SignInPage(driver);
		sip.closePopUp();
		sip.signIn(Utils.readProperty("email"), Utils.readProperty("password"));
		hp = new HomePage(driver);
		hp.goToCategory("pet shop");
		ProductsPage pp = new ProductsPage(driver);
		pp.goToSubCategory("cat food");
		pp = new ProductsPage(driver);
		pp.goToProductDetailPage("premium mousse wet cat food, chicken");
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		pdp.chooseType("premium mousse wet cat food, salmon");
		pdp = new ProductDetailsPage(driver);
		pdp.addProductToList("list1");
		pdp.goToMyGroceryLists();
		MyGroceryListsPage glp = new MyGroceryListsPage(driver);
		glp.goToList("list1");
		int quantitySalmon = glp.getProductQuantityOnList("premium mousse wet cat food, salmon");
		int quantityChicken = glp.getProductQuantityOnList("premium mousse wet cat food, chicken");
		Assert.assertEquals(quantitySalmon, 1);
		Assert.assertEquals(quantityChicken, 0);
	}

	@Test
	public void tc17_addMultipleTypesProduct() {
		// Add one type when the other type is already on the list
		HomePage hp = new HomePage(driver);
		hp.goToSignIn();
		SignInPage sip = new SignInPage(driver);
		sip.closePopUp();
		sip.signIn(Utils.readProperty("email"), Utils.readProperty("password"));
		hp = new HomePage(driver);
		hp.goToCategory("pet shop");
		ProductsPage pp = new ProductsPage(driver);
		pp.goToSubCategory("cat food");
		pp = new ProductsPage(driver);
		pp.goToProductDetailPage("premium mousse wet cat food, chicken");
		ProductDetailsPage pdp = new ProductDetailsPage(driver);
		pdp.chooseType("premium mousse wet cat food, chicken");
		pdp = new ProductDetailsPage(driver);
		pdp.addProductToList("list1");
		pdp.goToMyGroceryLists();
		MyGroceryListsPage glp = new MyGroceryListsPage(driver);
		glp.goToList("list1");
		int quantitySalmon = glp.getProductQuantityOnList("premium mousse wet cat food, salmon");
		int quantityChicken = glp.getProductQuantityOnList("premium mousse wet cat food, chicken");
		Assert.assertEquals(quantitySalmon, 1);
		Assert.assertEquals(quantityChicken, 1);
	}
}
