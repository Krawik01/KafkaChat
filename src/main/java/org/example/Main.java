package org.example;

import org.example.GUI.LogFrame;
import org.example.database.DbConnect;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

import javax.swing.*;

public class Main {
    public static DbConnect dbConnect = new DbConnect();
    public static void main(String[] args) {
        EmbeddedKafkaBroker embeddedKafkaBroker = new EmbeddedKafkaBroker(1)
                .kafkaPorts(9092);

        embeddedKafkaBroker.afterPropertiesSet();

        SwingUtilities.invokeLater(()->new LogFrame());
        SwingUtilities.invokeLater(()->new LogFrame());

       // SwingUtilities.invokeLater(()->new Chat("Jakub","chat"));

    }
}
