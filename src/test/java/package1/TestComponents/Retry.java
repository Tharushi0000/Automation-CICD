package package1.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	
	int count =0;
	int maxTry=1;
			

	@Override
	public boolean retry(ITestResult result) {
		
		//when test fails it comes here and check do i need to rerun the test
		if (count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
