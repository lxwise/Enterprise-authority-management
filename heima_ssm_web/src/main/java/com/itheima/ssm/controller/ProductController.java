package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-24 13:23
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Product> list = productService.findAll();
        mv.addObject("productList",list);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 保存商品
     * @param product
     * @return
     */
    @RequestMapping("/save.do")
    public String saveProduct(Product product){
    productService.saveProduct(product);
    return "redirect:findAll.do";
    }
}
