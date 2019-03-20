package com.ssm.mapper;

import com.ssm.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByLogin(@Param("user_account") String userAccount, @Param("password") String password);

    int updateUser(User user);

    int modifyHeadImage(@Param("uid") Long userId,@Param("headUrl") String uploadStr);

    List<UserRoleExpand> selectUserAndRoleByPages(@Param("page")Integer page,
                                                  @Param("limit")Integer limit,
                                                  @Param("no")String no,
                                                  @Param("mobileNumber") String mobileNumber,
                                                  @Param("status") Integer status);

    long selectUserAndRoleCount(@Param("no")String no,
                                @Param("mobileNumber") String mobileNumber,
                                @Param("status") Integer status);

    User selectByaccount(String userAccount);

    User selectbyid(Long userId);

    boolean deleteByid(Long id);

    boolean deleteBatch(@Param("ids") Long[] ids);

    int userActive(@Param("userId")Long userId, @Param("status")int i);

    List<Role> selectAllRolesByAccount(String userAccount);

    List<Permission> selectAllPermissionsByAccount(String userAccount);
}