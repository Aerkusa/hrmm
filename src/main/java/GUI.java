import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI {
    String apiSecret = "";
    String apiID = "";
    
    String watchOneToken   = "";
    String watchTwoToken   = "";
    String watchThreeToken = "";
    String watchFourToken  = "";
    
    private BufferedImage watchOneImage;
    private BufferedImage watchTwoImage;
    private BufferedImage watchThreeImage;
    private BufferedImage watchFourImage;
    
    
    GUI(){
        JFrame myWindow = new JFrame("Heart Rate Monitoring Project");
        myWindow.setSize(1100, 550);
        myWindow.setLocationRelativeTo(null);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setResizable(true);
        JPanel screen = new JPanel();
        screen.setLayout(null);
        myWindow.add(screen);
        
        
        JButton selectButtonOne = new JButton("Get Data");
        this.buttonLooks(selectButtonOne);
        selectButtonOne.addActionListener((ActionEvent e) -> {
            Main.makeACall("token: Watch One");
        });
        screen.add(selectButtonOne);
        
        JButton selectButtonTwo = new JButton("Get Data");
        selectButtonTwo.addActionListener((ActionEvent e) -> {
            Main.makeACall("token: Watch Two");
        });
        this.buttonLooks(selectButtonTwo);
        screen.add(selectButtonTwo);
        
        JButton selectButtonThree = new JButton("Get Data");
        selectButtonThree.addActionListener((ActionEvent e) -> {
            Main.makeACall("token: Watch Three");
        });
        this.buttonLooks(selectButtonThree);
        screen.add(selectButtonThree);
        
        JButton selectButtonFour = new JButton("Get Data");
        selectButtonFour.addActionListener((ActionEvent e) -> {
            Main.makeACall("token: Watch Four");
        });
        this.buttonLooks(selectButtonFour);
        screen.add(selectButtonFour);
        
        selectButtonOne.setBounds  (150, 400, 115, 40);
        selectButtonTwo.setBounds  (400, 400, 115, 40);
        selectButtonThree.setBounds(600, 400, 115, 40);
        selectButtonFour.setBounds (840, 400, 115, 40);
        
        JLabel welcome = new JLabel("Welcome Professor!");
        welcome.setFont(new Font("Serif", Font.BOLD, 35));
        screen.add(welcome);
        welcome.setBounds(400, 30, 350, 30);
        
        
        JLabel watchOneLabel = new JLabel("Watch One");
        watchOneLabel.setFont(new Font("", Font.PLAIN, 20));
        screen.add(watchOneLabel);
        
        JLabel watchTwoLabel = new JLabel("Watch Two");
        watchTwoLabel.setFont(new Font("", Font.PLAIN, 20));
        screen.add(watchTwoLabel);
        
        JLabel watchThreeLabel = new JLabel("Watch Three");
        watchThreeLabel.setFont(new Font("", Font.PLAIN, 20));
        screen.add(watchThreeLabel);
        
        JLabel watchFourLabel = new JLabel("Watch Four");
        watchFourLabel.setFont(new Font("", Font.PLAIN, 20));
        screen.add(watchFourLabel);
        
        watchOneLabel.setBounds  (170, 120, 200, 50);
        watchTwoLabel.setBounds  (410, 120, 200, 50);
        watchThreeLabel.setBounds(600, 120, 200, 50);
        watchFourLabel.setBounds (850, 120, 200, 50);
        
        try {
            watchOneImage = ImageIO.read(new File("src\\main\\java\\watchOne.png"));
            Image sizedImage = watchOneImage.getScaledInstance(250, 300, watchOneImage.SCALE_DEFAULT);
            JLabel imageOne = new JLabel(new ImageIcon(sizedImage));
            
            watchTwoImage = ImageIO.read(new File("src\\main\\java\\watchTwo.png"));
            Image sizedImageTwo = watchTwoImage.getScaledInstance(250, 300, watchTwoImage.SCALE_DEFAULT);
            JLabel imageTwo = new JLabel(new ImageIcon(sizedImageTwo));
            
            watchThreeImage = ImageIO.read(new File("src\\main\\java\\watchTwo.png"));
            Image sizedImageThree = watchThreeImage.getScaledInstance(250, 310, watchThreeImage.SCALE_DEFAULT);
            JLabel imageThree = new JLabel(new ImageIcon(sizedImageThree));
            
            watchFourImage = ImageIO.read(new File("src\\main\\java\\watchThree.png"));
            Image sizedImageFour = watchFourImage.getScaledInstance(250, 310, watchFourImage.SCALE_DEFAULT);       
            JLabel imageFour = new JLabel(new ImageIcon(sizedImageFour));
            
            imageOne.setBounds  (65, 130, 300, 300);
            imageTwo.setBounds  (310, 130, 280, 300);
            imageThree.setBounds(510, 130, 300, 300);
            imageFour.setBounds (755, 140, 300, 300);
            
            screen.add(imageOne);
            screen.add(imageTwo);
            screen.add(imageThree);
            screen.add(imageFour);            

        }    
         catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Input/Ouput Exception", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "An Exception", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        finally{
            myWindow.setVisible(true);
        }
        
        
   }
        
    private void buttonLooks(JButton b){
        b.setForeground(Color.BLACK);
        b.setFocusPainted(false);
        b.setFont(new Font("", Font.BOLD,18));
        b.setBorder(BorderFactory.createBevelBorder(1, Color.red, Color.blue));
    }
        
        
        
        
        
}