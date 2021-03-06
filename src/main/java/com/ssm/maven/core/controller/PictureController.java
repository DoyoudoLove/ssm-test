package com.ssm.maven.core.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.maven.core.pojo.Picture;
import com.ssm.maven.core.service.PictureService;
import com.ssm.maven.core.util.LayuiRtn;
import com.ssm.maven.core.util.Pager;
import com.ssm.maven.core.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private PictureService pictureService;

    /**
     * 保存图片
     *
     * @param itemId
     * @param classId
     * @param picturePath
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> savePictureInfo(String itemId, int classId, String picturePath) {
        Map<String, Object> map = new HashMap<>();
        Picture picture = new Picture(itemId, picturePath, classId);

        int count = 0;
        // picture.setItemId(itemId);
        // picture.setClassId(classId);
        // String[] urls = picturePath.split(",");
        // for (int i = 0; i < urls.length; i++) {
        //         picturePath = urls[i];
        //         picture.setPicturePath(picturePath);
        //     count = pictureService.savePicture(picture);
        // }
        count = pictureService.savePicture(picture);
        if (count > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 多张图片上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Object savePicture(MultipartFile file, HttpServletRequest request) {
        String desFilePath = "";
        String oriName = "";
        Map<String, String> dataMap = new HashMap<>();
        try {
            // 1.获取原文件名
            oriName = file.getOriginalFilename();
            // 2.获取原文件图片后缀，以最后的.作为截取(.jpg)
            String extName = oriName.substring(oriName.lastIndexOf("."));
            // 3.生成自定义的新文件名，这里以UUID.jpg|png|xxx作为格式（可选操作，也可以不自定义新文件名）
            String uuid = UUID.randomUUID().toString();
            String newName = uuid + extName;
            // 4.获取要保存的路径文件夹
            // System.out.println(request.getSession().getServletContext().getRealPath(""));
            String realPath = request.getSession().getServletContext().getRealPath("upload");
            File rel = new File(realPath, newName);
            if (!rel.exists() && !rel.isDirectory()) {
                rel.mkdirs();
            }
            // 5.保存图片
            file.transferTo(rel);
            // 6.返回保存结果信息
            dataMap.put("src", "upload/" + newName);
            return new LayuiRtn(0, oriName + "上传成功！", 0, dataMap);
        } catch (IllegalStateException e) {
            System.out.println("图片保存失败");
            return new LayuiRtn(1);
        } catch (IOException e) {
            System.out.println("图片保存失败--IO异常");
            return new LayuiRtn(1);
        }
    }

    /**
     * 表格数据渲染
     *
     * @param page
     * @return
     */
    @RequestMapping("/show")
    @ResponseBody
    public Object showPicture(Pager page) {

        List<Map<String, Object>> pictureList = pictureService.getPage(page.getPage(), page.getRows());
        if (pictureList.size() != 0) {
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pictureList);
            return new LayuiRtn(0, "", pageInfo.getTotal(), pictureList);
        }
        return null;
    }

    /**
     * 单个删除
     *
     * @param pictureId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> deletePicture(int pictureId) {
        Map<String, Object> map = new HashMap<>();

        int count = pictureService.deletePicture(pictureId);
        if (count > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 批量删除
     *
     * @param str
     * @return
     */
    @RequestMapping("/deletePictureInfo")
    @ResponseBody
    public Map<String, Object> deleteItemInfo(String str) {

        Map<String, Object> map = new HashMap<>();
        int count = 0;
        String[] arr = str.split(",");
        int pictureid = 0;
        for (int i = 0; i < arr.length; i++) {
            pictureid = Integer.parseInt(arr[i]);
            count = pictureService.deletePicture(pictureid);
        }
        if (count > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 查询图片路径（pass）
     *
     * @return
     */

    @RequestMapping("/selectPic")
    @ResponseBody
    public Map<String, Object> selectPic(@RequestParam("itemId")String itemId) {
        Map<String, Object> map = new HashMap<>();
        Picture picture = pictureService.getPictureByItemId(itemId);
        if (picture != null) {
            map.put("picture",picture);
        } else {
            map.put("msg",false);
        }
        return map;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> savePath(String picturePath,String itemId) {
        Map<String, Object> map = new HashMap<>();
        Picture picture = new Picture(itemId,picturePath);
        int count = pictureService.updatePicture(picture);
        if (count > 0) {
            map.put("success",true);
        } else {
            map.put("success",false);
        }
        return map;
    }





}
