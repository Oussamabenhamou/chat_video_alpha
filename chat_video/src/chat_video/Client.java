package chat_video;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.BindException;  
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Client extends javax.swing.JFrame {

    public Client() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        img_client = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(img_client, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(img_client, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pack();
    }
    public static void main(String args[]) throws UnknownHostException, IOException {
        java.awt.EventQueue.invokeLater(() -> new Client().setVisible(true));
        try (Socket s = new Socket("192.168.137.195", 9999); // Changed port to 9999
             ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream())) {
            Webcam cam = Webcam.getDefault();
            cam.open();
            while (true) {
                BufferedImage image = cam.getImage();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", baos); // Convert BufferedImage to byte array
                byte[] byteArray = baos.toByteArray();
                out.writeObject(byteArray); // Send byte array over the network
                out.flush();
                img_client.setIcon(new ImageIcon(image)); // Display BufferedImage on client UI
            }
        }
    }
    
    public static javax.swing.JLabel img_client;
}
