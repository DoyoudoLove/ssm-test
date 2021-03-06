package com.ssm.maven.core.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.maven.core.pojo.Item;
import com.ssm.maven.core.service.ItemService;
import com.ssm.maven.core.util.LayuiRtn;
import com.ssm.maven.core.util.Pager;
import com.ssm.maven.core.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Resource
    private ItemService itemService;

    /**
     * layui table填充
     *
     * @param page
     * @return
     */
    @RequestMapping("/show")
    @ResponseBody
    public Object showItemList(Pager page) {
        List<Map<String, Object>> itemList = itemService.getPage(page.getPage(), page.getRows());
        if (itemList.size() != 0) {
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(itemList);
            return new LayuiRtn(0, "", pageInfo.getTotal(), itemList);
        }
        return null;
    }

    /**
     * 添加
     *
     * @param item
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> saveItem(Item item) {
        Map<String, Object> map = new HashMap<>();

        int count = itemService.saveItem(item);
        if (count > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 删除单个商品
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteItem(String itemId) {

        Map<String, Object> map = new HashMap<>();

        int count = itemService.deleteItem(itemId);
        if (count > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 删除商品
     *
     * @param str
     * @return
     */
    @RequestMapping("/deleteItemInfo")
    @ResponseBody
    public Map<String, Object> deleteItemInfo(String str) {

        Map<String, Object> map = new HashMap<>();
        int count = 0;
        String[] arr = str.split(",");
        String itemId = null;
        for (int i = 0; i < arr.length; i++) {
            itemId = arr[i];
            count = itemService.deleteItem(itemId);
        }
        if (count > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 更新商品
     *
     * @param item
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> updateItem(Item item) {
        Map<String, Object> map = new HashMap<>();
        int result = itemService.updateItem(item);
        if (result > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping("/selectItem")
    @ResponseBody
    public Map<String, Object> selectItem(String itemId) {
        Map<String, Object> map = new HashMap<>();
        Item item = itemService.findItemById(itemId);
        if (item != null) {
            map.put("item", item);
        } else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 分页
     *
     * @param condition
     * @return
     */
    @RequestMapping("/getPage")
    @ResponseBody
    public PageInfo<Map<String, Object>> getPage(@RequestParam Map condition) {

        int pageNum = 1;
        if (StringUtil.isNotEmpty((String) condition.get("pageNum"))) {
            pageNum = Integer.parseInt((String) condition.get("pageNum"));
        }
        int pageSize = 10;
        if (StringUtil.isNotEmpty((String) condition.get("pageSize"))) {
            pageSize = Integer.parseInt((String) condition.get("pageSize"));
        }
        //开始分页
        List<Map<String, Object>> pageInfoByCondition = null;
        try {
            pageInfoByCondition = itemService.getPage(pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pageInfoByCondition);
        return pageInfo;
    }

    @RequestMapping("/showFruit/{classId}")
    public String showFruit(@PathVariable int classId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        List<Item> itemList = itemService.getItemList(classId);
        request.getSession().setAttribute("itemList", itemList);

        return "fruit";
    }


    @RequestMapping("/detail/{itemId}")
    public String detail(@PathVariable String itemId,HttpServletRequest request) {

        Item item = itemService.findItemById(itemId);

        request.getSession().setAttribute("item",item);
        return "detail";
    }


}
