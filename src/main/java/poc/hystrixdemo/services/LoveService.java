package poc.hystrixdemo.services;

import java.util.Random;

public class LoveService implements Service{
	public String doGet(){
		int r =  new Random().nextInt(100); 
    	if(r == 99){
    		throw new RuntimeException("Service99 Unavailable");
    	}
    	else{
    		return "Love ";
    	}
	}
}
