package serviceproduct.controller;


import model.product.bean.Product;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable("id") Long pid){
        Product p = new Product();
        p.setId(1L);
        p.setProductName("测试商品1");
        p.setNum(3);
        p.setPrice(new BigDecimal(5.5));
        return p;
    }
}
