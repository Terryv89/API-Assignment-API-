package com.example.customerapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspController {

    @GetMapping("/customerJspList")
    public String showCustomerJsp(){
        return "Customer.jsp";
    }
}
