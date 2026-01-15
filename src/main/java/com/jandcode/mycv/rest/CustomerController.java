package com.jandcode.mycv.rest;

import com.jandcode.mycv.entity.Customer;
import com.jandcode.mycv.entity.response.GeneralSuccessResponse;
import com.jandcode.mycv.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @PostMapping("/save")
    public ResponseEntity<GeneralSuccessResponse> save(
            @RequestBody Customer customer,
            HttpServletRequest request
    ) {

        String ip = request.getRemoteAddr();

        customerService.save(customer, ip);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GeneralSuccessResponse(
                        "Customer guardado correctamente",
                        HttpStatus.CREATED.value()
                ));

    }
}
