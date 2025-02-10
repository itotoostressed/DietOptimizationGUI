import org.apache.commons.math3.optim.linear.*;
public class Main {
    public static void main(String[] args) {
        Menu mainMenu = new Menu();
        while (!mainMenu.getLogin()) {
            mainMenu.setVisible(true);
        }
            DietGUI frame = new DietGUI();
            frame.setVisible(true);
    }
}
