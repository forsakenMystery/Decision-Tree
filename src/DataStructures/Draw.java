/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

/**
 *
 * @author forsakenMystery
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Draw extends JFrame {

    private JPanel contentPane;
    public BinaryTree tree;
    public DrawTree drawer;

    /**
     * Create the frame.
     */
    public Draw(BinaryTree tree) {
        setTitle("ID3");
//                tree.ldr(BinaryTree.getRoot());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 800, 500);
        contentPane = new JPanel();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
//                setUndecorated(true);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        drawer = new DrawTree(tree);
        contentPane.add(drawer);
        setContentPane(contentPane);
        this.tree = tree;
        setVisible(true);
    }

}

class DrawTree extends JPanel {

    public BinaryTree tree;

    public DrawTree(BinaryTree tree) {
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub

        g.setFont(new Font("Tahoma", Font.BOLD, 10));
		//g.drawString(String.valueOf(tree.root.data), this.getWidth()/2, 30);

        //DrawNode(g, tree.root,100, 50,2);
        DrawTree(g, 0, getWidth(), 0, getHeight() / (tree.getheight(BinaryTree.getRoot())), BinaryTree.getRoot());
    }

    public void DrawNode(Graphics g, Node n, int w, int h, int q) {
        g.setFont(new Font("Tahoma", Font.BOLD, 10));

        if (n != null) {

            g.drawString(n.getSymptom() + "", (this.getWidth() / q) + w, h);
            if (n.getYes() != null) {
                DrawNode(g, n.getYes(), -w, h * 2, q);
            }
            //DrawNode(g, n.left, -w, h*2, q);
            //g.drawString(String.valueOf(n.left.data), (this.getWidth()/q)-w, h+50);
            if (n.getNo() != null) {
                DrawNode(g, n.getNo(), w * 2, h * 2, q);
            }
            //g.drawString(String.valueOf(n.right.data), (this.getWidth()/q)+w, h+50);
        }

    }

    public void DrawTree(Graphics g, int StartWidth, int EndWidth, int StartHeight, int Level, Node node) {
        System.out.println("node = " + node);
        String data = node.String();
        g.setFont(new Font("Tahoma", Font.BOLD, 10));
        FontMetrics fm = g.getFontMetrics();
        int dataWidth = fm.stringWidth(data);
        if (node.getYes() != null) {
            g.setColor(Color.blue);
            int dd = fm.stringWidth(node.getYes().String());
            int x1 = (StartWidth + EndWidth) / 2 - dataWidth / 2 - 8 + dataWidth / 2;
            int y1 = StartHeight + Level / 2;
            int x2 = (StartWidth + (StartWidth + EndWidth) / 2) / 2 - dd / 2 - 8 + dd / 2;
            int y2 = StartHeight + Level + Level / 2;
            g.drawLine(x1, y1, x2, y2);
            g.setColor(Color.white);
            g.drawString("Yes", (x1+x2)/2+10, (y1+y2)/2);
        }
        if (node.getNo() != null) {
            g.setColor(Color.GREEN);
            int dd = fm.stringWidth(node.getNo().String());
            int x1=(StartWidth + EndWidth) / 2 - dataWidth / 2 - 8 + dataWidth / 2;
            int y1=StartHeight + Level / 2;
            int x2=((StartWidth + EndWidth) / 2 + EndWidth) / 2;
            int y2=StartHeight + Level + Level / 2;
            g.drawLine(x1, y1, x2, y2);
            g.setColor(Color.white);
            g.drawString("No", (x1+x2)/2+3, (y1+y2)/2);
//            g.setColor(Color.white);
//            Graphics2D g2d = (Graphics2D) g;
//            g2d.drawString("No", (x1+x2)/2, (y1+y2)/2);
//            AffineTransform at = new AffineTransform();
//            at.setToRotation(Math.atan((y2-y1)/x2-x1));
//            g2d.setTransform(at);
//            g2d.drawString("No", (x1+x2)/2, (y1+y2)/2);
        }
        g.setColor(Color.red);
//        System.out.println("why");
        g.fillOval((StartWidth + EndWidth) / 2 - dataWidth / 2 - 8, StartHeight + Level / 2 - dataWidth / 4 - 4, dataWidth + 12, dataWidth / 2 + 4);
        g.setColor(Color.BLACK);
        g.drawString(data, (StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2);
        if (node.getYes() != null) {
            DrawTree(g, StartWidth, (StartWidth + EndWidth) / 2, StartHeight + Level, Level, node.getYes());
        }

        if (node.getNo() != null) {
            DrawTree(g, (StartWidth + EndWidth) / 2, EndWidth, StartHeight + Level, Level, node.getNo());
        }
    }

}
