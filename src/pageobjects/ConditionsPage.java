package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConditionsPage extends MenuPage {
	@FindBy(css = ".section__body>.header-divider")
	//@FindBy(css = ".header-divider")
	private WebElement pageTitle;

	public ConditionsPage(WebDriver driver) {
		super(driver);
	}

	// validation
	public String getPageTitle() {
		wait.until(ExpectedConditions.visibilityOf(pageTitle));
		return getText(pageTitle);
	}
}
