package com.medical.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ewrfcas on 2017/3/2.
 */
@Controller
@SuppressWarnings("unused")
@Slf4j
public class DefaultController {
    @RequestMapping(value = "/")
    public String defaultPage(Model model) {return "index";}
}
