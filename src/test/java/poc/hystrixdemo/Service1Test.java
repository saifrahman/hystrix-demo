package poc.hystrixdemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import rx.*;

import org.junit.Test;

import junit.framework.Assert;
import poc.hystrixdemo.commands.Service1Command;

public class Service1Test {
	private static final String surName = "Rahman";
	private static final String name = "Saif";

	@Test
	public void NonBlockingExecution(){
		Observable<String> observableName = new Service1Command(name).observe();
		Observable<String> observableSurName = new Service1Command(surName).observe();
		
		observableName.subscribe((v) -> {
	        //System.out.println("onNext: " + v);
			Assert.assertEquals("Hello Saif!", v);
			
	    });
		observableSurName.subscribe((v) -> {
			Assert.assertEquals("Hello Rahman!", v);
		}, (exception) -> {
	        exception.printStackTrace();
	    });
	}
	@Test
	public void executeSynchronous(){
		String s = new Service1Command(name).execute();
		Assert.assertEquals("Hello Saif!", s);
		System.out.println(s);
	}
	@Test
	public void executeAsynchronous(){
		Future<String> future1 = new Service1Command(name).queue();
		Future<String> future2 = new Service1Command(surName).queue();
		try {
			String s1 = future1.get();
			String s2 = future2.get();
			Assert.assertEquals("Hello Saif!", s1);
			Assert.assertEquals("Hello Rahman!", s2);
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
