import javax.swing.UIManager;

public class App {
    public static void main(String[] args) throws Exception {
    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        new CentralPanel();
    }
}
