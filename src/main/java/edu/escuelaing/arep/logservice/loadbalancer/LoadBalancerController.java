package edu.escuelaing.arep.logservice.loadbalancer;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/loadbalancer")
public class LoadBalancerController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final List<String> serviceInstances = List.of(
            "http://localhost:35001/messages",
            "http://localhost:35002/messages",
            "http://localhost:35003/messages"
    );
    private final AtomicInteger counter = new AtomicInteger(0);

    private String getNextInstance() {
        int index = counter.getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }

    @PostMapping("/messages")
    public String postMessage(@RequestBody String message) {
        String instanceUrl = getNextInstance();
        System.out.println("Redirigiendo la solicitud a: " + instanceUrl);
        return restTemplate.postForObject(instanceUrl, message, String.class);
    }

    @GetMapping("/messages/last10")
    public String getLast10Messages() {
        String instanceUrl = getNextInstance();
        System.out.println("Redirigiendo la solicitud a: " + instanceUrl);
        return restTemplate.getForObject(instanceUrl + "/last10", String.class);
    }
}
