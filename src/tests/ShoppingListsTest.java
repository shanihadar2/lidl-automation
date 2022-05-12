package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.EditListPage;
import pageobjects.HomePage;
import pageobjects.MyGroceryListsPage;
import pageobjects.SignInPage;
import utils.Utils;

public class ShoppingListsTest extends BaseTest {

	@Test
	public void tc18_updateQuantity() {
		HomePage hp = new HomePage(driver);
		hp.goToSignIn();
		SignInPage sip = new SignInPage(driver);
		sip.closePopUp();
		sip.signIn(Utils.readProperty("email"), Utils.readProperty("password"));
		hp = new HomePage(driver);
		hp.goToMyGroceryLists();
		MyGroceryListsPage glp = new MyGroceryListsPage(driver);
		glp.goToList("list1");
		int totalRowsBefore = glp.getTotalRowsInList("list1");
		glp.updateQuantity("dozen roses", "3");
		glp = new MyGroceryListsPage(driver);
		int totalRowsAfter = glp.getTotalRowsInList("list1");
		int productQuantityAfter = glp.getProductQuantityOnList("dozen roses");

		Assert.assertEquals(totalRowsAfter, totalRowsBefore);
		Assert.assertEquals(productQuantityAfter, 3);
	}

	@Test
	public void tc19_updateIncorrectQuantity() {
		HomePage hp = new HomePage(driver);
		hp.goToSignIn();
		SignInPage sip = new SignInPage(driver);
		sip.closePopUp();
		sip.signIn(Utils.readProperty("email"), Utils.readProperty("password"));
		hp = new HomePage(driver);
		hp.goToMyGroceryLists();
		MyGroceryListsPage glp = new MyGroceryListsPage(driver);
		glp.goToList("list1");
		int totalRowsBefore = glp.getTotalRowsInList("list1");
		glp.updateQuantity("dozen roses", "0");
		glp = new MyGroceryListsPage(driver);
		String warningMsg = glp.getWarningMsg();
		int totalRowsAfter = glp.getTotalRowsInList("list1");
		int productQuantityAfter = glp.getProductQuantityOnList("dozen roses");

		Assert.assertEquals(warningMsg, "Oops! You must select an amount from 1-99.");
		Assert.assertEquals(totalRowsAfter, totalRowsBefore);
		Assert.assertEquals(productQuantityAfter, 0);
	}

	@Test
	public void tc20_deleteProduct() {
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
		int totalRowsBefore = glp.getTotalRowsInList("list1");
		glp.goToEditList();
		EditListPage elp = new EditListPage(driver);
		elp.deleteProductLine("premium mousse wet cat food, chicken");
		glp = new MyGroceryListsPage(driver);
		int totalRowsAfter = glp.getTotalRowsInList("list1");
		int productQuantity = glp.getProductQuantityOnList("premium mousse wet cat food, chicken");

		Assert.assertEquals(totalRowsAfter, totalRowsBefore - 1);
		Assert.assertEquals(productQuantity, 0);
	}

	@Test
	public void tc21_deleteAllProducts() {
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
		glp.goToEditList();
		EditListPage elp = new EditListPage(driver);
		elp.deleteAllProducts();
		glp = new MyGroceryListsPage(driver);
		int totalRows = glp.getTotalRowsInList("list1");

		Assert.assertEquals(totalRows, 0);
	}

	@Test
	public void tc22_deleteList() {
		HomePage hp = new HomePage(driver);
		hp.goToSignIn();
		SignInPage sip = new SignInPage(driver);
		sip.closePopUp();
		sip.signIn(Utils.readProperty("email"), Utils.readProperty("password"));
		hp = new HomePage(driver);
		hp.goToMyGroceryLists();
		MyGroceryListsPage glp = new MyGroceryListsPage(driver);
		int numOfShoppingListsBefore = glp.getNumOfShoppingLists();
		glp.goToList("list1");
		glp = new MyGroceryListsPage(driver);
		glp.goToEditList();
		EditListPage elp = new EditListPage(driver);
		elp.deleteList();
		glp = new MyGroceryListsPage(driver);
		int numOfShoppingListsAfter = glp.getNumOfShoppingLists();
		boolean isListExist = glp.isExist("list1");

		Assert.assertEquals(numOfShoppingListsAfter, numOfShoppingListsBefore - 1);
		Assert.assertFalse(isListExist);
	}

	@Test
	public void tc23_addNewList() {
		HomePage hp = new HomePage(driver);
		hp.goToSignIn();
		SignInPage sip = new SignInPage(driver);
		sip.closePopUp();
		sip.signIn(Utils.readProperty("email"), Utils.readProperty("password"));
		hp = new HomePage(driver);
		hp.goToMyGroceryLists();
		MyGroceryListsPage glp = new MyGroceryListsPage(driver);
		int numOfShoppingListsBefore = glp.getNumOfShoppingLists();
		glp.addNewList("list1");
		glp = new MyGroceryListsPage(driver);
		int numOfShoppingListsAfter = glp.getNumOfShoppingLists();
		boolean isListExist = glp.isExist("list1");

		Assert.assertEquals(numOfShoppingListsAfter, numOfShoppingListsBefore + 1);
		Assert.assertTrue(isListExist);
	}
}
