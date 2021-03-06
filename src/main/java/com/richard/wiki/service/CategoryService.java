package com.richard.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.richard.wiki.annotation.OperationRecord;
import com.richard.wiki.domain.Category;
import com.richard.wiki.examples.CategoryExample;
import com.richard.wiki.mapper.CategoryMapper;
import com.richard.wiki.req.CategoryQueryReq;
import com.richard.wiki.req.CategorySaveReq;
import com.richard.wiki.resp.CategoryQueryResp;
import com.richard.wiki.resp.PageResp;
import com.richard.wiki.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SnowFlake snowFlake;

    public PageResp<CategoryQueryResp> list(CategoryQueryReq categoryQueryReq) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");

        PageHelper.startPage(categoryQueryReq.getPage(),categoryQueryReq.getPageSize());
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);

        List<CategoryQueryResp> list = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryQueryResp categoryQueryResp = new CategoryQueryResp();
            categoryQueryResp.setId(category.getId());
            categoryQueryResp.setName(category.getName());
            categoryQueryResp.setSort(category.getSort());
            categoryQueryResp.setParent(category.getParent());
            list.add(categoryQueryResp);
        }

        // 返回的分页参数
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     * 返回所有分类数据
     * @return
     */
    public List<CategoryQueryResp> all() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        List<CategoryQueryResp> list = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryQueryResp categoryQueryResp = new CategoryQueryResp();
            categoryQueryResp.setId(category.getId());
            categoryQueryResp.setName(category.getName());
            categoryQueryResp.setSort(category.getSort());
            categoryQueryResp.setParent(category.getParent());
            list.add(categoryQueryResp);
        }

        return list;
    }

    /**
     * 新增
     * @param req
     */
    @OperationRecord(desc = "分类新增",type = "1")
    public void save(CategorySaveReq req) {
        Category category = Category.builder().id(snowFlake.nextId()).name(req.getName()).sort(req.getSort()).parent(req.getParent()).build();
        categoryMapper.insert(category);
    }

    /**
     * 编辑
     * @param req
     */
    @OperationRecord(desc = "分类更新",type = "3")
    public void update(CategorySaveReq req) {
        Category category = Category.builder().id(req.getId()).name(req.getName()).sort(req.getSort()).parent(req.getParent()).build();
        categoryMapper.updateByPrimaryKeySelective(category);
    }

}
