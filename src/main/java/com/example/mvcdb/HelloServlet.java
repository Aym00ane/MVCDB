package com.example.mvcdb;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String name=request.getParameter("name");
        String password=request.getParameter("password");

        boolean found = false;
        String url = "jdbc:mysql://localhost:8889/accounts";
        String D_username = "aymane";
        String D_password = "1234";

        LoginBean bean = new LoginBean();
        bean.setName(name);
        bean.setPassword(password);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(url, D_username, D_password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");

            while (result.next()) {
                String dbUserName = result.getString("NAME");
                String dbPassword = result.getString("PASSWORD");

                if (Objects.equals(bean.getName(), dbUserName) && Objects.equals(bean.getPassword(), dbPassword)) {
                    request.setAttribute("user", bean.getName());
                    RequestDispatcher rd=request.getRequestDispatcher("login-success.jsp");
                    rd.forward(request, response);
                    return;
                }
            }

            RequestDispatcher rd=request.getRequestDispatcher("login-error.jsp");
            rd.forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
