package data;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import utils.Excel;

public class DP {

	@DataProvider
	public Object[][] getDataFromExcel(Method m) {
		String excelPath = System.getProperty("user.dir") + "\\src\\data\\Input.xlsx";
		String sheetName = "";
		switch (m.getName()) {
		case "tc04_tc05_tc06_openCategory": {
			sheetName = "subCategories";
			break;
		}
		case "tc08_tc09_tc10_productDetails": {
			sheetName = "productsDetails";
			break;
		}
		case "tc12_tc13_tc14_addNewRegularProduct": {
			sheetName = "productsToAdd";
			break;
		}
		case "tc24_tc25_searchStore": {
			sheetName = "searchStore";
			break;
		}
		}

		Object[][] table = Excel.getTableArray(excelPath, sheetName);
		return table;
	}
}
