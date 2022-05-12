package pageobjects;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		PageFactory.initElements(driver, this);
	}

	public void click(WebElement el) {
		highlightElement(el);
		el.click();
		sleep(1000);
	}

	public void submit(WebElement el) {
		highlightElement(el);
		el.submit();
		sleep(1000);
	}

	public void fillText(WebElement el, String text) {
		highlightElement(el);
		el.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		el.sendKeys(text);
	}

	public String getText(WebElement el) {
		highlightElement(el);
		return el.getText();
	}

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void highlightElement(WebElement element) {
		// keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "border: 2px solid black;" + "background-color:lawngreen;" + originalStyle;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Change the style
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ newStyle + "');},0);", element);
		// Change the style back after 400 milliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);
	}

}
