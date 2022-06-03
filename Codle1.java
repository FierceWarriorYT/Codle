import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Codle1 implements ActionListener, KeyListener {
    JFrame frame;
    JPanel grid;
    JLabel title;
    String[] words = {"java", "algorithm", "inheritance", "array"};
    int randomNum = (int)(Math.random()*words.length);
    JTextField[][] tiles;
    private static JTextField text1;
    private int col = 0;
    private int row = 0;
    private boolean last = false;
    
    public Codle1() {
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
        
        tiles = new JTextField[6][words[randomNum].length()];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                JTextField text = new JTextField();
                text.setSize(new Dimension(50, 50));
                // text.setText("Bob");
                text.setBackground(Color.yellow);
                text.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                text.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
                text.setForeground(Color.black);
                text.setHorizontalAlignment(JTextField.CENTER);
     
                tiles[i][j] = text;
                grid.add(text);
                text.addKeyListener(this);

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

    public void actionPerformed(ActionEvent e)
    {
        
    }

    public void keyPressed(KeyEvent e)
    {
        // int code = e.getKeyCode();
        System.out.println("Bob");
        if(last == false)
        {
            tiles[row][col+1].requestFocus();
            tiles[row][col+1].setCaretPosition(0);
        }
        if (!(col == words[randomNum].length() - 1) && (!(col == words[randomNum].length() - 1) && (row != 6)))
        {
            col++;
        }
        if (col == words[randomNum].length() - 1 && row != 6)
        {
            if (row + 1 != 6)
            {
                row++;
                col = -1;
            } else
            {
                last = true;
            }
        }
        System.out.println(row + " "+ col);
    }

    public void keyTyped(KeyEvent e)
    {
    }
    
    public void keyReleased(KeyEvent e)
    {

    }


    private void runGame() {
        // for (int col = 0; col < words[randomNum].length(); col++) {
        //     int i = 0;
        //     while (col < words[randomNum].length()) {
        //         if (tiles[col][i].getText().length() > 0) {
        //             i++;
        //         }
        //     }
        // }
    }
    
    public static void main (String[] args) {
        Codle1 game = new Codle1();
        // text1.requestFocus();
        // text1.setCaretPosition(0);
    }
    
    
}
