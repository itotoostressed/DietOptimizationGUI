import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu extends JFrame {
    private boolean login = false;
    User currentUser = new User(); //creates a new user class that checks for login and everything else.

    public Menu() {

        setTitle("Diet Planner");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.CYAN);

        JLabel welcome = new JLabel("Welcome to the diet optimizer!", SwingConstants.CENTER);
        welcome.setBounds(0, 0, 800, 60);
        welcome.setFont(new Font("Serif", 1, 20));
        add(welcome, "North");

        JLabel usernameTitle = new JLabel("Username:");
        usernameTitle.setBounds(150, 100, 100, 30);
        add(usernameTitle);

        JTextField username = new JTextField();
        username.setBounds(150, 130, 500, 30);
        add(username);

        JLabel passwordTitle = new JLabel("Password:");
        passwordTitle.setBounds(150, 160, 100, 30);
        add(passwordTitle);

        JTextField password = new JTextField();
        password.setBounds(150, 190, 500, 30);
        add(password);

        JButton newAccount = new JButton();
        newAccount.setVisible(true);
        newAccount.setText("create account");
        newAccount.setBounds(450, 260, 200, 50);
        add(newAccount);

        JButton loginButton = new JButton();
        loginButton.setBounds(150, 260, 200, 50);
        loginButton.setText("Login");
        loginButton.setVisible(true);
        add(loginButton);

        newAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundPlayer.playSound("mixkit-cool-interface-click-tone-2568.wav");
                if (currentUser.checkUP(username.getText(), password.getText())) {
                    JOptionPane.showMessageDialog(Menu.this, "There is already an account with these details ");
                }
                else {
                    currentUser = new User(username.getText(), password.getText());
                    JOptionPane.showMessageDialog(Menu.this, "account created");
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundPlayer.playSound("mixkit-cool-interface-click-tone-2568.wav");
                if (currentUser.checkUP(username.getText(), password.getText())) {
                    login = true;
                    setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(Menu.this, "Incorrect password or username");
                }
            }
        });
    }

    public boolean getLogin() {
        return login;
    }
}// end of Menu class