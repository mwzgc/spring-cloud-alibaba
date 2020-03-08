package com.mwz.s2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplate restTemplate1;

    @Autowired
    private EchoService echoService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private MyService myService;

    // @PostConstruct
    // public void init() {
    // restTemplate1.setErrorHandler(new ResponseErrorHandler() {
    // @Override
    // public boolean hasError(ClientHttpResponse response) throws IOException {
    // return false;
    // }
    //
    // @Override
    // public void handleError(ClientHttpResponse response) throws IOException {
    // System.err.println("handle error");
    // }
    // });
    // }

    @GetMapping("/addUser")
    public String addUser() {
        myService.addUser();
        return "success";
    }

    @GetMapping("/echo-rest/{str}")
    public String rest(@PathVariable String str) {
        return restTemplate.getForObject("http://service-provider/echo/" + str,
                String.class);
    }

    @GetMapping("/index")
    public String index() {
        return restTemplate1.getForObject("http://service-provider", String.class);
    }

    @GetMapping("/test")
    public String test() {
        return restTemplate1.getForObject("http://service-provider/test", String.class);
    }

    @GetMapping("/sleep")
    public String sleep() {
        return restTemplate1.getForObject("http://service-provider/sleep", String.class);
    }

    @GetMapping("/notFound-feign")
    public String notFound() {
        return echoService.notFound();
    }

    @GetMapping("/divide-feign")
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        return echoService.divide(a, b);
    }

    @GetMapping("/divide-feign2")
    public String divide(@RequestParam Integer a) {
        return echoService.divide(a);
    }

    @GetMapping("/echo-feign/{str}")
    public String feign(@PathVariable String str) {
        return echoService.echo(str);
    }

    @GetMapping("/services/{service}")
    public Object client(@PathVariable String service) {
        return discoveryClient.getInstances(service);
    }

    @GetMapping("/services")
    public Object services() {
        return discoveryClient.getServices();
    }

}