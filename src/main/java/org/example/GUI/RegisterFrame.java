package org.example.GUI;

import org.example.service.UserService;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField usernameTextField;
    private JButton registerButton;
    private JPanel mainPanel;
    private JTextField passwordField;

    private String id;
    public RegisterFrame(){
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(mainPanel);
        this.setVisible(true);
        this.pack();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);


        registerButton.addActionListener(e -> {
            UserService service = new UserService();
            System.out.println("Password: " + passwordField.getText());
            boolean userAdded = service.addNewUserToDataBase(usernameTextField.getText(),passwordField.getText());
            if(userAdded){
                id = usernameTextField.getText();
                this.dispose();
                SwingUtilities.invokeLater(() -> {
                    Chat chat = new Chat(id, "chat");
                    chat.sendJoinedMessage(id);
                });
            }
        });
    }


}
