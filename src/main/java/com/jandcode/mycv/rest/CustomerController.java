package com.jandcode.mycv.rest;

import com.jandcode.mycv.entity.Customer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @PostMapping("/save")
    public ResponseEntity<Void> saveCustomer(
            @RequestBody Customer request){
        return ResponseEntity.ok().build();
    }
}
