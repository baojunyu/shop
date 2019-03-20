package com.ssm.controller;


import com.ssm.pojo.Brand;
import com.ssm.pojo.Category;
import com.ssm.pojo.Product;
import com.ssm.service.ProductService;
import com.ssm.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product",method = RequestMethod.GET)
    @ResponseBody
    public Object ProductList(Product product,
                              @RequestParam(defaultValue ="1") Integer page,
                              @RequestParam(defaultValue = "10") Integer limit){

        ResultVO vo=productService.showProductsPages(product,page,limit);
        return vo;
    }

    @RequestMapping(value = "/categorys",method = RequestMethod.GET)
    @ResponseBody
    public Object getCategas(){
        List<Category> list=productService.queryallfirstCategorys();
        return list;
    }

    @RequestMapping(value = "categorys/parent/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object getCategsSencond(@PathVariable Integer id){
        List<Category> list=productService.queryAllFirstCategorysPatent(id);
        return list;
    }

    @RequestMapping(value = "/brand",method = RequestMethod.GET)
    @ResponseBody
    public Object allBrand(){
        List<Brand> list=productService.querybrand();
        return  list;
    }

    @RequestMapping(value = "/product/upload",method = RequestMethod.POST)
    @ResponseBody
    public Object uploadProductDescImg(MultipartFile editorFile, HttpServletRequest request) throws IOException {
        Map<String,Object> map=new HashMap<>();

        //图片上传到 static/imgs/pdesc
        String realPath = request.getServletContext().getRealPath("static/imags/pdesc");
        File file1=new File(realPath);
        if (!file1.isDirectory()){
            file1.delete();
            file1.mkdirs();
        }
        if (!editorFile.isEmpty()){
            //截取源文件的后缀名
            String endname=editorFile.getOriginalFilename().substring(editorFile.getOriginalFilename().lastIndexOf("."));

            String filename=UUID.randomUUID().toString().replace("-","")+endname;

            File dest=new File(realPath+"/"+filename);

            editorFile.transferTo(dest);
            //返回指定格式的字符串
            map.put("errno",0);
            map.put("data",new String[]{"http://localhost:81/shop-admin/static/imags/pdesc/"+filename});
            return map;

        }
        return null;
    }


    @RequestMapping(value="/product",method = RequestMethod.POST)
    public  String   addProduct(Product product){

        boolean f=  productService.add(product);
        if(f){
            return "product/productlist";
        }
        return "product/productadd";

    }




    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropzFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        // 获取上传的原始文件名
        String fileName = dropzFile.getOriginalFilename();
        // 设置文件上传路径
        String filePath = request.getSession().getServletContext().getRealPath("/static/upload");
        // 获取文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

        // 判断并创建上传用的文件夹
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 重新设置文件名为 UUID，以确保唯一
        file = new File(filePath, UUID.randomUUID() + fileSuffix);

        try {
            // 写入文件
            dropzFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回 JSON 数据，这里只带入了文件名
        result.put("fileName", file.getName());

        return result;
    }


    @RequestMapping(value = "/product/images")
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file,HttpServletRequest request,Long id) throws IOException {
        String realPath = request.getServletContext().getRealPath("/static/imags/p");
        File file1=new File(realPath);
        if (!file1.isDirectory()){
            file1.delete();
            file1.mkdirs();
        }

        String filename=UUID.randomUUID().toString().replace("-","").toString()+"_"+file.getOriginalFilename();
        File dest=new File(realPath+"/"+filename);
        if (!file.isEmpty()){
            file.transferTo(dest);//上传
            boolean f=productService.addproductpicminimages(id,"/static/imags/p/"+filename);
        }
        return filename;
    }

    @RequestMapping(value = "/product/images/{id}",method = RequestMethod.GET)
    public Object uploadpic(@PathVariable Long id, Model model){
        model.addAttribute("id",id);
        return "product/productaddimages";
    }

    @RequestMapping(value = "/product/desc/{id}",method = RequestMethod.GET)
    public Object productinfor(@PathVariable Long id,Model model){
        Product product=productService.selectproductinfo(id);
        model.addAttribute("descproduct",product);
        return "product/productdesc";
    }

    @RequestMapping(value = "/product/modifybyid",method = RequestMethod.GET)
    public Object modifyprodct(Long productId,Model model){
        System.out.println(productId+"---------------");
        Product product=productService.selectproductinfo(productId);
        model.addAttribute("productmodify",product);
        return "product/productmodify";
    }


    @RequestMapping("/product/domodify")
    public  String  userModifyView(Product product,Model model){
        //做页面跳转


        boolean f= productService.modifyproduct(product);
        //存储到域对象
        model.addAttribute("productmodify",product);
        if(f){
            return "product/productlist";
        }

        //修改失败
        return "product/productmodify";
    }
}
