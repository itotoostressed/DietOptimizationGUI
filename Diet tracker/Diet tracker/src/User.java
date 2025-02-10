import javax.swing.*;
import java.io.*;

public class User extends JFrame{
    private String username;
    private String password;
    public User () {
        try{
            //new file writers, readers, print writers
            FileWriter fw = new FileWriter("UserInfo.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(this);
            pw.close();

        }catch(Exception e){
            System.err.println("Error in file writing/reading.");
        }
    }
    public void setInfo(String usernameInput, String passwordInput) {
        username = usernameInput;
        password = passwordInput;
    }

    //checking if username and password exist already
    public boolean checkUP(String u, String p){
        boolean check = false;
        try{
            FileReader fr = new FileReader("UserInfo.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String u2 = line;
                String p2 = line;
                if(u2.equals(username) && p2.equals(password)){
                    check = true;
                    br.close();
                    return check;
                }
                line = br.readLine();
            }
            br.close();
            return check;
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error in file writing/reading."+ e);
        }
        return check;
    }//end of checkUP method
}
