package org.example;

import org.example.Model.User;

import javax.swing.*;
import java.util.Objects;

public class UserService {

    DbConnect connection = new DbConnect();
    public boolean doesUserExist(User user){
        String result = connection.execSql("SELECT * FROM user WHERE username LIKE '" + user.getUsername() + "' AND userpassword LIKE '" + user.getPassword() +"'");
        if(Objects.equals(result, "")){
            String message = "Wrong Name or Password";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
}
