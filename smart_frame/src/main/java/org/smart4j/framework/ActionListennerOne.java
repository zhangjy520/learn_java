package org.smart4j.framework;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class ActionListennerOne{
    private static JLabel qrCode ;
    public static void main(String[] args) {
        //1.创建一个JFrame：myFrame
        JFrame myFrame = new JFrame();
        //2.设置myFrame的属性：可见、大小
        myFrame.setVisible(true);
        myFrame.setSize(200, 200);
        //3.创建一个JPanel：myPanel
        JPanel myPanel = new JPanel();
        myPanel.setLayout(null);

        //4.把myPanel放置在myFrame上
        myFrame.add(myPanel);
        //5.创建一个JButton：myButton
        JButton myButton = new JButton("按钮1");
        //6。把myButton放置在myPanel上
        myPanel.add(myButton);
        qrCode  = new JLabel();
        qrCode.setIcon(new ImageIcon("D:\\javaProjects\\git\\smart_frame\\src\\main\\java\\org\\smart4j\\framework\\a.jpg"));
        myPanel.add(qrCode);
        qrCode.setVisible(false);

        //7.给myButton添加事件监听：内部类方式（仔细看，特别是标点符号）
        myButton.addMouseListener(new MouseListener() {

            public void mouseReleased(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {
                qrCode.setVisible(false);
            }
            public void mouseEntered(MouseEvent e) {
                qrCode.setVisible(true);
            }
            public void mouseClicked(MouseEvent e) {
                //一个弹框，此处不细说其语法
                JOptionPane.showMessageDialog(null,"内部类事件监听监听","注意",0,null);
            }
        });
    }
}