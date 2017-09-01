package poc.hystrixdemo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.naming.spi.DirStateFactory.Result;

import rx.*;
import static org.awaitility.Awaitility.*;
import static java.util.concurrent.TimeUnit.*;
import com.netflix.hystrix.HystrixCommand;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import poc.hystrixdemo.commands.AndCommand;
import poc.hystrixdemo.commands.ICommand;
import poc.hystrixdemo.commands.JavaFxCommand;
import poc.hystrixdemo.commands.LoveCommand;
import poc.hystrixdemo.commands.MyHysterixCommand;

public class Service99AvailableTest2 {
	private static final String JAVA_FX_WORD = "javaFx";
	private static final String AND_WORD = "and ";
	private static final String HYSTRIX_WORD = "hystrix ";
	private static final String LOVE_WORD = "Love ";
	private static final String IWord = "I ";
	private static final String Tagline = "I love hystrix and javaFx";
	private ArrayList<String> results= new ArrayList<>(5);
	
	private static int mainIteration = 0;
	private static int fallbackIteration = 0; 

	HystrixCommand iCommand;
	HystrixCommand loveCommand;
	HystrixCommand hystrixCommand;
	HystrixCommand andCommand;
	HystrixCommand javaFxCommand;
	
	
	@Before
	public void setup(){
		results.add(IWord);
		results.add(LOVE_WORD);
		results.add(HYSTRIX_WORD);
		results.add(AND_WORD);
		results.add(JAVA_FX_WORD);
	}
	private boolean mainLoopExecuted(ArrayList<String> results){
		if(!results.get(0).equals(IWord)){
			return false;
		}else if(!results.get(1).equals(LOVE_WORD)){
			return false;
		}else if(!results.get(2).equals(HYSTRIX_WORD)){
			return false;
		}else if(!results.get(3).equals(AND_WORD)){
			return false;
		}else if(!results.get(4).equals(JAVA_FX_WORD)){
			return false;
		}else{
			return true;
		}
	}
	
	private void execute(){
		iCommand = new ICommand();
		iCommand.observe().subscribe( (v) -> {
			results.set(0, (String) v);
		}, (error) ->{});
		
		loveCommand = new LoveCommand();
		loveCommand.observe().subscribe( (v) -> {
			results.set(1, (String) v);
		}, (error) ->{});
		
		hystrixCommand = new MyHysterixCommand();
		hystrixCommand.observe().subscribe( (v) -> {
			results.set(2, (String) v);
		}, (error) ->{});
		
		andCommand = new AndCommand();
		andCommand.observe().subscribe( (v) -> {
			results.set(3, (String) v);
		}, (error) ->{});
		
		javaFxCommand = new JavaFxCommand();
		javaFxCommand.observe().subscribe( (v) -> {
			results.set(4, (String) v);
		}, (error) ->{});
		
		await().atLeast(10, SECONDS);
		
		if(mainLoopExecuted(results)){
			mainIteration++;
		}else{
			await().atLeast(10, SECONDS);
			if(mainLoopExecuted(results)){
				mainIteration ++;
			}else{
				fallbackIteration++;
				System.out.println(results);
			}
		}
		
	}
	
	@Test
	public void FallbackExecution(){
		System.out.println("Initial Results: " + results);
		for(int i = 0 ; i < 1000;i++){
			execute();
		}
		//block
		await().atLeast(20, SECONDS);
		System.out.println("Main iterations:" + mainIteration);
		System.out.println("Fallback iterations:" + fallbackIteration);
	}

	
	
	
}
