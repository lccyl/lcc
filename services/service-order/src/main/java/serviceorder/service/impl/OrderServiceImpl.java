package serviceorder.service.impl;

import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import model.order.bean.Order;
import model.product.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import serviceorder.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Override
    public Order createOrder(Order order) {

        Product product = getProduct(1L);
        //List<Product> lp = new ArrayList<>();
        //lp.add(product);

        order.setTotalAmount(product.getPrice());
        order.setProductList(Arrays.asList(product));

        return order;
    }

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DiscoveryClient discoveryClient;
    public Product getProduct(Long productId){
        ServiceInstance instance =  discoveryClient.getInstances("service-product").get(0);
        String url = "http://"+instance.getHost()+":"+instance.getPort()+"/product/get/1";
        log.info(url);
        Product product = restTemplate.getForObject(url,Product.class);
        return product;
    }
    @Autowired
    LoadBalancerClient loadBalancerClient;
    public Product getProductLoadBalance(Long productId){
        ServiceInstance instance =  loadBalancerClient.choose("service-product");
        String url = "http://"+instance.getHost()+":"+instance.getPort()+"/product/get/1";
        log.info(url);
        Product product = restTemplate.getForObject(url,Product.class);
        return product;
    }

    public Product getProductLoadBalance2(Long productId){
        String url = "http://"+"service-product"+"/product/get/1";
        Product product = restTemplate.getForObject(url,Product.class);
        return product;
    }
}
