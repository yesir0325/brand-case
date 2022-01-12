package com.itheima.web.Servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();
        String jsonString = JSON.toJSONString(brands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Brand brand = JSON.parseObject(params,Brand.class);
        brand.setBrandName(new String(brand.getBrandName().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
        brand.setCompanyName(new String(brand.getCompanyName().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
        brand.setDescription(new String(brand.getDescription().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
        brandService.addBrand(brand);
        response.getWriter().write("success");
    }
    //批量删除
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BufferedReader br = request.getReader();
        String params = br.readLine();
        int[] ids = JSON.parseObject(params, int[].class);
        brandService.deleteByIds(ids);
        response.getWriter().write("success");
    }
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BufferedReader reader = request.getReader();
        String _id = reader.readLine();
        int id = Integer.parseInt(_id);
        brandService.deleteById(id);
        response.getWriter().write("success");
    }
    public void editById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Brand brand = JSON.parseObject(params,Brand.class);
        brand.setBrandName(new String(brand.getBrandName().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
        brand.setCompanyName(new String(brand.getCompanyName().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
        brand.setDescription(new String(brand.getDescription().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
        brandService.editById(brand);
        response.getWriter().write("success");
    }
}
