package com.playdata.todos.dao;

import com.mysql.cj.ServerVersion;
import com.mysql.cj.Session;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.ClientInfoProvider;
import com.mysql.cj.jdbc.JdbcPreparedStatement;
import com.mysql.cj.jdbc.JdbcPropertySet;
import com.mysql.cj.jdbc.JdbcStatement;
import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ServerSessionStateController;
import com.mysql.cj.xdevapi.Result;
import com.playdata.todos.Config.JdbcConnection;
import com.playdata.todos.Config.LogoutThread;
import com.playdata.todos.dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class UserDao {
    public static User me = null;

    public boolean insert(User user){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "insert into users(username, password, name)" +
                "values(?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUserID());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getUserName());

            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(String id, String password){
        List<User> users = new ArrayList<>();
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select *" +
                "from users " +
                "where username=? and password=?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, password);
            ResultSet result = pst.executeQuery();

            while(result.next()) {
                users.add(makeUser(result));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (users.size() > 0) {
            me = users.get(0);
            System.out.println(me);
            new LogoutThread().start();
            return true;
        } else
         return false;
    }

    private User makeUser(ResultSet resultset){
            try {
                Integer userid = resultset.getInt("id");
                String username = resultset.getString("username");
                String name = resultset.getString("name");
                String crate_at = resultset.getString("create_at");

                return new User(userid, username, null, name, crate_at);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
