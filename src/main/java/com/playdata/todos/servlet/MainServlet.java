package com.playdata.todos.servlet;

import com.playdata.todos.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (UserDao.me == null) {
            resp.sendRedirect("/login");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>main</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"/css/main.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>" + UserDao.me.getUserName() + "로그인 성공</h1>\n" +
                    "<p><img src=\"/img/img.png\"></p>\n" +
                    "</body>\n" +
                    "</html>");
            //super.doGet(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
    }
}
