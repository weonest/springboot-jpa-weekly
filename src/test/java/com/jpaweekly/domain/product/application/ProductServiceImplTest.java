package com.jpaweekly.domain.product.application;

import com.jpaweekly.domain.product.dto.ProductCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void create_product_test() {
        ProductCreateRequest request = new ProductCreateRequest("주먹밥", 100);

        Long productId = productService.createProduct(request);

        assertThat(productId).isNotNull();
    }

}
