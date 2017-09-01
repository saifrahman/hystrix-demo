package poc.hystrixdemo.services;

import java.util.Random;

public class Service99Available implements Service {
	public String doGet(String name){
		int r =  new Random().nextInt(100); 
    	if(r == 99){
    		throw new RuntimeException("Service99 Unavailable");
    	}else{
    		return "Hello " + name + "!";
    	}
	}
}
