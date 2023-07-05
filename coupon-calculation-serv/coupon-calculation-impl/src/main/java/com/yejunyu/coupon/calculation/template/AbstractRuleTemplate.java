package com.yejunyu.coupon.calculation.template;

import com.yejunyu.coupon.calculation.api.beans.Product;
import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/4
 * @Description: TODO
 **/
@Slf4j
public abstract class AbstractRuleTemplate implements RuleTemplate {
    @Override
    public ShoppingCart calculation(ShoppingCart order) {
        // 获取订单总价
        Long orderTotalAmount = getTotalPrice(order.getProducts());
        // 获取以shopId为维度的总价统计
        Map<Long, Long> sumAmount = getTotalPriceGroupByShop(order.getProducts());
        // 获取优惠券
        CouponTemplateInfo template = order.getCouponInfos().get(0).getTemplate();
        // 查询最低消费限制
        Long threshold = template.getRule().getDiscount().getThreshold();
        Long quota = template.getRule().getDiscount().getQuota();
        // 计算优惠金额, 如果shopId未指定,则按总金额计算,否则要筛选shopId的优惠券
        Long shopId = template.getShopId();
        Long shopTotalAmount = (shopId == null) ? orderTotalAmount : sumAmount.get(shopId);
        // 如果不符合优惠券使用标准,则按原价走,不使用优惠券
        if (shopTotalAmount == null || shopTotalAmount < threshold) {
            order.setCost(orderTotalAmount);
            order.setCouponInfos(Collections.emptyList());
            return order;
        }
        // 子类中实现calculateNewPrice计算新的价格
        Long newCost = calculateNewPrice(orderTotalAmount, shopTotalAmount, quota);
        // 订单价格不能小于1分钱
        if (newCost < minCost()) {
            newCost = minCost();
        }
        order.setCost(newCost);
        log.debug("AbstractRuleTemplate#calculation original price={}, new price={}", orderTotalAmount, newCost);
        return order;
    }

    /**
     * 1 分钱
     *
     * @return
     */
    public Long minCost() {
        return 1L;
    }

    private Map<Long, Long> getTotalPriceGroupByShop(List<Product> products) {
        return products.stream()
                .collect(groupingBy(Product::getShopId, summingLong(p -> p.getPrice() * p.getCount())));
    }

    private Long getTotalPrice(List<Product> products) {
        return products.stream()
                .mapToLong(p -> p.getPrice() * p.getCount())
                .sum();
    }

    /**
     * 不同优惠券策略计算最终价格
     * 金额计算具体逻辑,延迟到子类实现
     *
     * @param orderTotalAmount 总金额
     * @param shopTotalAmount  店铺总金额
     * @param quota            折扣
     * @return 新价格
     */
    protected abstract Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota);

    protected long convertToDecimal(double value) {
        return new BigDecimal(value).setScale(0, RoundingMode.HALF_UP).longValue();
    }
}
