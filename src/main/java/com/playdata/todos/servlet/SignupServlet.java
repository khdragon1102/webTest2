package com.playdata.todos.servlet;

import com.playdata.todos.dao.UserDao;
import com.playdata.todos.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        req.getRequestDispatcher("views/signup.html").forward(req, resp);
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("name = " + name);
        //입력값 db로 보내준다
        User user = new User(null, username, password, name, null);
        UserDao dao = new UserDao();
        if (dao.insert(user))
        {
            //회원가입 성공 로그인 페이지로
            resp.setStatus(201);
            resp.sendRedirect("/login");
        }
        else{
            //실패 뭔가 띄워줘야함

        }
        //



        //super.doPost(req, resp);
    }
}
