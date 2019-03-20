package com.ssm.mapper;

import com.ssm.pojo.Permission;
import com.ssm.pojo.PermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Long perId);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long perId);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectbtpage(@Param("page")Integer page,
                                  @Param("limit")Integer limit,
                                  @Param("no")String no,
                                  @Param("fg")String fg);

    long selectbycount(@Param("no")String no, @Param("fg")String fg);

    int updateByKey(Permission permission);

    int deleteByKey(Long id);

    int deleteByall(@Param("ids")Long[] ids);

    List<Permission> selsctfater(int i);

    long selectbyid(int i);
}