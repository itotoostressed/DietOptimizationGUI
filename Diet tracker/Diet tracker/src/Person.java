//import java.io.*;
//
//public class person {
//    //instance vars
//    private String username;
//    private String password;
//    private int energyNum;
//    private int popCount;
//
//
//    //constructor
//    public Person(){
//        this.username = "";
//        this.password = "";
//        this.energyNum = 0;
//        this.popCount = 0;
//    }
//
//
//    //setter for name
//    public void setUser(String uname){
//        this.username = uname;
//    }
//    //setter for password
//    public void setPass(String pass){
//        this.password = pass;
//    }
//    //setter for Energy count
//    public void setENum(int eNum){
//        this.energyNum = eNum;
//    }
//    // Getter for username
//    public String getUser() {
//        return this.username;
//    }
//    // Getter for password
//    public String getPass() {
//        return this.password;
//    }
//    //Checking for new user, file I/O to log/sign in
//    //Storing the information to file method
//    public void storeInfo(){
//        String info = this.toString();
//        try{
//            //new filewriters, readers, printwriters
//            FileWriter fw = new FileWriter("PersonInfo.txt", true);
//            PrintWriter pw = new PrintWriter(fw);
//
//
//            pw.println(info);
//            pw.close();
//
//
//        }catch(Exception e){
//            System.err.println("Error in file writing/reading.");
//        }
//    }//end of StoreInfo method
//
//
//    //checking if username and password exist already
//    public boolean checkUP(String u, String p){
//        boolean check = false;
//        try{
//            FileReader fr = new FileReader("PersonInfo.txt");
//            BufferedReader br = new BufferedReader(fr);
//            String line = br.readLine();
//            while(line != null){
//                int index1 = findSpaces1(line);
//                int index2 = findSpaces2(line);
//                String u2 = line.substring(0, index1);
//                String p2 = line.substring(index1+1, index2);
//                if(u2.equals(u) && p2.equals(p)){
//                    check = true;
//                    br.close();
//                    return check;
//                }
//                line = br.readLine();
//            }
//            br.close();
//            return check;
//        }catch(Exception e){
//            System.err.println("Error in file writing/reading."+ e);
//        }
//        return check;
//    }//end of checkUP method
//
//
//    //same as checkUP but only for username
//    public boolean checkU(String u){
//        boolean check = false;
//        try{
//            FileReader fr = new FileReader("PersonInfo.txt");
//            BufferedReader br = new BufferedReader(fr);
//            String line = br.readLine();
//            while(line != null){
//                int index1 = findSpaces1(line);
//                String u2 = line.substring(0, index1);
//                if(u2.equals(u)){
//                    check = true;
//                    br.close();
//                    return check;
//                }
//                line = br.readLine();
//            }
//            br.close();
//            return check;
//        }catch(Exception e){
//            System.err.println("Error in file writing/reading."+ e);
//        }
//        return check;
//    }//end of checking username method
//
//
//    //finds the index of the spaces in a line, for finding username and password in the file
//    //one for the first space, two for the second
//    public static int findSpaces1(String str) {
//        for (int i = 0; i < str.length(); i++) {
//            if (str.charAt(i) == ' ') {
//                return i;
//            }
//        }
//        return -1;
//    }
//    public static int findSpaces2(String str) {
//        int count = 0;
//        for (int i = 0; i < str.length(); i++) {
//            if (str.charAt(i) == ' ') {
//                count++;
//                if (count == 2){
//                    return i;
//                }
//            }
//        }
//        return -1;
//    }//end of checking for spaces methods
//
//
//}//end of person class
