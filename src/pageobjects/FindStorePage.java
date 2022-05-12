package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindStorePage extends MenuPage {
	@FindBy(css = "[name='search']")
	private WebElement searchField;
	@FindBy(css = ".card .card-body")
	private List<WebElement> searchResultsList;
	@FindBy(css = ".list-container>.headline")
	private WebElement searchResultMsg;

	public FindStorePage(WebDriver driver) {
		super(driver);
	}

	public void searchForStore(String cityOrZip) {
		fillText(searchField, cityOrZip);
		submit(searchField);
	}

	// validations
	public int getNumOfResults() {
		return searchResultsList.size();
	}

	public String getMsg() {
		String msg = "";
		try {
			return getText(searchResultMsg);
		} catch (Exception e) {
			// There is no message
		}
		return msg;
	}
}
