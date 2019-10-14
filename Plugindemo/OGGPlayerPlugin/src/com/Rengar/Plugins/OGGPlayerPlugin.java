package com.Rengar.Plugins;
import java.awt.Label;
import javax.swing.JFrame;

import com.Rengar.Plugins.IPlayerPlugin;
public class OGGPlayerPlugin implements IPlayerPlugin {
    private String filePath;
    JFrame win;

    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void service(){
        try {
            win=new JFrame("OGGplayer");
            win.add(new Label(filePath+"is playing"));
            win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            win.setSize(250, 100);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void play(){
    	win.setVisible(true);
    	win.show();
    }

    @Override
    public void stopAudio(){
        win.dispose();
    }}
