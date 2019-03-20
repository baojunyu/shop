package com.ssm.controller;

import com.ssm.pojo.Role;
import com.ssm.pojo.User;
import com.ssm.service.RoleService;
import com.ssm.vo.ResultVO;
import com.ssm.vo.ZtreeVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequiresPermissions(value = {"role:view","role:query"},logical = Logical.AND)
    @RequestMapping("role/list")
    public @ResponseBody
    Object userList(@RequestParam(defaultValue = "1") Integer page,
                    @RequestParam(defaultValue = "10") Integer limit,
                    String no,
                    String name){
        ResultVO vo=roleService.showRolesPage(page,limit,no,name);
        return vo;
    }

    @RequestMapping(value = "/role/active")
    @ResponseBody
    public  Object  userActive(@RequestParam Long roleUkid,@RequestParam Boolean status){
        //激活用户
        boolean f=roleService.roleActive(roleUkid,status);
        if (f){
            return ResultVO.success();
        }
        return ResultVO.error();
    }

    @RequestMapping("/role/allroles")
    @ResponseBody
    public  Object queryAllRolse(){

        return roleService.queryAllRoles();
    }

    @RequestMapping("role/add")
    public String roleadd(Role role, Model model, HttpSession session){
        User users =(User) session.getAttribute("users");
        role.setCreateUserId(users.getUserId());
        Date date=new Date();
        role.setCreateTime(date);
        role.setStatus(1);
        boolean f=roleService.addRole(role);
        model.addAttribute("roleadd",role);
        if (f){
            return "role/roleinfo";
        }
        return "role/roleadd";
    }

    @RequestMapping("/role/modify")
    public  String  userModifyView(Long roleUkid,Model model){
        //做页面跳转
        Role role=roleService.queryrole(roleUkid);
        //存储到域对象
        model.addAttribute("rolemodify",role);

        return "role/rolemodify";
    }

    @RequestMapping("/role/domodify")
    public  String  userModifyView(Role role,Model model,HttpSession session){
        User users =(User) session.getAttribute("users");
        role.setModifyUserId(users.getUserId());
        Date date=new Date();
        role.setModifyTime(date);
        //做页面跳转
        boolean f= roleService.modifyrole(role);
        if(f){
            //存储到域对象
            Date modifyTime = role.getModifyTime();
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(modifyTime);
            Date parse = null;
            try {
                parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            role.setModifyTime(parse);
            model.addAttribute("roleadd",role);
            return "role/roleinfogai";
        }
        //存储到域对象
        model.addAttribute("rolemodify",role);
        //修改失败
        return "role/rolemodify";
    }

    @RequestMapping(value = "/role/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public  Object deletUser(@PathVariable Long id){
        //去删除操作
        boolean f=  roleService.deleteroleById(id);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");

    }

    @RequestMapping(value = "/role/deletebatch",method = RequestMethod.POST)
    @ResponseBody
    public  Object deletUser(@RequestParam("ids") Long[] ids){
        //去删除操作
        boolean f=  roleService.deleteBatch(ids);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");

    }

//    @RequiresPermissions("role:showtree")
    @RequestMapping("/role/ztree")
    @ResponseBody
    public Object roleList(@RequestParam Long roleUkid){
        List<ZtreeVo> list=roleService.showZtree(roleUkid);
        return list;
    }

//    @RequiresPermissions("role:updatepermission")
    @RequestMapping("/role/editpermission")
    @ResponseBody
    public Object editpermission(@RequestParam Long rid,Long[] aids){
        boolean f=roleService.edirTree(rid,aids);
        return f?ResultVO.success():ResultVO.error();
    }

}
