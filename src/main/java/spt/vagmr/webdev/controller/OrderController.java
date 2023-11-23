package spt.vagmr.webdev.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spt.vagmr.webdev.vo.Order;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/30-13:04
 * springBootProject
 * @Description
 */
@RestController
public class OrderController {
    @PostMapping("/order/new")
    public String newOrder(@Validated @RequestBody Order order){
        return "订单创建成功,订单id为%s".formatted(order.getOrderId());
    }
}
