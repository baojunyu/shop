package com.ssm.service.Impl;

import com.ssm.mapper.PermissionMapper;
import com.ssm.pojo.Permission;
import com.ssm.service.PermissionService;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public ResultVO showPermissionPage(Integer page, Integer limit, String no, String fg) {
        List<Permission> list=permissionMapper.selectbtpage((page-1)*limit,limit,no,fg);
        long count=permissionMapper.selectbycount(no,fg);
        return ResultVO.success(count,list);
    }

    @Override
    public boolean addPermission(Permission permission) {
        permission.setAvailable("1");
        int i=permissionMapper.insert(permission);
        return i>0;
    }

    @Override
    public Permission querypermissionById(Long perId) {
        Permission permission = permissionMapper.selectByPrimaryKey(perId);
        return permission;
    }

    @Override
    public boolean modifyPermission(Permission permission) {
        int i = permissionMapper.updateByKey(permission);
        return i>0;
    }

    @Override
    public boolean deletepermissionById(Long id) {
        int i = permissionMapper.deleteByKey(id);
        return i>0;
    }

    @Override
    public boolean deletepermissionByall(Long[] ids) {
        if (ids!=null){
            int i=permissionMapper.deleteByall(ids);
            return i>0;
        }
        return false;
    }

    @Override
    public ResultVO selectPermissionadd() {
        List<Permission> list=permissionMapper.selsctfater(0);
        long l = permissionMapper.selectbyid(0);
        return ResultVO.success(l,list);
    }


    @Override
    public List<Permission> addPermissionmune() {
        List<Permission> list=permissionMapper.selsctfater(0);
        return list;
    }
}
