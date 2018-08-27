package com.lenovo.microservice.controller;

import com.lenovo.microservice.config.JdbcConfigBean;
import com.lenovo.microservice.pojo.Item;
import com.lenovo.microservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private JdbcConfigBean jdbcConfigBean;

    //@RequestMapping(value="/item/{id}", method = RequestMethod.GET)
    @GetMapping(value="/item/{id}")
    public Item queryItemById(@PathVariable("id") Long id) {
        return this.itemService.queryItemById(id);
    }

    @GetMapping(value="test")
    public String test() {
        return this.jdbcConfigBean.toString();
    }
}
