package com.ssm.service.Impl;

import com.ssm.mapper.RoleMapper;
import com.ssm.mapper.RolePermissionMapper;
import com.ssm.pojo.Role;
import com.ssm.service.RoleService;
import com.ssm.vo.ResultVO;
import com.ssm.vo.ZtreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public ResultVO showRolesPage(Integer page, Integer limit, String no, String name) {
        List<Role> list= roleMapper.selectRoleByPages((page-1)*limit,limit,no,name);
        long count= roleMapper.selectRoleCount(no,name);
        return ResultVO.success(count,list);
    }

    @Override
    public boolean roleActive(Long roleUkid, Boolean status) {
        if (roleUkid!=null&&status!=null){
            return roleMapper.roleActive(roleUkid,status?1:0)>0;
        }
        return false;
    }

    @Override
    public List<Role> queryAllRoles() {

        return roleMapper.selectAll();
    }

    @Override
    public boolean addRole(Role role) {
        int i=roleMapper.insert(role);
        return i>0;
    }

    @Override
    public Role queryrole(Long roleUkid) {
        Role role=roleMapper.selectbyid(roleUkid);
//        Role role1 = roleMapper.selectByPrimaryKey(roleUkid);
        return role;
    }

    @Override
    public boolean modifyrole(Role role) {
//        int i = roleMapper.updateByPrimaryKey(role);
        int i1=roleMapper.updaterole(role);
        return i1>0;
    }

    @Override
    public boolean deleteroleById(Long id) {
//        int i = roleMapper.deleteByPrimaryKey(id);
        int i1=roleMapper.deletebyid(id);
        return i1>0;
    }

    @Override
    public boolean deleteBatch(Long[] ids) {
        return roleMapper.deletebyidall(ids);
    }

    @Override
    public List<ZtreeVo> showZtree(Long roleUkid) {
        if (roleUkid==null){
            return null;
        }
        List<ZtreeVo> zlist=roleMapper.showTree(roleUkid);
        return zlist;
    }

    @Override
    public boolean edirTree(Long rid, Long[] aids) {
        if (rid!=null){
            //删除
            rolePermissionMapper.deletePermission(rid);
            if (aids!=null && aids.length>0){
                //批量新增
                rolePermissionMapper.addBatch(rid,aids);
            }
            return true;
        }
        return false;
    }
}
