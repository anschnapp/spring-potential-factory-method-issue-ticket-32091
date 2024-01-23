package com.example.demo;

public class ServiceWithDependency {
    private final InnerService innerService;

    public ServiceWithDependency(InnerService innerService) {
        this.innerService = innerService;
    }

    public void test() {
        // delegate to service 1
        innerService.test();
    }
}
