package com.ssm.service.Impl;

import com.ssm.commons.PageVo;
import com.ssm.controller.APIURlUtils;
import com.ssm.dto.ResponseDto;
import com.ssm.service.UserService;
import com.ssm.utils.HttpClientUtils;
import com.ssm.utils.JsonUtils;
import com.ssm.vo.ProductVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResponseDto login(String userAccount, String password) {
        Map<String,String> map=new HashMap<>();
        map.put("userAccount",userAccount);
        map.put("password",password);
        String post = HttpClientUtils.post(APIURlUtils.LOGIN, map);

        ResponseDto responseDto = JsonUtils.jsonToPojo(post, ResponseDto.class);
        return responseDto;
    }

    @Override
    public PageVo<ProductVo> quertPagevo(Integer page, Integer limit) {
        Map<String,String> map=new HashMap<>();
        map.put("page",page+"");
        map.put("limit",limit+"");

        String s = HttpClientUtils.get(APIURlUtils.QUERY_ALLPRODUCT_PAGE, map);
        PageVo pageVo = JsonUtils.jsonToPojo(s, PageVo.class);
        return pageVo;
    }
}
