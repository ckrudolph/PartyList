/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partylist;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Connor
 */
public class Playlist implements Runnable{
    
    private AudioPlayer player;
    private final ArrayList<ArrayList<File>> music;
    private int user;
    public boolean end;
    
    public Playlist(ArrayList<ArrayList<File>> music, int user){
        this.music = music;
        this.user = user;
        end = false;
    }
    
    @Override
    public void run() {
        try{
            while(!empty() && !end){
                while(music.get(user).size() > 0 && !end){
                    player = new AudioPlayer(music.get(user).get(0));
                    player.run();
                    player.close();
                    if(player.isComplete())
                        music.get(user).remove(0);
                    user = (user+1)%music.size();
                }
                user = (user+1)%music.size();
            }
        }
        catch (Exception ex) {
            Logger.getLogger(MusicWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isComplete(){
        return player.isComplete();
    }
    
    public void close(){
        player.close();
    }
    
    public int getUser(){
        return user;
    }
    
    public boolean empty(){
        for(ArrayList<File> user: music)
            if(user.size() > 0)
                return false;
        return true;
    }
}
