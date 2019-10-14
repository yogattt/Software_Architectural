package com.Rengar.player;

import com.Rengar.player.pluginParser.Plugin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AAPFrame {
    private JButton add;
    private JButton play;
    private JButton stop;
    private JButton getListButton;
    private JButton help;
    private TextArea result;
    private AAPlayer aaPlayer;
    public AAPFrame(){
        aaPlayer = new AAPlayer();
        JFrame frame = new JFrame("AAPFrame");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        Container con= frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JPanel jPanel0 = new JPanel();
        jPanel0.setLayout(new BoxLayout(jPanel0, BoxLayout.X_AXIS));
        getListButton=new JButton("显示插件列表");
        jPanel0.add(getListButton);
        add=new JButton("添加插件(.jar）");
        jPanel0.add(add);
        con.add(jPanel0);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        play=new JButton("播放");
        stop=new JButton("停止播放");
        help=new JButton("帮助");
        jPanel.add(play);
        jPanel.add(stop);
        jPanel.add(help);
        con.add(jPanel);
        result=new TextArea();
        result.setEditable(false);
        con.add(result);
        frame.setSize(500,400);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!aaPlayer.getPlayerState()) {
                    JFileChooser jfc = new JFileChooser();
                    if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File file = jfc.getSelectedFile();
                        String path = file.getAbsolutePath();
                        result.append("播放"+path+"\n"
                        		+ "只能用此窗口的停止播放按钮关闭播放窗口。播放窗口关闭失效。\n");
                        aaPlayer.play(path);
                    }
                }
                else
                	result.append("请关闭当前播放\n");
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc=new JFileChooser();
                if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                    File file=jfc.getSelectedFile();
                    String path = file.getAbsolutePath();
                    result.append("添加插件"+path+"\n");
                    AAPlayer.addJarToXML(path);
                }
            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cur=AboutBox.getHelp();
                JOptionPane.showMessageDialog(null, cur, "插件列表",JOptionPane.PLAIN_MESSAGE);
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(aaPlayer.getPlayerState()) {
                    aaPlayer.stop();
                }
            }
        });
        getListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Plugin> list = aaPlayer.getPlugins();
                    String str = new String();
                    for(Plugin plugin: list){
                        str += (plugin + "\n");
                    }
                    result.append("列表：\n"+str+"\n");
                    JOptionPane.showMessageDialog(null, str, "插件列表",JOptionPane.PLAIN_MESSAGE);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws Exception{
        AAPFrame aapFrame = new AAPFrame();
    }
}
