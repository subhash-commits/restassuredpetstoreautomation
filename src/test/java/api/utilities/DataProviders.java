package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {
		ExcelUtility ex = new ExcelUtility(System.getProperty("user.dir") + "\\TestData\\usersdata.xlsx");
		int totalrows = ex.getRowcount("Sheet1");
		int totalcols = ex.getCellCount("Sheet1", 1);
		String apiData[][] = new String[totalrows][totalcols];
		for (int i = 1; i <= totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
				apiData[i - 1][j] = ex.getCellData("Sheet1", i, j);
			}
		}

		return apiData;

	}

	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {
		ExcelUtility ex = new ExcelUtility(System.getProperty("user.dir") + "\\TestData\\usersdata.xlsx");
		int totalrows = ex.getRowcount("Sheet1");

		String apiData[] = new String[totalrows];
		for (int i = 1; i <= totalrows; i++) {
			apiData[i - 1] = ex.getCellData("Sheet1", i, 3);
		}

		return apiData;

	}
}
