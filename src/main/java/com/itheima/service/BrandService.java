package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandService {
    //查询所有
    List<Brand> selectAll();
    /**
     * 添加
     */
    void addBrand(Brand brand);
    /**
     * delete
     */
    void deleteByIds( int[] ids);
    /**
     * 分页查询
     */
    PageBean<Brand> selectByPage(int currentPage,int pageSize);
    //删除
    void deleteById(int id);
    //修改
    void editById(Brand brand);

}
