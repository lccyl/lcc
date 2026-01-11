package serviceproduct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;


@SpringBootTest
public class MyTest {

    @Autowired
    DiscoveryClient discoveryClient;
    @Test
    public void test(){
        List<String> ls = discoveryClient.getServices();
        for (String s : ls) {
            System.out.println(s);
            List<ServiceInstance> si = discoveryClient.getInstances(s);
            for (ServiceInstance serviceInstance : si) {
                System.out.println(serviceInstance.getHost()+" "+serviceInstance.getPort());
            }

        }


    }
}
