import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private boolean login = false;
    public Menu () {
//        JFrame menuScreen = new JFrame();
//        menuScreen.setTitle("Diet Planner");
//        menuScreen.setSize(800, 400);
//        menuScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Diet Planner");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcome = new JLabel();
        welcome.setText("Welcome to the diet optimizer");
        welcome.setFont(new Font("Serif", Font.BOLD, 20));
        add(welcome, BorderLayout.NORTH);

        JButton loginButton = new JButton();
        loginButton.setBounds(400, 150, 100, 50);




        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Please enter a numerical value", "Enter a number", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public boolean getLogin() {
        return login;
    }

}
