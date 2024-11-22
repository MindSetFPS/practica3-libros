package com.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BinaryTreeRenderer extends JPanel {

    private Node root;
    private int size = 20;

    public BinaryTreeRenderer() {
        this.root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);

        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, root, getWidth() / 2, getHeight() / 2 , size);
    }

    private int drawTree(Graphics g, Node node, int x, int y, int size) {
        if (node == null) return 0;

        int leftOffset = -(size / 4) + getPreferredWidth(node.left) * 12;
        int rightOffset = (size - 5) + getPreferredWidth(node.right) * 12;

        Color color = node != root ? Color.BLACK : Color.RED;
        g.setColor(color);
        g.fillOval(x, y, size, size);

        if (node.left != null || node.right != null) {
            drawChild(g, x, y + size, leftOffset, -30 * getPreferredWidth(node.left), Color.BLACK);
            drawChild(g, x, y + size, rightOffset, 30 * getPreferredWidth(node.right), Color.BLACK);

            if (node.left != null && node.left.left != null) {
                g.drawLine( x, y + size - 6, x - leftOffset / 2, y - 24);
            }
            if (node.right != null && node.right.left != null) {
                g.drawLine(x, y + size - 6, x + rightOffset / 2, y + 12);
            }

            drawTree(g, node.left, x - leftOffset / 3, y + size + size / 4, size );
            drawTree(g, node.right, x + rightOffset / 3, y + size + size / 4, size );
        }

        return size * getPreferredWidth(node.left) + size;
    }

    private void drawChild(Graphics g, int parentX, int parentY, double offset, double height, Color color) {
        g.setColor(color);
        g.drawOval((int) (parentX - offset / 2), (int) (parentY + height * -12), 10, 10);

        if (height > 0) {
            g.drawLine((int) (parentX + offset / 2),(int) (parentY + height * -12 + size / 4),
                    (int) (parentX + offset / 2),(int) (parentY + height * -6));
        }
    }

    private int getPreferredWidth(Node node) {
        return node == null ? 0 : drawTree(getGraphics(), node, getWidth() / 2, getHeight() - size - 10, size);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Binary Tree Renderer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                BinaryTreeRenderer renderer = new BinaryTreeRenderer();
                frame.getContentPane().add(renderer);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }


}
