package org.example.service;

import org.example.database.DbConnect;
import org.example.model.User;

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
    public boolean addNewUserToDataBase(String username, String userpassword) {
        boolean userHasBeenAdded = false;
        if(doesUserExist(new User(username, userpassword))){
            String message = "User already exist";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(message);
        }

        if (!username.equals("") && !userpassword.equals("") && username != null && userpassword != null) {
            String query = "INSERT INTO user (username, userpassword) VALUES ('" + username + "', '" + userpassword + "')";
            connection.execUpdateSql(query);
            userHasBeenAdded = true;
        } else {
            String message = "Wrong data";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(message);
        }
        return userHasBeenAdded;
    }


}
