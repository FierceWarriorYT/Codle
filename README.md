import javax.swing.*;
import java.awt.*;

public class Codle {
    JFrame frame;
    JPanel grid;
    JLabel title;
    String[] words = {"java", "algorithm", "inheritance", "array"};
    int randomNum = (int)(Math.random()*words.length);
    JTextField[][] tiles;
    
    public Codle() {
        initUI();
    }
    
    private void initUI() {
        frame = new JFrame();
        grid = new JPanel();
        title = new JLabel("Codle");
        
        title.setBounds(225, 20, 150, 50);              //create title of GUI
        title.setBackground(Color.black);
        title.setOpaque(true);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 36));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        
        grid.setBounds(50, 100, (75*words[randomNum].length()) + (10*(words.length-1)), (75*6) + (10*5));             //create panel for Codle grid
        grid.setBackground(Color.black);
        grid.setLayout(new GridLayout(6, words[randomNum].length(), 10, 10));  
        
        tiles = new JTextField[words[randomNum].length()][6];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                JTextField text = new JTextField();
                text.setSize(new Dimension(50, 50));
                text.setBackground(Color.yellow);
                text.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                text.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
                text.setForeground(Color.black);
                text.setHorizontalAlignment(JTextField.CENTER);
     
                tiles[i][j] = text;
                grid.add(text);
            }
        }
        
        frame.setTitle("Codle");
        frame.add(title);
        frame.add(grid);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(grid.getBounds().height, grid.getBounds().width);
        frame.getContentPane().setBackground(Color.black);   
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    private void runGame() {
        for (int col = 0; col < words[randomNum].length(); col++) {
            int i = 0;
            while (col < words[randomNum].length()) {
                if (tiles[col][i].getText().length() > 0) {
                    i++;
                }
            }
        }
    }
    
    public static void main (String[] args) {
        Codle game = new Codle();
        game.runGame();
    }
    
    
}
