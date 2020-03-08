package com.mwz.s1;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SentinelController {

    private Random random = new Random();

    @GetMapping("/s/t")
    @SentinelResource(value = "sentinel_test", fallback = "fallback")
    public String sentinelTest() {
        sleep(50);
        return "hi";
    }

    public String fallback() {
        return "error";
    }

    private void sleep(int n) {
        int s = Math.abs(random.nextInt()) % n + 1;
        try {
            Thread.sleep(s);
        } catch (Exception ex) {

        }
    }

}

