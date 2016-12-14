/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partylist;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author Connor
 */
public class AudioPlayer implements Runnable{
    
    private Player player;
    
    public AudioPlayer(){
        
    }
    public AudioPlayer(String path) throws Exception{
        FileInputStream fis     = new FileInputStream(path);
        BufferedInputStream bis = new BufferedInputStream(fis);
        player = new Player(bis);
    }
    public AudioPlayer(File file) throws Exception{
        FileInputStream fis     = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        player = new Player(bis);
    }
    
    @Override
    public void run() {
        try {
            player.play();
        } catch (JavaLayerException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        player.close();
    }
    
    public boolean isComplete(){
        return player.isComplete();
    }
}
