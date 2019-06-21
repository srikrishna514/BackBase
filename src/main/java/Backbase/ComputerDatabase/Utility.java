package Backbase.ComputerDatabase;


	
	
	
	//package Backbase.ComputerDatabase;

	import static org.testng.Assert.assertTrue;

import java.awt.List;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Random;

	import org.junit.Test;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;

	import junit.framework.Assert;


	//public String nextField_css = "input[id='nextnumerictextbox']";
	public class Utility {

		
		
		
			



	@BeforeMethod
	/* 	Author:  
	 * 	Description: launch Local webdriver
	 *	Modified By: Srikrishna
	 *	Modified On: 
	 *	Modification: 
	 *  Parameter: 
	 */
	public void BrowserAutomation()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		WebDriver driver=new ChromeDriver();
		 
		driver.get("http://computer-database.herokuapp.com/computers");
		}
	@AfterMethod
	/* 	Author:  
	 * 	Description: Closes Local webdriver
	 *	Modified By: Srikrishna
	 *	Modified On: 
	 *	Modification: 
	 *  Parameter: 
	 */

	public void quitBrowser(WebDriver driver){
		driver.quit();
	} 	


	public void addComputer(WebDriver driver, String introduceddate, String discontinued, String selectvalue) {
		int randomNum= genRandom_4_Digit_Num();
		driver.findElement(By.id("add")).click();
		driver.findElement(By.id("name")).sendKeys("Computer Aded"+randomNum);
		driver.findElement(By.id("introduced")).sendKeys(introduceddate);
		driver.findElement(By.id("discontinued")).sendKeys(discontinued);
		
		WebElement dropdown=driver.findElement(By.id("company"));
		selectDropdownValueByVisibleText(dropdown,selectvalue);
		
		driver.findElement(By.cssSelector("#input[type=submit]")).click();
		if(driver.findElement(By.cssSelector("#input[type=submit]")).getText().contains("Computer"+ "Computer Aded"+randomNum+" has been created"))
		{
		
			Assert.assertTrue("Computer"+ "Computer Aded"+randomNum+" has been created",true);
			
			
		}
	}
	public void deleteComputer(WebDriver driver, String deleteComp) {
				
		driver.findElement(By.id("searchbox")).sendKeys(deleteComp);
		driver.findElement(By.id("searchsubmit")).click();
	
		driver.findElement(By.cssSelector("#tbody[trnth-child(1)]")).click();
		
		driver.findElement(By.cssSelector("#type[value=Delete this computer]")).click();
				
		
		if(driver.findElement(By.cssSelector("#input[class=alert-message warning]")).getText().contains(" Computer has been deleted"))
		{
		
			Assert.assertTrue("Computer has been deleted",true);
			
			
		}
	}

	public void selectDropdownValueByVisibleText(WebElement element, String text) {
		/*     Author: Srikrishna
		 *     Description: Select value from dropdown
		 *     Modified By: 
		 *     Modified On: 
		 *     Modification:
		 *     Parameter : element, text
		 */
		Select sct = new Select(element);
		sct.selectByVisibleText(text);
	}
	public String[] getColumnValues(WebDriver driver, String columnName, int firstValues, int lastValues){
		/*     Author:  Srikrishna
		 *     Description: get the column values from specified columns
		 
		 */
		java.util.List<WebElement> gridHeaders = driver.findElements(By.tagName("th"));
		int columnCount = 0;
		for(WebElement header : gridHeaders){
			columnCount++;
			if(header.getText().equalsIgnoreCase(columnName)){ 
				break;
			}
		}
		System.out.println("ColumnNumber is " + columnCount);
		ArrayList<String> columnValues = null;
		columnValues = new ArrayList<String>();
		for (int firstValue=1; firstValue<=firstValues; firstValue++){
			columnValues.add(getCell(driver,firstValue,columnCount));
		}
		String lastPageIcon_xpath = null;
		WebElement lastPageIcon = driver.findElement(By.xpath(lastPageIcon_xpath));
		lastPageIcon.isEnabled();
		System.out.println(lastPageIcon.isEnabled());
		String recordCount_css = null;
		if (lastPageIcon.isDisplayed()){
			lastPageIcon.click();
			
			String recordCount = driver.findElement(By.cssSelector(recordCount_css)).getText();
			String[] recordNum = recordCount.split(" ");  
			int lastPageRecords = Integer.parseInt(recordNum[0]);
			System.out.println("last Page Records " + lastPageRecords);
			for (int lastValue=(((lastPageRecords % 100)-lastValues)+1); lastValue<=(lastPageRecords % 100); lastValue++){
				columnValues.add(getCell(driver,lastValue,columnCount));
			}
		}
		else{
			String recordCount = driver.findElement(By.cssSelector(recordCount_css)).getText();
			String[] recordNum = recordCount.split(" "); 
			int lastPageRecords = Integer.parseInt(recordNum[0]);
			for (int lastValue=lastPageRecords-lastValues; lastValue<=lastPageRecords; lastValue++){
				columnValues.add(getCell(driver,lastValue,columnCount));
			}
		}
		System.out.println("Total values loaded into arry are: "+columnValues);
		String[] columnValuesarry = columnValues.toArray(new String[columnValues.size()]);
		return columnValuesarry;
	}

	private String getCell(WebDriver driver, int firstValue, int columnCount) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isSorted(String[] columnValues,String sortType,String ColumnName){    	
		/*     Author:  Srikrishna
		 *     Description: Checks if the array sort or not in specified order
		 *     Parameter : columnValues, sortType, ColumnName
		 */
		boolean flag=true;
		int arrayCounter;
		if (sortType.equals("Asc")){
			for(arrayCounter=0; arrayCounter<columnValues.length-1; arrayCounter++){
				if(!(columnValues[arrayCounter] == "" & columnValues[arrayCounter+1]== "")){
					if(!(columnValues[arrayCounter] == null & columnValues[arrayCounter+1]== null)){
					int result = columnValues[arrayCounter].compareToIgnoreCase(columnValues[arrayCounter+1]); 
					if(result<=0){
						flag=true;
					}
					else{
						System.out.println(columnValues[arrayCounter] + " : " + columnValues[arrayCounter+1] + " " + result);
						flag=false;
						break;
					}
				}
			}
			}
			if(flag){
				System.out.println( sortType + " Sort is sucessfull " + "on column name " + ColumnName );
				
			}
			else{
				System.out.println( sortType + " Sort is not sucessfull " + "on column name " + ColumnName);
				
		
			}
			return flag;
		}
		
		else{
			for(arrayCounter=0; arrayCounter<columnValues.length-1; arrayCounter++){
				int result = columnValues[arrayCounter].compareToIgnoreCase(columnValues[arrayCounter+1]); 
				if(result<=0){
					flag=false;
					break;
				}
				else{
					flag=true;
				}
			}
			if(flag){
				System.out.println(sortType + " Desc Sort is sucessfull "+ "on column name " + ColumnName );
				
			}
			else{
				System.out.println(sortType + " Sort is not sucessfull " + "on column name " + ColumnName);
				
			}	
			return flag;
		}
	}
	public static int genRandom_4_Digit_Num(){
		/* 	Author:  Srikrishna
		 * 	Description: Returns a random 4 digit integer between 1000 and 9999
		 *	Modified By: 
		 *	Modified On:
		 *	Modification:
		 *  Parameter : 
		 */
		Random rn = new Random();
		int range = 99999 - 1000 + 1;  
		int intNum4 =  rn.nextInt(range) + 1000;  // For 4 digit number
		return intNum4;
	}
	}


