package poc.hystrixdemo.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import poc.hystrixdemo.services.Service;
import poc.hystrixdemo.services.Service1;

public class Service1Command extends HystrixCommand<String> {

    private final String name;
    private final Service service;

    public Service1Command(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
        this.service = new Service1();
    }

    @Override
    protected String run() {
        return service.doGet(name);
    }
}