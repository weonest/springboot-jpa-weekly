package com.jpaweekly.domain.order.infrastructrue;

import com.jpaweekly.domain.order.OrderProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomOrderProductRepositoryImpl implements CustomOrderProductRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAll(List<OrderProduct> orderProducts) {
        SqlParameterSource[] sqlParameterSources = new SqlParameterSource[orderProducts.size()];
        int idx = 0;
        for (OrderProduct orderProduct : orderProducts) {
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("order_id", orderProduct.getOrder().getId())
                    .addValue("product_id", orderProduct.getProduct().getId())
                    .addValue("quantity", orderProduct.getQuantity())
                    .addValue("totalPrice", orderProduct.getTotalPrice());
            sqlParameterSources[idx++] = source;
        }
        String sql = """
                insert into order_product (order_id, product_id, quantity, total_price)
                values(:order_id, :product_id, :quantity, :totalPrice)""";
        jdbcTemplate.batchUpdate(sql, sqlParameterSources);
    }

}
