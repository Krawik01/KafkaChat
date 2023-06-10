package org.example;

import lombok.Getter;
import org.example.Model.Chat;
import org.example.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogFrame extends JFrame {

    @Getter
    private JPanel mainPanel;
    private JButton REGISTERButton;

    private JPasswordField passwordField;
    private JButton LogInButton;
    private JTextField nameTextField;
    private String id;
    private String topic;
    public static User user;

    public LogFrame() {
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(mainPanel);
        this.setVisible(true);
        this.pack();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        LogInButton.addActionListener(e -> {


        });
        LogInButton.addActionListener(e -> {
            System.out.println(nameTextField.getText());
            System.out.println(String.valueOf(passwordField.getPassword()));

            user = new User(nameTextField.getText(), String.valueOf(passwordField.getPassword()));

            UserService userService = new UserService();
            System.out.println(userService.doesUserExist(user));
            if (userService.doesUserExist(user)) {
                SwingUtilities.invokeLater(() -> new Chat(id,topic));
            }
        });
    }

    public static String getUsernName() {
        return user.getUsername();
    }

    public String getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }
}
