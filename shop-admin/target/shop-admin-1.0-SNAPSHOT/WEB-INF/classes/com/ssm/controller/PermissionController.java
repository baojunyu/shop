package com.ssm.controller;

import com.ssm.pojo.Permission;
import com.ssm.service.PermissionService;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("permission/list")
    public @ResponseBody
    Object permissionList(@RequestParam(defaultValue = "1") Integer page,
                    @RequestParam(defaultValue = "10") Integer limit,
                    String no,
                    String fg){
        ResultVO vo=permissionService.showPermissionPage(page,limit,no,fg);
        return vo;
    }

    @RequestMapping(value = "permission/menus",method = RequestMethod.GET)
    public @ResponseBody
    Object permissionaddtype(){
        return permissionService.addPermissionmune();
    }



    @RequestMapping("permission/add")
    public String Permissionadd(Permission permission, Model model){
        boolean f=permissionService.addPermission(permission);
        model.addAttribute("permission",permission);
        if (f){
            return "permission/permissioninfo";
        }
        return "permission/addpermission";
    }



    @RequestMapping("/permission/modify")
    public  String  userModifyView(Long perId,Model model){
        //做页面跳转
        //根据id查询数据
        Permission permission= permissionService.querypermissionById(perId);
        //存储到域对象
        model.addAttribute("permission",permission);

        return "permission/permissionmodify";
    }


    @RequestMapping("permission/domodify")
    public  String updatePermission(Permission permission,Model model){
        boolean f= permissionService.modifyPermission(permission);
        //存储到域对象
        model.addAttribute("permission",permission);
        if(f){
            return "permission/permissioninfo";
        }

        //修改失败
        return "permission/permissionmodify";
    }

    @RequestMapping(value = "/permission/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public  Object deletPermission(@PathVariable Long id){
        //去删除操作
        boolean f=  permissionService.deletepermissionById(id);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");

    }


    @RequestMapping(value = "/permission/deletebatch",method = RequestMethod.POST)
    @ResponseBody
    public  Object deletPermissionBatch(@RequestParam("ids") Long[] ids){
        //去删除操作
        boolean f=  permissionService.deletepermissionByall(ids);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");

    }
}
