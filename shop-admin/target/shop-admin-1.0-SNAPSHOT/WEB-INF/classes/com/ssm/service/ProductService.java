package com.ssm.service;

import com.ssm.pojo.Brand;
import com.ssm.pojo.Category;
import com.ssm.pojo.Product;
import com.ssm.vo.ResultVO;

import java.util.List;

public interface ProductService {
    ResultVO showProductsPages(Product product, Integer page, Integer limit);

    List<Category> queryallfirstCategorys();

    List<Category> queryAllFirstCategorysPatent(Integer id);

    List<Brand> querybrand();

    boolean add(Product product);

    boolean addproductpicminimages(Long id, String s);

    Product selectproductinfo(Long id);

    boolean modifyproduct(Product product);
}
