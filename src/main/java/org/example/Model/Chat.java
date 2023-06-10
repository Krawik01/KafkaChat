package org.example.Model;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.LogFrame;
import org.example.MessageProducer;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class Chat extends JFrame {
    private JTextArea message;
    private JPanel panel1;
    private JButton SENDButton;

    String id;
    String topic;

public Chat(String id, String topic) {
    this.id = id;
    this.topic = topic;
    SENDButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MessageProducer.send(new ProducerRecord<>(LogFrame.getUsernName(), LocalDateTime.now()+ " - " + LogFrame.getUsernName() + ": " + message.getText()));

        }
    });
}
}
