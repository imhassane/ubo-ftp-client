import views.ClientForm;

import javax.swing.*;
import java.io.IOException;

public class ClientWindow {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        ClientForm form = new ClientForm();
        form.setSize(900, 700);
        form.setVisible(true);
    }
}
