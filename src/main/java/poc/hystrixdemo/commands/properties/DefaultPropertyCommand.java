package poc.hystrixdemo.commands.properties;


import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import poc.hystrixdemo.services.AndService;
import poc.hystrixdemo.services.FallbackService;
import poc.hystrixdemo.services.HystrixService;
import poc.hystrixdemo.services.IService;
import poc.hystrixdemo.services.Service;
import poc.hystrixdemo.services.Service99Available;
import poc.hystrixdemo.services.WeService;

public class DefaultPropertyCommand extends HystrixCommand<String> {


    public DefaultPropertyCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup1"));
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