package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    static JFrame window = new JFrame("Hello World!");
    static BinaryTree BT = new BinaryTree();
    
    public static void UI() {
        // Set up the window dimensions and close operation
        window.setSize(300, 150);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold our GUI components
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Create a text field
        JTextField textField = new JTextField(20); // 20 columns wide

        // Create a button
        JButton button = new JButton("Add Book!");

        // Add an action listener to the button (what happens when it's clicked)
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                BT.insert(Integer.parseInt(text));
                System.out.println("Book Added! Text field value: " + text);
                textField.setText("");
            }
        });
        
        JButton showBT = new JButton("Show BT");
        JLabel btRecorridos = new JLabel();
    
        showBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                BT.printInOrder();
                BT.printPostOrder();
                BT.printPreOrder();
            }
        });

        // Add components to the panel
        panel.add(new JLabel("Book ISBN"));
        panel.add(textField);
        panel.add(new JLabel("Book title"));
        panel.add(new JTextField(20));
        panel.add(button);
        panel.add(showBT);
        
        // Add the panel to the frame
        window.getContentPane().add(panel);

        // Make the frame visible (show it on screen)
        window.setVisible(true);
    }

    public static void main(String[] args) {
        UI();
    }
}