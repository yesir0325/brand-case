package com.itheima.web.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//替换HTTPSerlet，根据请求路径进行方法分发
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String uri = req.getRequestURI();
        //获取最后一段路径，方法名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index+1);//方法名
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            Method method = cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            try {
                method.invoke(this,req,resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
