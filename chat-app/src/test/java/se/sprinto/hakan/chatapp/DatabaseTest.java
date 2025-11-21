package se.sprinto.hakan.chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.sprinto.hakan.chatapp.dao.*;
import se.sprinto.hakan.chatapp.model.Message;
import se.sprinto.hakan.chatapp.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {

    private UserdatabaseDAO userdatabaseDAO;
    private MessageDatabaseDAO messageDatabaseDAO;

    @BeforeEach
    void arrange() throws SQLException {

        //Arrange
        userdatabaseDAO = new UserdatabaseDAO();
        messageDatabaseDAO = new MessageDatabaseDAO();

        Connection conn = DatabaseUtil.getInstance().getConnection();
        Statement stmt = conn.createStatement();

        stmt.execute("""
                CREATE TABLE users (
                    id INT Auto_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) NOT NULL,
                    password VARCHAR(50) NOT NULL 
                    );
                """);

        stmt.execute("""
                CREATE TABLE messages (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT NOT NULL,
                    content VARCHAR(50),
                    timestamp TIMESTAMP NOT NULL,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                    );
                """);
        System.out.println(DatabaseUtil.getInstance().getConnection().getMetaData().getURL());

    }


    @Test
    void testUserAndMessages(){
        User user = userdatabaseDAO.register(new User("Snigel", "123"));

        Message message1 = new Message(user.getId(), "HEJHEJ", LocalDateTime.now());
        Message message2 = new Message(user.getId(), "Tjena", LocalDateTime.now());

        //Act
        messageDatabaseDAO.saveMessage(message1);
        messageDatabaseDAO.saveMessage(message2);

        List<Message> messages = messageDatabaseDAO.getMessagesByUserId(user.getId());

        //Assert
        assertEquals(2, messages.size());
        assertEquals("HEJHEJ", messages.get(0).getText());
        assertEquals("Tjena", messages.get(1).getText());
    }

}
