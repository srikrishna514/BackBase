package Backbase.ComputerDatabase;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


//public String nextField_css = "input[id='nextnumerictextbox']";
public class Addcomputer {



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

//addComputer(WebDriver driver, String introduceddate, String discontinued, String selectvalue) {
	@Test()
	/* 	Author:  
	 * 	Description: 
	 *	Modified By: 
	 *	Modification: 
	 */
	
	public void AddNewComp(WebDriver driver){
		Datafile df=new Datafile();
		String introduceddate=df.introduceddate;
		String discontinued=df.discontinued;
		String selectvalue=df.selectvalue;
		Utility ua = new Utility();
		ua.addComputer(driver, introduceddate, discontinued, selectvalue);
	}

	@Test()
	/* 	Author:  
	 * 	Description: 
	 *	Modified By: 
	 *	Modification: 
	 */
	
	public void deleteComp(WebDriver driver){
		Datafile df=new Datafile();
		String deleteComp=df.selectvalue;
		Utility ua = new Utility();
		ua.deleteComputer(driver,deleteComp);
	}
	
	@Test()
	/* 	Author:  
	 * 	Description: 
	 *	Modified By: 
	 *	Modification: 
	 */
	
	public void verifySorting(WebDriver driver){
		Datafile df=new Datafile();
		String columnName=df.columnName;
		
		Utility ua = new Utility();
		String a[] = getColumnValues(driver,  columnName,"","");
		isSorted("", "ASC", columnName)
	}
}