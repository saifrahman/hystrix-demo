package poc.hystrixdemo.commands.properties;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

public class DynamicInstanceCommand extends HystrixCommand<String> {


    public DynamicInstanceCommand() {
    	super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
    	        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
    	               .withExecutionTimeoutInMilliseconds(4000)));
    }

    @Override
    protected String run() {
    	HystrixProperty<Integer> res = properties.executionTimeoutInMilliseconds();
    	return res.get().toString();
    }
    
    @Override
    protected String getFallback() {
        return "null";
    }
    
}