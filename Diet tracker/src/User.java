import javax.swing.*;
import java.io.*;

public class User extends JFrame{
    private final String username;
    private final String password;
    public User () {
        username = "";
        password = "";
    }

    public User (String usernameInput, String passwordInput) {
        username = usernameInput;
        password = passwordInput;
        try{
            //new file writers, print writers
            FileWriter fw = new FileWriter("userinfo.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(username);
            bw.newLine();
            bw.write(password);
            bw.close();

        }catch(Exception e){
            System.err.println("Error in file writing/reading.");
        }
    }

    //checking if username and password exist already
    public boolean checkUP(String usernameInput, String passwordInput){
        try{
            FileReader fr = new FileReader("userinfo.txt");
            BufferedReader br = new BufferedReader(fr);
            String usernameLine = br.readLine();
            String passwordLine = br.readLine();
            if(usernameInput.equals(usernameLine) && passwordInput.equals(passwordLine)){
                return true;
            }
            br.readLine();
            br.close();
            return false;
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error in file writing/reading."+ e);
        }
        return false;
    }//end of checkUP method
}