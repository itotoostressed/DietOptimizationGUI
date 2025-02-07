public class Main {

    public static void main(String[] args) {
        Menu MenuScreen = new Menu();
        MenuScreen.setVisible(true);
        boolean login = MenuScreen.getLogin();
        if (login) {
            DietGUI frame = new DietGUI();
            frame.setVisible(true);
        }
    }
}