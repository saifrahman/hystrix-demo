package poc.hystrixdemo.services;

public interface Service {
	default String doGet(String name){return name;}
	
	default String doGet(){return "Hello";}
}
