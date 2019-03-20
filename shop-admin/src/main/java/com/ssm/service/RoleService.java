package com.ssm.service;

import com.ssm.pojo.Role;
import com.ssm.vo.ResultVO;
import com.ssm.vo.ZtreeVo;

import java.util.List;

public interface RoleService {
    ResultVO showRolesPage(Integer page, Integer limit, String no, String name);

    boolean roleActive(Long roleUkid, Boolean status);

    List<Role> queryAllRoles();

    boolean addRole(Role role);

    Role queryrole(Long roleUkid);

    boolean modifyrole(Role role);

    boolean deleteroleById(Long id);

    boolean deleteBatch(Long[] ids);

    List<ZtreeVo> showZtree(Long roleUkid);

    boolean edirTree(Long rid, Long[] aids);
}
