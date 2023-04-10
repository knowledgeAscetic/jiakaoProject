package com.wang.jk.controller;

import com.wang.jk.filter.ErrorFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController {

    @RequestMapping(ErrorFilter.ERROR_URL)
    public void  handle(HttpServletRequest request) throws Exception {
        throw (Exception) request.getAttribute("error");

    }
}
