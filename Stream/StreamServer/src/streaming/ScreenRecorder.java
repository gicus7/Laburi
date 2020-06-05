package streaming;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenRecorder extends javax.swing.JFrame {

    private static DatagramSocket datagramSocket;
    private int port = 1331;
    private byte[] buffer;

    private MouseEvent getPositionEvent;
    private boolean running = false;
    private boolean streamStatus = false;

    private Color brightRed = new Color(242, 38, 19);
    private Color darkRed = new Color(84, 11, 12);
    private Color brightGreen = new Color(127, 255, 0);
    private Color darkGreen = new Color(0, 75, 0);

    private ArrayList<StreamConnection> streamConnections = new ArrayList<StreamConnection>();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelDisplayImages;
    private javax.swing.JLabel jLabelStreamStatusOff;
    private javax.swing.JLabel jLabelStreamStatusOn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelCloseWindow;
    private javax.swing.JPanel jPanelDragWindow;
    private javax.swing.JPanel jPanelStreamStatusOff;
    private javax.swing.JPanel jPanelStreamStatusOn;

    public ScreenRecorder() {
        initComponents();

        try {
            datagramSocket = new DatagramSocket(port);
        } catch (Exception ex) {
            Logger.getLogger(ScreenRecorder.class.getName()).log(Level.SEVERE, null, ex);
        }

        jPanelStreamStatusOff.setBackground(brightRed);
        jLabelStreamStatusOff.setForeground(Color.white);

        jPanelStreamStatusOn.setBackground(darkGreen);
        jLabelStreamStatusOn.setForeground(Color.GRAY);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScreenRecorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScreenRecorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScreenRecorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScreenRecorder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScreenRecorder().setVisible(true);
            }
        });
    }

    private void jPanelDragWindowMousePressed(java.awt.event.MouseEvent evt) {
        getPositionEvent = evt;
    }

    private void jPanelDragWindowMouseDragged(java.awt.event.MouseEvent evt) {
        setLocation(evt.getXOnScreen() - getPositionEvent.getX(),
                evt.getYOnScreen() - getPositionEvent.getY());
    }

    private void jPanelCloseWindowMousePressed(java.awt.event.MouseEvent evt) {
        running = false;

        String message = "\\offline";
        sendMessageToAllClients(message.getBytes());

        System.exit(0);
    }

    private void jPanelStreamStatusOffMousePressed(java.awt.event.MouseEvent evt) {
        changeStreamStatus();
    }

    private void jPanelStreamStatusOnMousePressed(java.awt.event.MouseEvent evt) {
        changeStreamStatus();
    }

    private void changeStreamStatus() {
        changeStreamStatusColor();

        if (!streamStatus) {
            startStream();
        } else {
            stopStream();
        }

        streamStatus = !streamStatus;
    }

    private void changeStreamStatusColor() {
        if (jPanelStreamStatusOff.getBackground() == brightRed
                || jPanelStreamStatusOn.getBackground() == darkGreen) {

            jPanelStreamStatusOff.setBackground(darkRed);
            jLabelStreamStatusOff.setForeground(Color.GRAY);

            jPanelStreamStatusOn.setBackground(brightGreen);
            jLabelStreamStatusOn.setForeground(Color.white);

        } else if (jPanelStreamStatusOff.getBackground() == darkRed
                || jPanelStreamStatusOn.getBackground() == brightGreen) {

            jPanelStreamStatusOff.setBackground(brightRed);
            jLabelStreamStatusOff.setForeground(Color.white);

            jPanelStreamStatusOn.setBackground(darkGreen);
            jLabelStreamStatusOn.setForeground(Color.GRAY);
        } else {
            System.out.println("Stream status error");
        }
    }

    private void startStream() {
        running = true;
        startStreamThread();
        startWaitingMessagesThread();
        String message = "\\online";
        sendMessageToAllClients(message.getBytes());
    }

    private BufferedImage getImage() {
        try {
            Robot robot = new Robot();

            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(rectangle);

            return bufferedImage;
        } catch (AWTException ex) {
            Logger.getLogger(ScreenRecorder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private void startStreamThread() {
        new Thread("Screen Recorder") {
            BufferedImage image;
            BufferedImage scaledImage;

            @Override
            public void run() {
                try {
                    while (running) {
                        image = getImage();
                        scaledImage = Scalr.resize(image, Scalr.Method.BALANCED, 480, 270);
                        sendMessageToAllClients(getBytes(scaledImage));
                        jLabelDisplayImages.setIcon(new ImageIcon(scaledImage));
                    }
                    image = ImageIO.read(ScreenRecorder.class.getResource("/streaming/images/StreamOffline.jpg"));
                    scaledImage = Scalr.resize(image, Scalr.Method.BALANCED, 480, 270);
                    jLabelDisplayImages.setIcon(new ImageIcon(scaledImage));
                } catch (IOException ex) {
                    Logger.getLogger(ScreenRecorder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

    private void startWaitingMessagesThread() {
        new Thread("Waiting for Clients Thread") {
            DatagramPacket packet;
            byte[] waitingBuffer;

            @Override
            public void run() {
                while (running) {
                    try {
                        waitingBuffer = new byte[256];
                        packet = new DatagramPacket(waitingBuffer, waitingBuffer.length);
                        datagramSocket.receive(packet);
                        processingMessage(packet);
                    } catch (IOException ex) {
                        Logger.getLogger(ScreenRecorder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    private byte[] getBytes(BufferedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", baos);
        } catch (IOException ex) {
            Logger.getLogger(ScreenRecorder.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    private byte[] getBytes(String message) {
        buffer = new byte[256];
        buffer = message.getBytes();

        return buffer;
    }

    private void sendMessage(byte[] bytes, InetAddress address, int port) {
        try {
            if (running) {
                // Send bytes
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
                datagramSocket.send(packet);
            }
        } catch (IOException ex) {
            Logger.getLogger(ScreenRecorder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendMessageToAllClients(byte[] bytes) {
        for (int i = 0; i < streamConnections.size(); i++) {
            StreamConnection streamConnection = streamConnections.get(i);
            InetAddress address = streamConnection.getAddress();
            int port = streamConnection.getPort();
            sendMessage(bytes, address, port);
        }
    }

    // Proccesing message
    private void processingMessage(DatagramPacket packet) {
        String message = new String(packet.getData());
        if (message.startsWith("\\connect:")) {
            String name = message.substring(message.indexOf(":") + 1, message.indexOf("\\end"));
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            if (verifyName(name)) {
                StreamConnection newUser = new StreamConnection(name, address, port);
                streamConnections.add(newUser);
                sendMessageToAllClients(getBytes("\\message: [" + name + "] entered the stream." + "\\end"));
                String messageStreamOnline = "\\online";
                sendMessageToAllClients(messageStreamOnline.getBytes());
            } else
                sendMessage("\\busyName".getBytes(), address, port);
        } else if (message.startsWith("\\disconnect:")) {
            String name = message.substring(message.indexOf(":") + 1, message.indexOf("\\end"));
            for (int i = 0; i < streamConnections.size(); i++) {
                StreamConnection user = streamConnections.get(i);
                if (user.getName().equals(name)) {
                    streamConnections.remove(i);
                    break;
                }
            }
            sendMessageToAllClients(getBytes("\\message: [" + name + "] came out of the stream." + "\\end"));
        }
    }

    boolean verifyName(String name) {
        for (int i = 0; i < streamConnections.size(); i++) {
            StreamConnection user = streamConnections.get(i);
            if (user.getName().equals(name)) {
                return false;
            }
        }

        return true;
    }

    private void stopStream() {
        sendMessageToAllClients("\\offline".getBytes());
        running = false;
    }
    // End of variables declaration//GEN-END:variables
}
