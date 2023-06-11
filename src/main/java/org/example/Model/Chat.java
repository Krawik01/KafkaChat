package org.example.Model;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.MessageConsumer;
import org.example.MessageProducer;


import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;

public class Chat extends JFrame {
    private JTextArea TextArea;
    private JPanel panel1;
    private JButton SENDButton;
    private JTextField messageToSend;

    String id;
    String topic;

    private final MessageConsumer messageConsumer;

    public Chat(String id, String topic) {
        this.id = id;
        this.topic = topic;

        messageConsumer = new MessageConsumer(topic, id);
        this.setTitle(id);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel1);
        this.pack();
        this.setVisible(true);

        TextArea.setEditable(false);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        Executors.newSingleThreadExecutor().submit(()->{
            while (true) {
                messageConsumer.kafkaConsumer.poll(Duration.of(1, ChronoUnit.SECONDS)).forEach(
                        m -> {
                            System.out.println(m);
                            TextArea.append(m.value() + System.lineSeparator());
                        }
                );
            }
        });

//        SENDButton.addActionListener(e -> MessageProducer.send
//                (new ProducerRecord<>(topic, LocalDateTime.now() + " - " + id+ ": " + messageToSend.getText())));

        SENDButton.addActionListener(e -> {
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            MessageProducer.send(new ProducerRecord<>(topic, currentTime + " : " + id + ": " + messageToSend.getText()));
           // TextArea.setText("");
            messageToSend.setText("");
         //   sendMessage(id, messageToSend.getText());
        });
    }
    public void sendJoinedMessage(String messageToSend){
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        MessageProducer.send(new ProducerRecord<>("chat", currentTime +": " + messageToSend + " joined the chat!"));


    }
}
