package poc.hystrixdemo.commands;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import poc.hystrixdemo.services.FallbackService;
import poc.hystrixdemo.services.Service;
import poc.hystrixdemo.services.Service99Available;

public class Service99AvailableCommand extends HystrixCommand<String> {

    private final String name;
    private final Service service;
    private final Service fallbackService;

    public Service99AvailableCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
        this.service = new Service99Available();
        this.fallbackService = new FallbackService();
    }

    @Override
    protected String run() {
    		return service.doGet(name);
    }
    
    @Override
    protected String getFallback() {
        return fallbackService.doGet(name);
    }
}