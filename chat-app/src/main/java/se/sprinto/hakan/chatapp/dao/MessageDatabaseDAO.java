package se.sprinto.hakan.chatapp.dao;

import se.sprinto.hakan.chatapp.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDatabaseDAO implements MessageDAO {


    @Override
    public void saveMessage(Message message) {
        String sql = "INSERT INTO messages (user_id, content, timestamp) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, message.getUserId());
            stmt.setString(2, message.getText());
            stmt.setTimestamp(3, Timestamp.valueOf(message.getTimestamp()));

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getMessagesByUserId(int userId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE user_id = ?";

        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                messages.add(new Message(
                        rs.getInt("user_id"),
                        rs.getString("content"),
                        rs.getTimestamp("timestamp").toLocalDateTime()

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;

    }


}
