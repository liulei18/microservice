package com.lenovo.microservice.order.service;

import com.lenovo.microservice.order.OrderProperties;
import com.lenovo.microservice.order.feign.ItemFeignClient;
import com.lenovo.microservice.order.pojo.Item;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ItemService {

    //Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;

//    @Value("${lenovo.item.url}")
//    private String lenovoItemUrl;

    @Autowired
    private OrderProperties orderProperties;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ItemFeignClient itemFeignClient;

    /**
     * 调用商品的微服务提供的接口进行查询数据
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById(Long id) {
        return this.itemFeignClient.queryItemById(id);
    }


    /*@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") // 进行容错处理
    public Item queryItemById(Long id) {
        String serviceId = "lenovo-microservice-item";
        return this.restTemplate.getForObject("http://"+serviceId+"/item/"+id,Item.class);
    }*/

    public Item queryItemByIdFallbackMethod(Long id){ // 请求失败执行的方法
        return new Item(id, "查询商品信息出错!", null, null, null);
    }

   /* public Item queryItemById(Long id) {
        String serviceId = "lenovo-microservice-item";
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
        if (instances.isEmpty()){
            return null;
        }
        //为了演示，在这里只获取一个实例
        ServiceInstance serviceInstance = instances.get(0);
        String url = serviceInstance.getHost()+":"+serviceInstance.getPort();
        return this.restTemplate.getForObject("http://"+url+"/item/"+id,Item.class);
    }*/
    /*public Item queryItemById(Long id) {
        String url = this.orderProperties.getItem().getUrl() + id;
        return this.restTemplate.getForObject(url, Item.class);
    }*/


}
