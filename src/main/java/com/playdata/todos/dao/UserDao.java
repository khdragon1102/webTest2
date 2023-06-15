package com.playdata.todos.dao;

import com.playdata.todos.Config.JdbcConnection;
import com.playdata.todos.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void insert(User user){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "insert into users(username, password, name)" +
                "values(?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUserID());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getUserName());
            pst.executeUpdate();
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

        return users.size() != 0;
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
