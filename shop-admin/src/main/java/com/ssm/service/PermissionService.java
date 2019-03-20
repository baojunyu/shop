package com.ssm.service;

import com.ssm.pojo.Permission;
import com.ssm.vo.ResultVO;

import java.util.List;

public interface PermissionService {
    ResultVO showPermissionPage(Integer page, Integer limit, String no, String fg);

    boolean addPermission(Permission permission);

    Permission querypermissionById(Long perId);

    boolean modifyPermission(Permission permission);

    boolean deletepermissionById(Long id);

    boolean deletepermissionByall(Long[] ids);

    ResultVO selectPermissionadd();

    List<Permission> addPermissionmune();
}
