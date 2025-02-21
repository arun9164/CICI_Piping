package rahulshetty.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshetty.components.Base;
import rahulshetty.pageobject.LandingPage;

public class ErrorMessegeValidationTest extends Base {
    @Test (dataProvider ="getHash",groups="ErrorValidations")
    public void validateLoginPageErrorMessage(HashMap<String, String> input) throws IOException {
        LandingPage land = getLandingPage();  // Use already initialized instance
        
//        land.LoginIntoApplication("arunnatikar99@gmail.com", "Rl@1234");
        land.LoginIntoApplication(input.get("email"), input.get("pass"));
        String expectedErrorMsg = "Incorrect email or password.";
        String actualErrorMsg = land.getLoginPageErrorMessage();
        
        System.out.println("Error message - " + actualErrorMsg);
        AssertJUnit.assertEquals(actualErrorMsg, expectedErrorMsg);
    }
    
//    @DataProvider
//    public Object[] [] getHash() {
//    	HashMap <String, String > map = new <String, String>HashMap();
//    	map.put("email", "arunnatikar99@gmail.com");
//    	map.put("pass", "Rahul@123455");
//    	HashMap <String, String > map1 = new <String, String>HashMap();
//    	map.put("email", "arunnatikar99@gmail.com");
//    	map.put("pass", "Rahul@1234578");
//    	
//    	return new Object[][] {{map},{map1}};
//    }
    
    @DataProvider
    public Object[][] getHash() throws IOException {
    	List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+
    			"//src//test//java//rahulshetty//data//ErrorValidation.json");
    			return new Object[][] {{data.get(0)},{data.get(1)}};
    }
    
    
    
//    @DataProvider
//    public Object[] [] getData(){
//    	return new Object[] [] {{"arunnatikat99@gmail.com", "1234"},{"arun@gmail.com", "123456"}};
//    	
//    }
}
