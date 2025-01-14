package com.jpaweekly.domain.customer;

import com.jpaweekly.domain.customer.dto.CustomerRequest;
import com.jpaweekly.domain.customer.dto.CustomerResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomerConverter {

    public static CustomerResponse convertEntityToResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName());
    }

    public static Customer convertRequestToEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();
    }
}
