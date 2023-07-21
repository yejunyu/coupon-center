package com.yejunyu.coupon.customer.feign;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.calculation.api.beans.SimulationOrder;
import com.yejunyu.coupon.calculation.api.beans.SimulationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/20
 * @Description: TODO
 **/
@FeignClient(value = "coupon-calculation-serv", path = "/calculation")
public interface CalculationService {

    @PostMapping("/checkout")
    ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart order);

    @PostMapping("/simulate")
    SimulationResponse simulate(@RequestBody SimulationOrder order);
}
