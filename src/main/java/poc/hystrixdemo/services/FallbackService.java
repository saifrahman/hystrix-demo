package poc.hystrixdemo.services;

public class FallbackService implements Service{
	public String doGet(String name){
		return "AlterEgo" + name;
	}
}
