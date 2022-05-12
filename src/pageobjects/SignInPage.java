package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends MenuPage {
	@FindBy(css = "[name='email']")
	private WebElement emailField;
	@FindBy(css = "[name='password']")
	private WebElement passwordField;
	@FindBy(css = ".clickable.button.sign-in")
	private WebElement signInBtn;
	@FindBy(css = ".errors li")
	private List<WebElement> errorMsgList;

	public SignInPage(WebDriver driver) {
		super(driver);
	}

	public void signIn(String email, String password) {
		try {
			fillText(emailField, email);
			fillText(passwordField, password);
			click(signInBtn);
		} catch (Exception e) {
			// Already signed in
		}
	}

	// Validations
	public List<String> getErrorMsgs() {
		List<String> errorMsgs = new ArrayList<String>();
		for (int i = 0; i < errorMsgList.size(); i++) {
			errorMsgs.add(i, getText(errorMsgList.get(i)));
		}
		return errorMsgs;
	}

	public int countErrorMsgs() {
		return errorMsgList.size();
	}
}
