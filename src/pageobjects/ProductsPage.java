package pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends MenuPage {
	@FindBy(css = ".category-filter__text")
	private List<WebElement> subCategoriesNamesList;
	@FindBy(css = ".category-filter__link .count")
	private List<WebElement> subCategoriesTotalProductsList;
	@FindBy(css = ".grid-item")
	private List<WebElement> productsList;
	@FindBy(css = ".add-to-list")
	private List<WebElement> addToListBtnsList;
	@FindBy(css = ".view-more .button")
	private WebElement viewMoreBtn;

	public ProductsPage(WebDriver driver) {
		super(driver);
	}

	public void goToSubCategory(String name) {
		wait.until(ExpectedConditions.visibilityOfAllElements(subCategoriesNamesList));
		for (WebElement el : subCategoriesNamesList) {
			wait.until(ExpectedConditions.elementToBeClickable(el));
			if (getText(el).equalsIgnoreCase(name)) {
				click(el);
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOfAllElements(productsList));
	}

	public void goToProductDetailPage(String productName) {
		wait.until(ExpectedConditions.visibilityOfAllElements(productsList));
		for (WebElement el : productsList) {
			wait.until(ExpectedConditions.elementToBeClickable(el));
			if (getText(el.findElement(By.cssSelector(" .detail-card-description"))).equalsIgnoreCase(productName)) {
				click(el);
				break;
			}
		}
	}

	public void loadAllProducts() {
		boolean isThereMoreProducts = true;
		while (isThereMoreProducts) {
			try {
				click(viewMoreBtn);
			} catch (Exception e) {
				isThereMoreProducts = false;
			}
		}
	}

	// validations
	public int getNumOfSubCategories() {
		wait.until(ExpectedConditions.visibilityOfAllElements(subCategoriesNamesList));
		return subCategoriesNamesList.size();
	}

	public String getSubCategoryName(int num) {
		wait.until(ExpectedConditions.visibilityOfAllElements(subCategoriesNamesList));
		return getText(subCategoriesNamesList.get(num - 1));
	}

	public int getNumOfProductsOnPage() {
		return productsList.size();
	}

	public int getNumOfProductsOnSubCategory(String name) {
		int i = 0;
		for (WebElement el : subCategoriesNamesList) {
			if (getText(el).equalsIgnoreCase(name)) {
				String num = getText(subCategoriesTotalProductsList.get(i));
				int num1 = Integer.parseInt(num);
				return num1;
			}
			i++;
		}
		return -1;// Didn't find the subCategory
	}

	public String getProductPrice(String productName) {
		for (WebElement el : productsList) {
			if (getText(el.findElement(By.cssSelector(" .detail-card-description"))).equalsIgnoreCase(productName)) {
				return getText(el.findElement(By.cssSelector(" .price")));
			}
		}
		return "-1"; // Didn't find the product
	}
}
