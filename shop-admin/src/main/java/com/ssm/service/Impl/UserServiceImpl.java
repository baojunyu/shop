package com.ssm.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.UserRoleMapper;
import com.ssm.pojo.Role;
import com.ssm.pojo.User;
import com.ssm.pojo.UserExample;
import com.ssm.pojo.UserRoleExpand;
import com.ssm.service.UserService;
import com.ssm.utils.MD5Utils;
import com.ssm.vo.ResultVO;
import com.ssm.vo.UserRoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public ResultVO showUsersPage(Integer page, Integer limit, String no, String mobileNumber, Integer status) {
        //1.分页数据  data
        List<UserRoleVO> data=new ArrayList<>();

        //条件查询      有无条件都查询出数据
        List<UserRoleExpand> list= userMapper.selectUserAndRoleByPages((page-1)*limit,limit,no,mobileNumber,status);
        for (UserRoleExpand ur:list){     //把条件查询的结果遍历
            UserRoleVO vo=new UserRoleVO();
            vo.setRoleList(ur.getRoleList());   //结果用户的角色列表赋给数据显示类
            //赋值两个对象相同的属性
            BeanUtils.copyProperties(ur,vo);    //用户的其他信息赋给vo

            //该用户拥有的所有角色信息
            List<Role> roleList = ur.getRoleList();
            for (Role r:roleList){
                //把当前角色id添加到vo的角色id集合里面
                vo.getRids().add(r.getRoleUkid());
                vo.getRnames().add(r.getRoleName());
            }

            data.add(vo);
        }
        //2.查询分页 总个数 count
        long count= userMapper.selectUserAndRoleCount(no,mobileNumber,status);
        return ResultVO.success(count,data);
    }

    @Override
    public ResultVO showUsersPageTest(Integer page, Integer limit) {
        //开启分页
        PageHelper.startPage(page, limit);
        //查询所有用户数据
        List<User> list = userMapper.selectByExample(null);

        //查询个数
        long l = userMapper.countByExample(null);
        return ResultVO.success(l,list);
    }

    //逆向工程登录
    @Override
    public User login(String userAccount, String password) {
        //密码加密
        String encrypt = MD5Utils.encrypt(password);
        //调用mapper
        UserExample example = new UserExample();
        example.createCriteria().andUserAccountEqualTo(userAccount)
                .andPasswordEqualTo(encrypt)
                .andStatusEqualTo(1);
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            return  users.get(0);
        }
        return null;
    }

    @Override
    public boolean modifyUserHeadImage(Long userId, String uploadStr) {
        if(userId!=null){
            return userMapper.modifyHeadImage(userId,uploadStr)>0;
        }
        return false;
    }

    @Override
    public int changepass(Integer userid,User user) {
        long l = userid.longValue();
        user.setUserId(l);
        int i = userMapper.updateUser(user);
        return i;
    }

    //自己编写登录
    @Override
    public User login1(String userAccount, String password) {
        //密码加密
        String encrypt = MD5Utils.encrypt(password);
        User user = userMapper.selectByLogin(userAccount, encrypt);
        if (user!=null){
            return user;
        }
        return null;
    }

    @Override
    public boolean checkAccount(String userAccount) {
        User user=userMapper.selectByaccount(userAccount);
        System.out.println(user.toString());
        if (user!=null){
            return true;
        }
        if (user==null){
            return false;
        }
        return false;
    }

    @Override
    public User queryUserById(Long userId) {
        if (userId!=null){
            return userMapper.selectbyid(userId);
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        user.setPassword(MD5Utils.encrypt(user.getPassword()));
        user.setCreateTime(new Date());
        int i=userMapper.insertSelective(user);
        return i>0;
    }

    @Override
    public boolean modifyUser(User user) {
        user.setModifyTime(new Date());
        int i = userMapper.updateUser(user);
        return i>0;
    }


    @Override
    public boolean deleteUserById(Long id) {
        if(id==null){
            return false;
        }

        boolean f=userMapper.deleteByid(id);
        return  f;
    }

    @Override
    public boolean deleteBatch(Long[] ids) {
        if(ids!=null&&ids.length>0){
            return  userMapper.deleteBatch(ids);

        }
        return false;
    }

    @Override
    public boolean userActive(Long userId, Boolean status) {
        if (userId!=null&&status!=null){
            return userMapper.userActive(userId,status?1:0)>0;
        }
        return false;
    }

    @Override
    public boolean editUserRole(Long userId, Long[] rids) {
        if (userId!=null){
            //先去删除用户拥有的所有角色信息
            userRoleMapper.deleUserRolesByUid(userId);
            if(rids!=null&&rids.length>0){
                //新增
                userRoleMapper.insertBatch(userId,rids);
            }
            return true;
        }
        return false;
    }
}
