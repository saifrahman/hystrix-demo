package poc.hystrixdemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import rx.*;
import static org.awaitility.Awaitility.*;
import static java.util.concurrent.TimeUnit.*;

import org.junit.Test;

import junit.framework.Assert;
import poc.hystrixdemo.commands.Service99AvailableCommand;

public class Service99AvailableTest {
	private static final String name = "Saif";
	private static int mainIteration = 0; 
	private static int fallbackIteration = 0; 

	@Test
	public void FallbackExecution(){
		for(int i = 0 ; i < 1000;i++){
			execute();
		}
		//block
		await().atLeast(10, SECONDS);
		System.out.println("Main iterations:" + mainIteration);
		System.out.println("Fallback iterations:" + fallbackIteration);
	}

	private void execute() {
		Observable<String> observableName = new Service99AvailableCommand(name).observe();
		observableName.subscribe((v) -> {
			String mainLoop = "Hello Saif!";
			String fallbackLoop = "AlterEgoSaif";
			Assert.assertTrue(mainLoop.equals(v) || fallbackLoop.equals(v) ) ;
			if(mainLoop.equals(v)){
				mainIteration++;
			}else{
				fallbackIteration++;
			}
			
		}, (exception) -> {
	        //exception.printStackTrace();
	    });
	}
	
}
