package com.shawa.web;

//import com.shawa.IngredientRef;

import com.shawa.Shawa;
import com.shawa.ShawaOrder;
import com.shawa.ShawaUDT;
import com.shawa.data.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/orders")
@SessionAttributes("shawaOrder")  // указывает, что объект ShawaOrder, объявленный в классе чуть ниже, должен поддерживаться на уровне сеанса. Это важно, потому что создание шаурмы также является первым шагом в создании заказа, и созданный нами заказ необходимо будет перенести в сеанс, охватывающий несколько запросов.
public class OrderController {

    OrderRepository orderRepository;
    
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@ModelAttribute @Valid ShawaOrder order, Errors errors,
                        SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "/orderForm";
        }
        log.info("Order submitted: {}", order);
        orderRepository.save(order);    // TODO NOW: all is ok when debug, why not save shawas
        log.info("Needed ingredients: {}", order.getShawas()
                .stream()
                .flatMap(shawa -> shawa.getIngredients().stream())
                .collect(Collectors.toSet()));

//        log.info("Needed ingredients: {}", order.getShawas()
//                .stream()
//                .map(shawa -> shawa.getIngredientRefs().stream()
//                        .map(IngredientRef::getIngredient))
//                .collect(Collectors.toSet()));

        sessionStatus.setComplete();
        return "redirect:/";
    }
}
