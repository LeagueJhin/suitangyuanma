package com.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//与web.xml中的配置等效
//@WebServlet("/ErrorHandlerController")
public class ErrorHandleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        该属性给出异常产生的信息，信息可被存储，
//        并在存储为
//        java.lang.Throwable
//        数据类型后可被分析。
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "UnKnown";
        }
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "UnKnown";
        }
        PrintWriter out = resp.getWriter();
        String title = "Error/Exception 信息";

        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n");
        out.println("<h1>异常信息实例演示</h1>");
        if (throwable == null && statusCode == null) {
            out.println("<h2>错误信息丢失</h2>");
            out.println("请返回 <a href=\"" +
                    resp.encodeURL("http://localhost:8080/") +
                    "\">主页</a>。");
        } else if (statusCode != null) {
            out.println("错误代码 : " + statusCode);
        } else {
            out.println("<h2>错误信息</h2>");
            out.println("Servlet Name : " + servletName +
                    "</br></br>");
            out.println("异常类型 : " +
                    throwable.getClass().getName() +
                    "</br></br>");
            out.println("请求 URI: " + requestUri +
                    "<br><br>");
            out.println("异常信息: " +
                    throwable.getMessage());
        }
        out.println("</body>");
        out.println("</html>");

    }
}
