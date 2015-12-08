package com.beehive.erp.taobao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuzhikai on 2015/12/7.
 */
@Controller
@RequestMapping({"/testData"})
public class TestDataController extends BaseController{
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TestDataController.class);

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test( @RequestBody String data){
       return "test:"+data;
    }
}
