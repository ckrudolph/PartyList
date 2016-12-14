/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partylist;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Connor
 */
public class ConnectionManager implements Runnable {
    
    public final static int SOCKET_PORT = 7777;
    private MusicWindow window;
    
    public ConnectionManager(MusicWindow window) {
        this.window = window;
    }

    @Override
    public void run() {
        ServerSocket servsock = null;
        Socket sock = null;
        try {
            servsock = new ServerSocket(SOCKET_PORT);
        while(window.isEnabled()) {
                sock = servsock.accept();
                
                BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String name = br.readLine();
                String user = br.readLine();
                File file = new File(name);
                OutputStream out = new FileOutputStream(file);
                
                byte [] bytes  = new byte [10000000];
                InputStream in = sock.getInputStream();
                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }
                
                window.getSong(name, file, user);
                
                br.close();
                out.close();
                in.close();
                sock.close();
            }
        servsock.close();
        }   catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
}
