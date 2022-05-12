package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EditListPage extends BasePage {
	@FindBy(css = ".entry-row.row>.primary [name='text']")
	private List<WebElement> productsNamesList;
	@FindBy(css = ".entry-row .checkbox")
	private List<WebElement> checkboxList;
	@FindBy(css = ".entry-row.row>.detail-row-input")
	private List<WebElement> quantityList;
	@FindBy(css = ".remove-item")
	private WebElement removeSelectedBtn;
	@FindBy(css = ".remove-list")
	private WebElement deleteListBtn;
	@FindBy(css = ".submit")
	private WebElement saveBtn;

	public EditListPage(WebDriver driver) {
		super(driver);
	}

	public void deleteProductLine(String name) {
		// If there are some lines with the same product name - delete the first one
		wait.until(ExpectedConditions.visibilityOfAllElements(productsNamesList));
		for (int i = 0; i < productsNamesList.size(); i++) {
			if (productsNamesList.get(i).getAttribute("value").equalsIgnoreCase(name)) {
				click(checkboxList.get(i));
				break;
			}
		}
		click(removeSelectedBtn);
		click(saveBtn);
	}

	public void deleteAllProducts() {
		wait.until(ExpectedConditions.visibilityOfAllElements(checkboxList));
		for (WebElement el : checkboxList) {
			click(el);
		}
		click(removeSelectedBtn);
		click(saveBtn);
	}

	public void deleteList() {
		click(deleteListBtn);
	}

}
