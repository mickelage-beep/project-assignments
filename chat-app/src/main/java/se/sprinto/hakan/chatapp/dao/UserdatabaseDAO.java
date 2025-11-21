package se.sprinto.hakan.chatapp.dao;

import se.sprinto.hakan.chatapp.model.Message;
import se.sprinto.hakan.chatapp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserdatabaseDAO implements UserDAO {


    @Override
    public User register(User user) {
        String sql = "INSERT INTO users (username, password) VALUES(?,?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    user.setId(id);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User login(String username, String password) {
        String sql = "SELECT * FROM users LEFT JOIN messages ON users.id = messages.user_id " +
                "WHERE username=? AND password=?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
