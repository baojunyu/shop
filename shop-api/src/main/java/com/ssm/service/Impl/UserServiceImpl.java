package com.ssm.service.Impl;

import com.ssm.commons.PageVo;
import com.ssm.dto.ResponseDto;
import com.ssm.dto.UserDto;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.ProductPictureMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.ProductPicture;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.utils.ErrorCode;
import com.ssm.utils.MD5Utils;
import com.ssm.vo.ProductVo;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPictureMapper productPictureMapper;
    
    @Override
    public ResponseDto login(String userAccount, String password) {
        ResponseDto responseDto=new ResponseDto();
        String s = MD5Utils.encrypt(password);
        User user = userMapper.selectByLogin(userAccount, s);
        if (user==null){
            return ResponseDto.error(ErrorCode.LOGIN_ERROR,"用户名或密码错误");

        }
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return ResponseDto.success(userDto);
    }

    @Override
    public PageVo queryPageVo(Integer page, Integer limit) {
        PageVo<ProductVo> pageVo=new PageVo<>();
        pageVo.setLimit(limit);
        pageVo.setPage(page);

        List<ProductVo> list = productMapper.selectProductPages((page - 1) * limit, limit);
        for (ProductVo p:list){
            //遍历商品  根据id查询商品的第一个图片
            Long productId = p.getProductId();
            List<ProductPicture> productPictureList=productPictureMapper.selectpurl(productId);

            if (productPictureList!=null&&productPictureList.size()>0){
                p.setPicUrl(productPictureList.get(0).getPicUrl());
            }
        }

        long l = productMapper.selectProductcounts();
        pageVo.setCount(l);
        pageVo.setData(list);
        Long ps=l%limit==0?(l/limit):(l/limit+1);
        pageVo.setPage(ps.intValue());
        return pageVo;
    }
}
