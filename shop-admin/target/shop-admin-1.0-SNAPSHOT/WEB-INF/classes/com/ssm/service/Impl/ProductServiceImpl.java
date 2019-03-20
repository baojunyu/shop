package com.ssm.service.Impl;

import com.ssm.mapper.BrandMapper;
import com.ssm.mapper.CategoryMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.ProductPictureMapper;
import com.ssm.pojo.*;
import com.ssm.service.ProductService;
import com.ssm.vo.ProductVo;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private  ProductPictureMapper productPictureMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;
    @Override
    public ResultVO showProductsPages(Product product, Integer page, Integer limit) {
        List<ProductVo> list=productMapper.selectProductPages(product,(page-1)*limit,limit);

        for (ProductVo pvo:list){
            ProductPictureExample example=new ProductPictureExample();
            example.createCriteria().andProductIdEqualTo(pvo.getProductId());
            List<ProductPicture> productPictureList=productPictureMapper.selectByExample(example);

            if (productPictureList!=null&&productPictureList.size()>0){
                pvo.setPicUrl(productPictureList.get(0).getPicUrl());
            }
        }
        //查询个数
        Long count=productMapper.selectProductcounts(product);
        return ResultVO.success(count,list);
    }


    @Override
    public List<Category> queryallfirstCategorys() {
        List<Category> list=categoryMapper.selectbyfirst(1);
        return list;
    }

    @Override
    public List<Category> queryAllFirstCategorysPatent(Integer id) {

        List<Category> list=categoryMapper.selectbysecong(id);
        return list;
    }

    @Override
    public List<Brand> querybrand() {
        List<Brand> list=brandMapper.selectallbrand();
        return list;
    }

    @Override
    public boolean add(Product product) {
        product.setProductionDate(new Date());
        product.setAuditStatus(0);
        product.setModifiedTime(new Date());
        product.setIndate(new Date());
        product.setPublishStatus(0);

        return productMapper.insertproduct(product)>0;
    }

    @Override
    public boolean addproductpicminimages(Long id, String s) {
        ProductPicture productPicture=new ProductPicture();
        productPicture.setProductId(id);
        productPicture.setPicUrl(s);
        productPicture.setIsMaster(1);
        productPicture.setPicOrder(0);
        productPicture.setPicStatus(1);
        productPicture.setModifiedTime(new Date());
        int insert = productPictureMapper.insert(productPicture);
        return insert>0;
    }

    @Override
    public Product selectproductinfo(Long id) {
        Product product=productMapper.selectbyid(id);
        return product;
    }

    @Override
    public boolean modifyproduct(Product product) {
        int i=productMapper.updatebyid(product);
        return i>0;
    }
}
