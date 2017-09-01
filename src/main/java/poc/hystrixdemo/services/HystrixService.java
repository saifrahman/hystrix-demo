package poc.hystrixdemo.services;

public class HystrixService implements Service{
	public String doGet(){
		 return "hystrix ";
	}
}
