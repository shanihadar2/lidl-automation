package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MenuPage extends BasePage {
	@FindBy(css = ".products-dropdown")
	private WebElement productsBtn;
	@FindBy(css = ".MuiButtonBase-root.MuiListItem-root.MuiMenuItem-root.MuiListItem-button")
	private List<WebElement> categoriesList;
	@FindBy(css = ".profile-dropdown-button")
	private WebElement myAccountBtn;
	@FindBy(css = ".MuiTypography-root.MuiListItemText-primary.MuiTypography-body1")
	private List<WebElement> myAccountMenuList;
	@FindBy(css = ".user-name")
	private WebElement userNameText;
	@FindBy(css = ".search-input")
	private WebElement searchField1;
	@FindBy(css = ".search-input .MuiInputBase-input.MuiInput-input")
	private WebElement searchField2; // appears after clicking on searchField1
	@FindBy(css = ".sign-in")
	private WebElement signInBtn;
	@FindBy(css = ".footer-links .menu-link")
	private List<WebElement> footerLinksList;
	@FindBy(css = ".find-store-container .clickable-label")
	private WebElement findStoreBtn;
	@FindBy(css = ".clickable.button.notice--close")
	private WebElement closePopUpBtn;

	public MenuPage(WebDriver driver) {
		super(driver);
	}

	public void closePopUp() {
		try {
			click(closePopUpBtn);
		} catch (Exception e) {
			// There is no popUp
		}
	}

	public void searchProducts(String name) {
		click(searchField1);
		wait.until(ExpectedConditions.visibilityOf(searchField2));
		fillText(searchField2, name);
		submit(searchField2);
	}

	public void goToSignIn() {
		try {
			click(signInBtn);
		} catch (Exception e) {
			// Already signed in
		}
	}

	public void goToMyGroceryLists() {
		wait.until(ExpectedConditions.elementToBeClickable(myAccountBtn));
		click(myAccountBtn);
		for (WebElement el : myAccountMenuList) {
			if (getText(el).equalsIgnoreCase("my grocery lists")) {
				click(el);
				break;
			}
		}
	}

	public void goToCategory(String name) {
		wait.until(ExpectedConditions.elementToBeClickable(productsBtn));
		click(productsBtn);
		wait.until(ExpectedConditions.visibilityOfAllElements(categoriesList));
		for (WebElement el : categoriesList) {
			if (getText(el).equalsIgnoreCase(name)) {
				click(el);
				break;
			}
		}
	}

	public void goToFindStore() {
		wait.until(ExpectedConditions.elementToBeClickable(findStoreBtn));
		click(findStoreBtn);
	}

	public void goToFooterLink(String name) {
		wait.until(ExpectedConditions.visibilityOfAllElements(footerLinksList));
		for (WebElement el : footerLinksList) {
			if (getText(el).equalsIgnoreCase(name)) {
				wait.until(ExpectedConditions.elementToBeClickable(el));
				click(el);
				break;
			}
		}
	}

	// validation
	public String getUserName() {
		try {
			return getText(userNameText);
		} catch (Exception e) {
			return "There is no username";
		}
	}
}
