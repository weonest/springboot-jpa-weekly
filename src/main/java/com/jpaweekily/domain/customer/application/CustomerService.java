package com.jpaweekily.domain.customer.application;

import com.jpaweekily.domain.customer.dto.CustomerRequest;
import com.jpaweekily.domain.customer.dto.CustomerResponse;
import com.jpaweekily.domain.customer.dto.CustomerUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    Long create(CustomerRequest request);

    CustomerResponse findCustomerById(Long id);

    Page<CustomerResponse> findCustomersWithPaging(Pageable pageable);

    void update(Long id, CustomerUpdate request);

    void delete(Long id);

}
