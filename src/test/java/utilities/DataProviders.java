package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][]getData() throws IOException
	{
		String path=".\\testdata\\testdata.xlsx";
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int rows=xlutil.getRowCount("sheet1");
		int cols=xlutil.getCellCount("sheet1",1);
		
		String logindata[][]=new String[rows][cols];
		
		for(int i=1;i<=rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("sheet1", i, j);
			}
		}
		
		return logindata;
	}
	

}
