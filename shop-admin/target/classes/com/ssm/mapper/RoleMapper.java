package com.ssm.mapper;

import com.ssm.pojo.Role;
import com.ssm.pojo.RoleExample;
import com.ssm.vo.ZtreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long roleUkid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long roleUkid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleByPages(@Param("page")Integer page,
                                 @Param("limit")Integer limit,
                                 @Param("no")String no,
                                 @Param("name") String name);

    long selectRoleCount(@Param("no")String no,
                         @Param("name") String name);

    int roleActive(@Param("roleUkid")Long roleUkid, @Param("status")int i);

    List<Role> selectAll();

    Role selectbyid(Long roleUkid);

    int updaterole(Role role);

    int deletebyid(Long id);

    boolean deletebyidall(@Param("ids")Long[] ids);

    List<ZtreeVo> showTree(Long roleUkid);

}