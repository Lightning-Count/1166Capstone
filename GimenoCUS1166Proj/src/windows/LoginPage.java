package windows;

import mysql.Database;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginPage extends JFrame {
   private static final int FRAME_WIDTH = 450;
   private static final int FRAME_HEIGHT = 450;


   private String password;
   private String username;
   private JLabel JL_signup;
   private JPanel JP_panel;
   private JTextField TF_password;
   private JTextField TF_username;
   private JButton JB_submit;
   //private JButton  JB_forgetP;
   private JButton JB_creatacc;
   private Database dbConnect = new Database();

   public LoginPage(){
       JL_signup = new JLabel("Sign in");
       TF_username = new JTextField();
       TF_password = new JPasswordField();
       JB_submit = new JButton("Submit");
       JB_creatacc = new JButton("Creat Account");
       //JB_forgetP = new JButton("Forgot your password?");
       
       createButton();
       createPanel();

       setSize(FRAME_HEIGHT, FRAME_WIDTH);
   }



   public void createButton(){
       JB_submit.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               username = TF_username.getText();
               password = TF_password.getText();

               int userId = Database.login(username, password);
               if(userId == -1)
               {
            	   JOptionPane.showMessageDialog(null, "Login Failed!");
               }
               else
               {
            	   int userType = Database.checkUsertype(username);
            	   PrimaryWindowViewer window = new PrimaryWindowViewer(userId, userType);
            	   dispose();
               }
            	   
           }
       });
       
       JB_creatacc.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   CreatAccountViewer createAccount = new CreatAccountViewer();
    	   }
       });
       
   }


      
   

   public void createPanel(){
       JP_panel = new JPanel();
       
       JP_panel.setLayout(new GridLayout(15,0));
       JP_panel.add(JL_signup);
       JP_panel.add(new JLabel("Enter Username"));
       JP_panel.add(TF_username);
       JP_panel.add(new JLabel("Enter Password"));
       JP_panel.add(TF_password);
       JP_panel.add(JB_submit);
       JP_panel.add(JB_creatacc);
       //JP_panel.add(JB_forgetP);
       
       add(JP_panel);
   }
}
