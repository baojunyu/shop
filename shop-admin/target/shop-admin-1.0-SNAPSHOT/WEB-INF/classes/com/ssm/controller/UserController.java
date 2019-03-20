package com.ssm.controller;

import com.google.code.kaptcha.Constants;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.utils.MD5Utils;
import com.ssm.vo.ResultVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import org.apache.shiro.subject.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userloginshiro",method = RequestMethod.POST)
    public String userLogin1(@RequestParam String userAccount,
                            @RequestParam String password,
                            @RequestParam String imgCode){
        //获取验证码
        String code =(String) SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if(!imgCode.equalsIgnoreCase(code)){
            return "login";
        }

        //shiro认证
        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken(userAccount,password);

        try {
            //设置记住我
            token.setRememberMe(true);
            subject.login(token);

            //登录成功
            User user = userService.login1(userAccount, password);
            subject.getSession().setAttribute("users",user);
//            subject.getSession().setAttribute("userAccount",userAccount);
        }catch (UnknownAccountException e){
            //没有改用户
            e.printStackTrace();
            return "login";

        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            //密码不正确
            return "login";
        }catch (LockedAccountException e){
            //账号被锁定
            e.printStackTrace();
            return "login";
        }catch (AuthenticationException e) {
            //其他异常
            e.printStackTrace();
            return "login";
        }

        return "admin/index";
    }



    @RequestMapping(value = "/userlogin",method = RequestMethod.POST)
    public String userLogin(@RequestParam String userAccount,
                            @RequestParam String password,
                            @RequestParam String imgCode,
                            HttpSession session){

        //校验验证码
        String imgCode_ = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if (!imgCode_.equals(imgCode_)){
            return "login";
        }
        User user = userService.login1(userAccount, password);
//        User login = userService.login(userAccount, password);
        if (user!=null){
            session.setAttribute("users",user);
            return "admin/index";
        }
        return "login";
    }

    @RequestMapping(value = "/userchangepass",method = RequestMethod.POST)
    public String userchangepass(@RequestParam String yuanpass,
                            @RequestParam String newpass,
                            @RequestParam String passagain,
                            HttpSession session,
                                 SessionStatus sessionStatus){
        User users = (User) session.getAttribute("users");
        System.out.println(yuanpass+","+newpass+","+passagain+","+users.getPassword());
        String password = users.getPassword();
        String yuanencrypt = MD5Utils.encrypt(yuanpass);
        if (!password.equals(yuanencrypt)){
            return "admin/changepass";
        }
        Long userId = users.getUserId();
        if (!newpass.equals(passagain)){
            return "admin/changepass";
        }
        String encrypt = MD5Utils.encrypt(newpass);
        users.setPassword(encrypt);
        int changepass = userService.changepass(userId.intValue(), users);
        if (changepass==1){
            sessionStatus.setComplete();
            System.out.println(users.getUserAccount());
            return "login";
            //
        }
        return "admin/changepass";
    }

    @RequestMapping(value = "/loginout")
    public String loginout(HttpSession session){
        session.invalidate();
        //redirect:/WEB-INF/v/login.jsp
        return "login";
    }

    @RequestMapping(value = "/uploadheadimage",method = RequestMethod.POST)
    @ResponseBody
    public  Object  uploadHeadImage(MultipartFile file, HttpSession session,
                                    HttpServletRequest request) throws IOException {
        Map<String,String> res=new HashMap<>();
        //上传图片吧图片上传到static/imags/head/文件夹下
        String realPath = request.getServletContext().getRealPath("/static/imags/head");
        File file1=new File(realPath);
        if (!file1.isDirectory()){
            file1.delete();
            file1.mkdirs();
        }
        if (!file.isEmpty()){
            String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();

            File dest=new File(realPath+"/"+fileName);
            //要上传到服务器的路径
            String  uploadStr="static/imags/head/"+fileName;

            //文件复制
            file.transferTo(dest);

            //从session中获取userId
            User users =(User) session.getAttribute("users");

            //修改用户头像信息
            boolean b = userService.modifyUserHeadImage(users.getUserId(), uploadStr);

            if (b){
                users.setHeadimageurl(uploadStr);
                session.setAttribute("users",users);
                res.put("code","0");
                return res;
            }
        }
        res.put("code","1");
        return res;
    }

    @RequestMapping("table/user")
    public @ResponseBody Object userListTest(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit){
        ResultVO vo=userService.showUsersPageTest(page,limit);
        return vo;
    }

    @RequiresPermissions("user:view")
    @RequestMapping("user/list")
    public @ResponseBody Object userList(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit,
                                         String no,
                                         String mobileNumber,
                                         Integer status){
        ResultVO vo=userService.showUsersPage(page,limit,no,mobileNumber,status);
        return vo;
    }


    @RequestMapping("/user/checkaccount")
    @ResponseBody
    public Object CheckAccount(@RequestParam String userAccount){
        //调用service查询是否存在
        boolean f=userService.checkAccount(userAccount);
        if (f){
            return ResultVO.error();
        }
        return  ResultVO.success();
    }

    @RequiresPermissions("user:add")
    @RequestMapping("user/add")
    public  String  userAdd(User user, Model model){
        String password = user.getPassword();

        boolean f=userService.addUser(user);
        user.setPassword(password);
        model.addAttribute("user",user);
        if(f){

            return "user/userinfo";

        }
        return "user/useradd";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("/user/modify")
    public  String  userModifyView(Long userId,Model model){
        //做页面跳转
        //根据id查询用户数据

        User user= userService.queryUserById(userId);
        //存储到域对象
        model.addAttribute("user",user);

        return "user/usermodify";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("/user/domodify")
    public  String  userModifyView(User user,Model model){
        //做页面跳转
        //根据id查询用户数据

        boolean f= userService.modifyUser(user);
        //存储到域对象
        model.addAttribute("user",user);
        System.out.println(user.toString());
        if(f){
            return "user/userinfo";
        }

        //修改失败
        return "user/usermodify";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public  Object deletUser(@PathVariable Long id){
        //去删除操作
        boolean f=  userService.deleteUserById(id);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");

    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/user/deletebatch",method = RequestMethod.POST)
    @ResponseBody
    public  Object deletUserBatch(@RequestParam("ids") Long[] ids){
        //去删除操作
        boolean f=  userService.deleteBatch(ids);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");

    }

//    @RequiresPermissions("user:active")
    @RequestMapping(value = "/user/active")
    @ResponseBody
    public  Object  userActive(@RequestParam Long userId,@RequestParam Boolean status){
        //激活用户
        boolean f=userService.userActive(userId,status);
        if (f){
            return ResultVO.success();
        }
        return ResultVO.error();
    }

//    @RequiresPermissions("user:allocaterole")
    @RequestMapping("/userroleedit")
    @ResponseBody
    public  Object userRoleEdit(@RequestParam Long userId,
                                Long[] rids){
        boolean f=  userService.editUserRole(userId,rids);
        if(f){
            return ResultVO.success();
        }

        return ResultVO.error("error");
    }
}
