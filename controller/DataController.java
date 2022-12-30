package com.example.mvcdemo.controller;

import com.example.mvcdemo.service.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("/vue-manage-system")
    public String toTest1(){
        return "test1";
    }

    @RequestMapping("/guide-rpc-framework")
    public String toTest2(){
        return "test2";
    }

}
