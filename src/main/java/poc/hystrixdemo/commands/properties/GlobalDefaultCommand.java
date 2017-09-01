package poc.hystrixdemo.commands.properties;


import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;


public class GlobalDefaultCommand extends HystrixCommand<String> {


    public GlobalDefaultCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup2"));
    }

    @Override
    protected String run() {
    	return properties.executionTimeoutInMilliseconds().get().toString();
    }
    
    @Override
    protected String getFallback() {
        return "null";
    }
    
}