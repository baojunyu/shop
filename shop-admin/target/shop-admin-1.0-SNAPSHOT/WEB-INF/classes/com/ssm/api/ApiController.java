package com.ssm.api;

import com.ssm.pojo.Product;
import com.ssm.service.ProductService;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApiController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/api/products",method = RequestMethod.GET)
    @ResponseBody
    public  Object getAllProducts(@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer limit){
        ResultVO vo=productService.showProductsPages(new Product(),page,limit);
        return vo;
    }

    @RequestMapping(value = "/api/product/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object getProductsbyid(@PathVariable Long id){
        Product selectproductinfo = productService.selectproductinfo(id);
        return selectproductinfo;
    }

    @RequestMapping(value = "/api/product",method = RequestMethod.POST)
    @ResponseBody
    public Object getProduct(String name,Double price,String color){
        System.out.println(name+"--------"+price+"--------"+color);
        return ResultVO.success();
    }

    @RequestMapping(value = "/api/deleproduct/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object getPruduct4(@PathVariable Long id){
        System.out.println(id+"deleteid-------------->");
        return ResultVO.success();
    }

    @RequestMapping(value = "/api/putproduct/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Object testput(@PathVariable Long id,Product product){
        System.out.println(id+"--------->"+product.toString());

        return ResultVO.success();
    }
}
