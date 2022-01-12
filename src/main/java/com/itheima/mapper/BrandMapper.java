package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    //查询所有
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();
    //添加
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void addBrand(Brand brand);
    //批量删除
    void deleteByIds(@Param("ids") int[] ids);
    //分页
    @Select("select * from tb_brand limit #{begin}, ${size}")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);
    //查询总记录数
    @Select("select count(*) from tb_brand")
    int selecTtotalCount();
    //删除
    @Delete("delete from tb_brand where id=#{id}")
    void deleteById(int id);
    //修改
    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id}")
    void editById(Brand brand);
}
