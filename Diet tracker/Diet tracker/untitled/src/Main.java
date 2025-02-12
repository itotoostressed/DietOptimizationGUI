public class Main {
    public static void main(String[] args) {
        Menu mainMenu = new Menu();
        while (!mainMenu.getLogin()) {
            mainMenu.setVisible(true);
        }
        mainMenu.setVisible(false);
        DietGUI frame = new DietGUI();
        frame.setVisible(true);
    }
}