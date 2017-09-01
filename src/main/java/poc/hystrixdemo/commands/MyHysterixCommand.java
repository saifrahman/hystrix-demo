package poc.hystrixdemo.commands;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import poc.hystrixdemo.services.FallbackService;
import poc.hystrixdemo.services.HystrixService;
import poc.hystrixdemo.services.IService;
import poc.hystrixdemo.services.Service;
import poc.hystrixdemo.services.Service99Available;
import poc.hystrixdemo.services.WeService;

public class MyHysterixCommand extends HystrixCommand<String> {

    private final Service service;

    public MyHysterixCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.service = new HystrixService();
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