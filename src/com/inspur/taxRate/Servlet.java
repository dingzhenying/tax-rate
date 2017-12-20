package com.inspur.taxRate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String monthWage=request.getParameter("monthWage");
        Calculation calculation=new Calculation();
        double result=calculation.month(Double.parseDouble(monthWage));
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.close();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String monthWage=request.getParameter("monthWage");
        String yearAwards=request.getParameter("yearAwards");
        String splitWay=request.getParameter("splitWay");
        Calculation calculation=new Calculation();
        double wage=Double.parseDouble(monthWage);
        double awards=Double.parseDouble(yearAwards);
        int way=Integer.parseInt(splitWay);
        String result=calculation.year(wage,awards,way);
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.close();
    }

}
