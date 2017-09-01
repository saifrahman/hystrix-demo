package poc.hystrixdemo.commands;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import poc.hystrixdemo.services.AndService;
import poc.hystrixdemo.services.FallbackService;
import poc.hystrixdemo.services.HystrixService;
import poc.hystrixdemo.services.IService;
import poc.hystrixdemo.services.Service;
import poc.hystrixdemo.services.Service99Available;
import poc.hystrixdemo.services.WeService;

public class AndCommand extends HystrixCommand<String> {

    private final Service service;

    public AndCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.service = new AndService();
    }

    @Override
    protected String run() {
    	return service.doGet();
    }
    
    @Override
    protected String getFallback() {
        return "null";
    }
    
}