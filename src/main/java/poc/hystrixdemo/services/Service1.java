package poc.hystrixdemo.services;

public class Service1 implements Service{
	public String doGet(String name){
		 return "Hello " + name + "!";
	}
}
