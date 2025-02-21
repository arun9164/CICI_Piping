package rahulshetty.components;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int count = 0;  // ✅ Move count outside the method to persist across calls
    private final int max = 1;  // ✅ Maximum retries (change as needed)

    @Override
    public boolean retry(ITestResult result) {
        if (count < max) {
            count++;  // ✅ Now count increments properly✅❤️
            return true;  // ✅ Test will retry only once
        }
        return false;  // ✅ No more retries after reaching max
    }
}
