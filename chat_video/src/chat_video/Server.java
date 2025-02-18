package chat_video;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author LENOVO
 */
public class Server extends javax.swing.JFrame {

    /**
     * Creates new form Server
     */
    public Server() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        img_server = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        img_server.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(img_server, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(img_server, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(String args[]) {
    // Create and show the GUI on the Event Dispatch Thread
    java.awt.EventQueue.invokeLater(() -> {
        new Server().setVisible(true);
    });

    try (ServerSocket server = new ServerSocket(9999)) {
        System.out.println("Waiting for connection...");
        Socket s = server.accept();
        System.out.println("Connected");
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ImageIcon ic;

        while (true) {
            byte[] byteArray = (byte[]) in.readObject(); // Receive byte array from client
            ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
            BufferedImage image = ImageIO.read(bais); // Convert byte array back to BufferedImage
            img_server.setIcon(new ImageIcon(image)); // Display BufferedImage on server UI
        }
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel img_server;
    // End of variables declaration//GEN-END:variables
}
