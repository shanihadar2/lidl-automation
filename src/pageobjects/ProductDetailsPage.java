package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends MenuPage {
	@FindBy(css = ".product-title")
	private WebElement productName;
	@FindBy(css = ".product-price .price")
	private WebElement productPrice;
	@FindBy(css = ".description-info")
	private WebElement sectionName;
	@FindBy(css = ".add-to-list-button")
	private WebElement addToListBtn;

	// For products with multiple types
	@FindBy(css = ".product-variants-list .grid-item")
	private List<WebElement> typesList;

	// Popup window after clicking on add to list
	@FindBy(css = ".list-title")
	private List<WebElement> shoppingListNamesList;
	@FindBy(css = ".item-count")
	private List<WebElement> TotalProductsList;
	@FindBy(css = "[data-test='addToListDoneButton']")
	private WebElement doneBtn;

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}

	public void chooseType(String name) {
		wait.until(ExpectedConditions.visibilityOfAllElements(typesList));
		for (WebElement el : typesList) {
			if (getText(el).equalsIgnoreCase(name)) {
				click(el);
				break;
			}
		}
	}

	public void addProductToList(String listName) {
		wait.until(ExpectedConditions.elementToBeClickable(addToListBtn));
		click(addToListBtn);
		for (WebElement el : shoppingListNamesList) {
			if (getText(el).equalsIgnoreCase(listName)) {
				click(el);
				click(doneBtn);
				break;
			}
		}
	}

	// validations
	public String getProductName() {
		return getText(productName);
	}

	public String getprice() {
		return getText(productPrice);
	}

	public String getSectionName() {
		String section = getText(sectionName);
		section = section.replace("section: ", "");
		return section;
	}

}
