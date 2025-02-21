package rahulshetty.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshetty.pageobject.LandingPage;

public class Base {
    public WebDriver driver;
    public LandingPage land;

    public WebDriver initializeDriver() throws IOException {
        if (driver == null) {  
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("src/main/java/rahulshetty/resources/GlobalData.Properties");
            prop.load(fis);
            String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
//            String browserName = prop.getProperty("browser");

            System.out.println("Browser Name from Properties File: " + browserName); // Debugging Line

            if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("safari")) {
                driver = new SafariDriver();
            }


            driver.manage().window().maximize();
        }
        return driver;
    }
    
public List<HashMap<String, String>> getJsonDataToMap(String fileName) throws IOException {
		
		
		// Also need to add common io defendency
		
		//convert json to string
	 String jsonContent= FileUtils.
			 readFileToString(new File(fileName),StandardCharsets.UTF_8);
	//for converting String to HashMap we need  to add "Jackson databind" dependency
	 
	 ObjectMapper mapper = new ObjectMapper();
	 List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
	 return data;
	}

public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
	TakesScreenshot ts = (TakesScreenshot) driver; 
	File source =ts.getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
	FileUtils.copyFile(source, file);
	return System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";

}


    @BeforeMethod(alwaysRun=true)
    public void launchApplication() throws IOException {
        driver = initializeDriver();  // Only initializes once
        land = new LandingPage(driver);
        land.passURL();
    }

    public LandingPage getLandingPage() {
        return land;
    }

    
	
	@AfterMethod(alwaysRun=true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;  // Reset driver to ensure fresh session for next test
        }
    }
}
