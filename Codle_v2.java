import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Codle_v2 implements ActionListener, KeyListener {
    JFrame frame;
    JLayeredPane lPane;
    JPanel grid;
    JLabel title;
    String[] words = {"JAVA", "ALGORITHM", "INHERITANCE", "ARRAY", "METHOD"};
    int randomNum = (int)(Math.random()*words.length);
    Tile[][] tiles;
    private int col = 0;
    private int row = 0;
    private boolean last = false;
    private int height = 0;
    private int width = 0;
    
    public Codle_v2() {
        initUI();
    }
    
    private void initUI() {
        frame = new JFrame();
        lPane = new JLayeredPane();
        grid = new JPanel();
        title = new JLabel("Codle");
        
        title.setBounds(225, 20, 150, 50);              //create title of GUI
        title.setBackground(Color.black);
        title.setOpaque(true);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 36));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        lPane.setBounds(50, 100, (75*words[randomNum].length()) + (10*(words.length-1)), (75*6) + (10*5));
        lPane.setOpaque(true);
        
        grid.setBounds(0, 0, (75*words[randomNum].length()) + (10*(words.length-1)), (75*6) + (10*5));             //create panel for Codle grid
        grid.setBackground(Color.black);
        grid.setLayout(new GridLayout(6, words[randomNum].length(), 10, 10));  
        
        tiles = new Tile[6][words[randomNum].length()];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile text = new Tile();
                text.setSize(new Dimension(50, 50));
                // text.setText("Bob");
                text.setBackground(Color.white);
                text.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                text.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
                text.setForeground(Color.black);
                text.setHorizontalAlignment(JTextField.CENTER);
                text.setDocument(new LimitText(1));
     
                tiles[i][j] = text;
                grid.add(text);
                text.addKeyListener(this);

            }
        }
        
        lPane.add(grid, Integer.valueOf(0));

        frame.setTitle("Codle");
        frame.add(title);
        frame.add(lPane);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        width = grid.getWidth();
        height = grid.getHeight();
        frame.setSize(width + 100, height + 180);
        frame.getContentPane().setBackground(Color.black);   
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void restartGame() {
        //basically copying initUI() with some minor changes
    }
    
    private int getLetterCountWord(String letter, String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (letter.equals(word.substring(i, i + 1))) {
                count++;
            }
        }
        return count;
    }
    
    private int getLetterCountArrayList(String letter, ArrayList arr) {
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (letter.equals(arr.get(i))) {
                count++;
            }
        }
        return count;
    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && col == words[randomNum].length()-1) {
            //check guess and change tile colors appopriately
            String guess = "";
            for (int i = 0; i < words[randomNum].length(); i++) {
                guess += tiles[row][i].getText();
            }
            if (guess.length() == words[randomNum].length()) {            //logic for changing color of tiles
                ArrayList<String> temp = new ArrayList<String>();
                for (int i = 0; i < guess.length(); i++) {
                    if (guess.substring(i, i + 1).toUpperCase().equals(words[randomNum].substring(i, i + 1))) {
                        tiles[row][i].setBackground(Color.GREEN);
                        tiles[row][i].setIsGreen(true);             //from Tile class
                        temp.add(guess.substring(i, i + 1).toUpperCase());
                    }
                    tiles[row][i].setForeground(Color.WHITE);
                }
                for (int i = 0; i < guess.length(); i++) {
                    if (words[randomNum].indexOf(guess.substring(i, i + 1).toUpperCase()) != -1 && !(guess.substring(i, i + 1).toUpperCase().equals(words[randomNum].substring(i, i + 1))) && ((getLetterCountWord(guess.substring(i, i + 1), guess.substring(0, i + 1))) + getLetterCountArrayList(guess.substring(i, i + 1).toUpperCase(), temp) <= getLetterCountWord(guess.substring(i, i + 1).toUpperCase(), words[randomNum]))) {
                        tiles[row][i].setBackground(Color.YELLOW);
                    } else if (tiles[row][i].getBackground() != Color.GREEN){
                            tiles[row][i].setBackground(Color.DARK_GRAY);
                    }
                    tiles[row][i].setForeground(Color.WHITE);
                }
            }
            //checking if guess is correct
            boolean isCorrect = true;
            for (int i = 0; i < guess.length(); i++) {
                if (!(tiles[row][i].getIsGreen())) {
                    isCorrect = false;
                    break;
                }
            }
            if (isCorrect) {
                //display winning screen
                JPanel panel = new JPanel();
                panel.setBounds(grid.getBounds().width/2 - 100, grid.getBounds().height/2 - 150, 200, 300);
                panel.setBackground(Color.DARK_GRAY);
                
                JLabel j = new JLabel("Well done.");
                j.setBounds(panel.getBounds().width/2 - 25, panel.getBounds().height/2 - 10, 50, 20);
                j.setBackground(Color.DARK_GRAY);
                j.setHorizontalAlignment(JLabel.CENTER);
                j.setVerticalAlignment(JLabel.CENTER);
                j.setFont(new Font("Helvetica Neue", Font.BOLD, 36));
                j.setForeground(Color.white);
                j.setOpaque(true);

                JButton restartButton = new JButton("Restart");
                restartButton.addActionListener((event) -> restartGame());

                panel.add(j);
                panel.add(restartButton);
                
                lPane.add(panel, Integer.valueOf(1));

                System.out.println("won");
            }
            row++;
            col = -1;
            last = false;
            System.out.println(row);
            if(row == 6)
            {
                System.out.println("bob");
                JPanel panel = new JPanel();
                panel.setBounds(grid.getBounds().width/2 - 100, grid.getBounds().height/2 - 150, 200, 300);
                panel.setBackground(Color.DARK_GRAY);
                
                JLabel j = new JLabel("L");
                j.setBounds(panel.getBounds().width/2 - 25, panel.getBounds().height/2 - 10, 50, 20);
                j.setBackground(Color.DARK_GRAY);
                j.setHorizontalAlignment(JLabel.CENTER);
                j.setVerticalAlignment(JLabel.CENTER);
                j.setFont(new Font("Helvetica Neue", Font.BOLD, 36));
                j.setForeground(Color.white);
                j.setOpaque(true);

                JLabel k = new JLabel("+ Ratio");
                k.setBounds(panel.getBounds().width/2 - 25, panel.getBounds().height/2 - 10, 50, 20);
                k.setBackground(Color.DARK_GRAY);
                k.setHorizontalAlignment(JLabel.CENTER);
                k.setVerticalAlignment(JLabel.CENTER);
                k.setFont(new Font("Helvetica Neue", Font.BOLD, 36));
                k.setForeground(Color.white);
                k.setOpaque(true);

                JLabel l = new JLabel("+ Bozo");
                l.setBounds(panel.getBounds().width/2 - 25, panel.getBounds().height/2 - 10, 50, 20);
                l.setBackground(Color.DARK_GRAY);
                l.setHorizontalAlignment(JLabel.CENTER);
                l.setVerticalAlignment(JLabel.CENTER);
                l.setFont(new Font("Helvetica Neue", Font.BOLD, 36));
                l.setForeground(Color.white);
                l.setOpaque(true);

                JButton restartButton = new JButton("Restart");
                restartButton.addActionListener((event) -> restartGame());

                panel.add(j);
                panel.add(k);
                panel.add(l);
                panel.add(restartButton);
                
                lPane.add(panel, Integer.valueOf(1));
                
                System.out.println("lost");
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
        {
            if (col >= 1)
            {
                col-=2;
            }
            tiles[row][col+1].setText("");
            last = false;
        }
        if((!last && row != 6) || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)               
        {
            tiles[row][col+1].requestFocus();
            tiles[row][col+1].setCaretPosition(0);
        }
        if (!(col == words[randomNum].length() - 1) && (!(col == words[randomNum].length() - 1) && (row != 6)))
        {
            col++;
            if (col == words[randomNum].length()-1)
            {
                last = true;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public static void main (String[] args) {
        Codle_v2 game = new Codle_v2();
    }
}

class LimitText extends PlainDocument {                   //Limits text in each textfield to only one letter
    private int limit;
    LimitText(int limit) {
       super();
       this.limit = limit;
    }
    LimitText(int limit, boolean upper) {
       super();
       this.limit = limit;
    }
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
       if (str == null)
          return;
       if ((getLength() + str.length()) <= limit) {
          super.insertString(offset, str, attr);
       }
    }
 }

 class Tile extends JTextField{
    boolean isGreen; 
    
    public Tile() {
        super();
        isGreen = false;
     }

     public boolean getIsGreen() {
         return isGreen;
     }
     
     public void setIsGreen(boolean b) {
         isGreen = b;
     }
 }