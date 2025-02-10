import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu extends JFrame {
    private boolean login = false;


    public Menu() {
        setTitle("Diet Planner");
        setSize(800, 400);
        setDefaultCloseOperation(3);
        setLayout(null);
        getContentPane().setBackground(Color.CYAN);

        JLabel welcome = new JLabel("<html>Welcome to the diet optimizer!<br/>please input your username and password.</html>", SwingConstants.CENTER);
        welcome.setBounds(0, 0, 800, 60);
        welcome.setFont(new Font("Serif", 1, 20));
        add(welcome, "North");

        //can add JLabels here, as USERNAME, and PASSWORD above the textFields, but it seems inefficient. Can add later!

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
//        newAccount.setBounds();

        JButton loginButton = new JButton();
        loginButton.setBounds(300, 260, 200, 50);
        loginButton.setText("Login");
        loginButton.setVisible(true);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login = true;
                setVisible(false);
//                User currentUser = new User(); //creates a new user class that checks for login and everything else.
//                if (currentUser.checkUP(username.getText(), password.getText())) {
//                    System.out.println("this is a previous user");
//                }else {
//                    currentUser.setInfo(username.getText(), password.getText());
//                }
//                if ()
                //file IO goes here. Or you could create a new JPanel/Frame to lead the user to a login screen
            }
        });
    }

    public boolean getLogin() {
        return login;
    }
}// end of Menu class
