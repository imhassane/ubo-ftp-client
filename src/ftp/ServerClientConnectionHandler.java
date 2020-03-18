package ftp;

import commandes.CommandExecutor;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerClientConnectionHandler implements Runnable {
    private final Socket socket;
    private final BufferedReader reader;
    private final PrintStream printer;

    public ServerClientConnectionHandler(Socket sock, BufferedReader reader, PrintStream printer) {
        this.socket = sock;
        this.reader = reader;
        this.printer = printer;
    }

    @Override
    public void run() {
        String commande = null;

        printer.println("1 Bienvenue ! ");
        printer.println("1 Serveur FTP Personnel.");
        printer.println("0 Authentification");

        try {
            while (!(commande = reader.readLine()).equals("bye")) {
                System.out.println(">> " + commande);
                CommandExecutor.executeCommande(printer, commande);
            }

            printer.println("221 Vous êtes déconnecté");
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }

    }
}
