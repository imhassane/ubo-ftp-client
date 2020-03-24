package views;

import ftp.Client;

import javax.swing.*;
import java.io.IOException;

public class ClientForm extends JFrame {
    private JPanel panel1;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton serverBackButton;
    private JButton clientBackButton;
    private JTextField serverCurrentPath;
    private JTextField clientCurrentPath;
    private JTree serverFilesTree;
    private JTree clientFilesTree;
    private JTextArea serverResponse;
    private JTextArea serverResult;

    Client client;

    String result = "", command = "", serverPath = "", clientPath = "", username = "", password = "";
    StringBuilder serverResponseStr = new StringBuilder();

    public ClientForm() throws Exception {
        init();
        config();
        execute();
    }

    private void init() throws IOException, InterruptedException {
        clientPath = System.getProperty("user.dir");
        client = new Client("localhost", 2121);
        Thread.sleep(500);
        while (client.getReader().ready()) {
            serverResponseStr.append(client.getReader().readLine() + "\n");
            serverResponse.setText(serverResponseStr.toString());
        }
    }

    private void config() {
        this.setContentPane(panel1);

        serverFilesTree.setRootVisible(false);
        clientFilesTree.setRootVisible(false);

        usernameField.setText("personne");
        passwordField.setText("abcd");

        // Actions
        loginButton.addActionListener((event) -> {
            client.setUsername(usernameField.getText());
            client.setPassword(passwordField.getText());

            try {
                client.sendCommande("user " + client.getUsername());
                client.sendCommande(String.format("passe %s %s", client.getPassword(), client.getUsername()));
                Thread.sleep(500);
                if (client.getReader().ready()) {
                    result = client.getReader().readLine();
                    serverResponse.append(result + "\n");
                }
                if (client.getReader().ready()) {
                    result = client.getReader().readLine();
                    serverResponse.append(result + "\n");
                }
                if(result.contains("230"))
                    client.setAuthenticated(true);

                execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void execute() throws IOException {
        if(client.isAuthenticated() || result.contains("230")) {
            try {
                client.sendCommande("pwd");
                Thread.sleep(300);
                if (client.getReader().ready()) {
                    result = client.getReader().readLine();
                    if(result.contains("257")) {
                        serverPath = result;
                        serverCurrentPath.setText(result.substring(4));
                        clientCurrentPath.setText(clientPath);
                    }
                    serverResponse.append(serverPath + "\n");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
