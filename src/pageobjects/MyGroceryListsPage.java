package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyGroceryListsPage extends MenuPage {
	// From shopping lists menu (on the left)
	@FindBy(css = ".list-title")
	private List<WebElement> shoppingListsNamesList;
	@FindBy(css = ".item-count")
	private List<WebElement> TotalRowsList;
	@FindBy(css = "[data-test='createNewListButtonSmall']")
	private WebElement addNewListBtn;
	@FindBy(css = "[data-test='listNameInputField'] .MuiInput-input")
	private WebElement newListNameField;

	// From current list
	@FindBy(css = ".detail-header__list-title")
	private WebElement listTitle;
	@FindBy(css = ".row-name")
	private List<WebElement> productsNamesList;
	@FindBy(css = "[name='quantity']")
	private List<WebElement> productsQuantityList;
	@FindBy(css = ".Toastify__toast--warning .Toastify__toast-body")
	private WebElement warningMsg;

	// From the menu on the right
	@FindBy(css = ".ellipsis-menu")
	private WebElement menuBtn;
	@FindBy(css = ".detail-list.edit")
	private WebElement editListBtn;

	public MyGroceryListsPage(WebDriver driver) {
		super(driver);
	}

	public void goToList(String listName) {
		for (WebElement el : shoppingListsNamesList) {
			if (getText(el).equalsIgnoreCase(listName)) {
				click(el);
				break;
			}
		}
	}

	public void updateQuantity(String productName, String newQuantity) {
		wait.until(ExpectedConditions.visibilityOfAllElements(productsNamesList));
		for (int i = 0; i < productsNamesList.size(); i++) {
			if (getText(productsNamesList.get(i)).equalsIgnoreCase(productName)) {
				fillText(productsQuantityList.get(i), newQuantity);
				break;
			}
		}
		click(listTitle);// To exit the quantity button
	}

	public void goToEditList() {
		click(menuBtn);
		click(editListBtn);
	}

	public void addNewList(String name) {
		click(addNewListBtn);
		fillText(newListNameField, name);
		submit(newListNameField);
	}

	// validations
	public int getTotalRowsInList(String listName) {
		for (int i = 0; i < shoppingListsNamesList.size(); i++) {
			if (getText(shoppingListsNamesList.get(i)).equalsIgnoreCase(listName)) {
				return Integer.parseInt(getText(TotalRowsList.get(i)));
			}
		}
		return 0;
	}

	public int getProductQuantityOnList(String productName) {
		wait.until(ExpectedConditions.visibilityOfAllElements(productsNamesList));
		for (int i = 0; i < productsNamesList.size(); i++) {
			if (getText(productsNamesList.get(i)).equalsIgnoreCase(productName)) {
				return Integer.parseInt(productsQuantityList.get(i).getAttribute("value"));
			}
		}
		return 0;
	}

	public String getWarningMsg() {
		String msg = "";
		try {
			return getText(warningMsg);
		} catch (Exception e) {
			// There is no message
		}
		return msg;
	}

	public int getNumOfShoppingLists() {
		return shoppingListsNamesList.size();
	}

	public boolean isExist(String listName) {
		boolean exist = false;
		for (WebElement el : shoppingListsNamesList) {
			if (getText(el).equalsIgnoreCase(listName)) {
				exist = true;
				break;
			}
		}
		return exist;
	}
}
