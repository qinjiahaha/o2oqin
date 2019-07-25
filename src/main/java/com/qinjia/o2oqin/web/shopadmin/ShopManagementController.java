package com.qinjia.o2oqin.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qinjia.o2oqin.dto.ShopExecution;
import com.qinjia.o2oqin.entity.Area;
import com.qinjia.o2oqin.entity.PersonInfo;
import com.qinjia.o2oqin.entity.Shop;
import com.qinjia.o2oqin.entity.ShopCategory;
import com.qinjia.o2oqin.enums.ShopStateEnum;
import com.qinjia.o2oqin.service.AreaService;
import com.qinjia.o2oqin.service.ShopCategoryService;
import com.qinjia.o2oqin.service.ShopService;
import com.qinjia.o2oqin.util.CodeUtil;
import com.qinjia.o2oqin.util.HttpServletRequestUtil;
import com.qinjia.o2oqin.util.ImageUtil;
import com.qinjia.o2oqin.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    private static final Logger logger= LoggerFactory.getLogger(ShopManagementController.class);
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;
    @RequestMapping(value="/getshopinitinfo",method=RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopInitInfo(){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<ShopCategory> shopCategoryList=new ArrayList<ShopCategory>();
        List<Area> areaList=new ArrayList<Area>();
        try{
            shopCategoryList=shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList=areaService.getAreaList();
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());

        }
        return modelMap;
    }

    @RequestMapping(value="/registershop",method= RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        if(!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","输入了错误的验证码");
            return modelMap;
        }
        String shopStr=HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue("shopStr", Shop.class);
            logger.debug("shopStr",shopStr);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }

        if(shop!=null&&shopImg!=null){
            PersonInfo owner=new PersonInfo();
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution shopExecution= null;
            try {
                shopExecution = shopService.addShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
                if(shopExecution.getState()== ShopStateEnum.CHECK.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",shopExecution.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
            }

            return modelMap;
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入店铺信息");
            return modelMap;
        }
    }
  /*  private static void inputStreamToFile(InputStream ins, File file){
        FileOutputStream os=null;
        try{
            os=new FileOutputStream(file);
            int bytesRead=0;
            byte[] buffer=new byte[1024];
            while((bytesRead=ins.read(buffer))!=-1){
                os.write(buffer,0,bytesRead);
            }
        }catch (Exception e){
            throw new RuntimeException("调用inputStreamToFile产生异常:"+e.getMessage());
        }finally {
            try{
                if(os!=null){
                    os.close();
                }
                if(ins!=null){
                    ins.close();
                }
            }catch (IOException e){
                throw new RuntimeException("调用inputStreamToFile关闭io产生异常:"+e.getMessage());
            }
        }
    }*/
}
