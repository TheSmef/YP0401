package com.example.YP.Controllers;

import com.example.YP.Models.*;
import com.example.YP.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER')")
public class OrderController {

    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OrderPurchaseRepository orderPurchaseRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/order")
    public String main(Model model){
        Iterable<Point> listPoint = pointRepository.findAll();
        model.addAttribute("listPoint", listPoint);
        return "point/Index";
    }

    @GetMapping("order/filter-direct")
    public String Filter(@RequestParam String searchName,
                         Model model){
        Iterable<Point> listPoint = pointRepository.findByAddress(searchName);
        model.addAttribute("listPoint", listPoint);
        return "point/Index";
    }

    @GetMapping("order/filter-contains")
    public String FilterContains(@RequestParam String searchName,
                                 Model model){
        Iterable<Point> listPoint = pointRepository.findByAddressContaining(searchName);
        model.addAttribute("listPoint", listPoint);
        return "point/Index";
    }

    @GetMapping("order/add")
    public String pointAddView(Model model, Point point){
        return "point/add";
    }
    @PostMapping("order/add")
    public String postAdd(@Valid Point point,
                          BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("point", point);
            return "point/add";
        }
        pointRepository.save(point);
        return "redirect:/order";
    }
    @GetMapping("order/detail/{id}")
    public String Detail(@PathVariable() Long id, Model model){
        Point point = pointRepository.findById(id).orElseThrow();
        point.setOrderPurchases(orderPurchaseRepository.findByPoint(point));
        model.addAttribute("el", point);
        return "point/point-details";
    }

    @GetMapping("order/edit/{id}")
    public String Edit(@PathVariable() Long id, Model model){
        Point point = pointRepository.findById(id).orElseThrow();
        model.addAttribute("point", point);
        return "point/edit";
    }

    @PostMapping("order/edit/{id}")
    public String EditConc(@Valid Point point, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("point", point);
            return "point/edit";
        }
        pointRepository.save(point);
        return "redirect:/order/detail/" + point.getId();
    }

    @GetMapping("order/del/{id}")
    public String Delete(@PathVariable() Long id){
        pointRepository.deleteById(id);
        return "redirect:/order";
    }

    @GetMapping("order/order-add/{id}")
    public String orderAddView(Model model){
        Iterable<Employee> em = employeeRepository.findAll();
        model.addAttribute("employee", em);
        return "point/order-add";
    }
    @PostMapping("order/order-add/{id}")
    public String orderAdd(@PathVariable() Long id, @RequestParam(name = "emp") Long idem ,Model model){
        OrderPurchase orderPurchase = new OrderPurchase();
        orderPurchase.setDate(new Date());
        orderPurchase.setEmployee(employeeRepository.findById(idem).orElseThrow());
        orderPurchase.setPoint(pointRepository.findById(id).orElseThrow());
        orderPurchaseRepository.save(orderPurchase);
        return "redirect:/order/detail/" + id;
    }
    @GetMapping("order/order-details/{id}")
    public String orderDetails(@PathVariable() Long id, Model model){
        model.addAttribute("order", orderPurchaseRepository.findById(id).orElseThrow());
        return "point/order-details";
    }


    @GetMapping("order/order-del/{id}")
    public String orderDelete(@PathVariable() Long id, Model model){
        OrderPurchase orderPurchase = orderPurchaseRepository.findById(id).orElseThrow();
        orderPurchaseRepository.delete(orderPurchase);
        return "redirect:/order/detail/" + orderPurchase.getPoint().getId();
    }

    @GetMapping("order/orderproduct-delete/{id}")
    public String orderProductDelete(@PathVariable() Long id, Model model){
        OrderProduct orderProduct = orderProductRepository.findById(id).orElseThrow();
        orderProductRepository.delete(orderProduct);
        return "redirect:/order/order-details/" + orderProduct.getOrderPurchase().getId();
    }
    @GetMapping("order/orderproduct-add/{id}")
    public String orderProductAddView(@PathVariable() Long id, Model model){
        Iterable<Product> em = productRepository.findAll();
        model.addAttribute("product", em);
        return "point/orderproduct-add";
    }
    @PostMapping("order/orderproduct-add/{id}")
    public String orderProductAdd(@PathVariable() Long id, @RequestParam(name = "product") Long idem, @RequestParam() int amount, Model model){
        if (amount <= 0){
            Iterable<Product> em = productRepository.findAll();
            model.addAttribute("product", em);
            model.addAttribute("error", "Количество товара в заказе не может быть 0 или меньше");
            return "point/orderproduct-add";
        }
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(productRepository.findById(idem).orElseThrow());
        orderProduct.setAmount(amount);
        orderProduct.setOrderPurchase(orderPurchaseRepository.findById(id).orElseThrow());
        orderProductRepository.save(orderProduct);
        return "redirect:/order/order-details/" + id;
    }
}
