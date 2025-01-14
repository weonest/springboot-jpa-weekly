package com.jpaweekly.domain.customer.presentation;

import com.jpaweekly.domain.customer.application.CustomerService;
import com.jpaweekly.domain.customer.dto.CustomerRequest;
import com.jpaweekly.domain.customer.dto.CustomerResponse;
import com.jpaweekly.domain.customer.dto.CustomerUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerApiController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> customerPage(@PageableDefault() Pageable pageable) {
        Page<CustomerResponse> customers = customerService.findCustomersWithPaging(pageable);
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<Long> customerCreate(@RequestBody @Valid CustomerRequest request) {
        Long id = customerService.create(request);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> customerUpdate(@PathVariable Long id, @RequestBody CustomerUpdate request) {
        customerService.update(id, request);
        return ResponseEntity.ok().build();
    }

}
