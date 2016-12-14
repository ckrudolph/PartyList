/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partylist;
import javax.swing.JOptionPane;
/**
 *
 * @author Connor
 */
public class Main {
    public static void main(String [ ] args)
    {
        String[] options = {"Host", "Client"};
        int choice = JOptionPane.showOptionDialog(null, "Would you like to open a client or host?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
        if(choice == 0)
        {
            MusicWindow window = new MusicWindow();
            ConnectionManager connections = new ConnectionManager(window);
            Thread connectionThread = new Thread(connections);
            connectionThread.start();
            window.setVisible(true);
        }
        else
        {
            ClientWindow window = new ClientWindow();
            window.setVisible(true);
        }
    }
}
