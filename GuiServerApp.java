import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiServerApp extends JFrame {
    private JTextField messageField;
    private JTextArea chatArea;
    private JButton exitButton;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public GuiServerApp() {
        setTitle("Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        messageField = new JTextField();
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        exitButton = new JButton("Exit");
        exitButton.setEnabled(false);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                sendMessage(message);
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(exitButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 350);
        setLocationRelativeTo(null);
        setVisible(true);

        setupServer();
    }

    private void setupServer() {
        try {
            serverSocket = new ServerSocket(12345);
            clientSocket = serverSocket.accept();

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;
            while ((message = in.readLine()) != null) {
                chatArea.append("From Client: " + message + "\n");
                if (message.equalsIgnoreCase("terminate")) {
                    sendMessage("terminate");
                    exitButton.setEnabled(true);
                    break;
                }
            }

            chatArea.append("Client has logged off. Server is now closing down.\nClick the Exit button to close the window.");
            messageField.setEnabled(false);

            closeConnections();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        out.println(message);
        chatArea.append("From Server: " + message + "\n");
        if (message.equalsIgnoreCase("terminate")) {
            closeConnections();
        }
        messageField.setText("");
    }

    private void closeConnections() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeWindow() {
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        new GuiServerApp();
    }
}
