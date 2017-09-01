package poc.hystrixdemo.commands;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import poc.hystrixdemo.services.FallbackService;
import poc.hystrixdemo.services.IService;
import poc.hystrixdemo.services.LoveService;
import poc.hystrixdemo.services.LuvService;
import poc.hystrixdemo.services.Service;
import poc.hystrixdemo.services.Service99Available;
import poc.hystrixdemo.services.WeService;

public class LoveCommand extends HystrixCommand<String> {

    private final Service service;
    private final Service fallbackService;

    public LoveCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.service = new LoveService();
        this.fallbackService = new LuvService();
    }

    @Override
    protected String run() {
    	return service.doGet();
    	
    }
    
    @Override
    protected String getFallback() {
        return fallbackService.doGet();
    }
}