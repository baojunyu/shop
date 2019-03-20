package com.ssm.mapper;

import com.ssm.pojo.ProductPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductPictureMapper {

    List<ProductPicture> selectpurl(Long productId);
}