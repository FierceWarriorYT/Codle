import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Codle2 implements ActionListener, KeyListener {
    JFrame frame;
    JPanel grid;
    JLabel title;
    // String[] words = {"java", "algorithm", "inheritance", "array"};
    String[] words = {"java"};

    int randomNum = (int)(Math.random()*words.length);
    JTextField[][] tiles;
    private int col = 0;
    private int row = 0;
    private boolean last = false;
    
    public Codle2() {
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
        frame.setTitle("Codle");
        frame.add(title);
        frame.add(grid);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(grid.getBounds().height, grid.getBounds().width);
        frame.getContentPane().setBackground(Color.black);   
        frame.setLayout(null);
        frame.setVisible(true);
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
                  if (guess.substring(i, i + 1).equals(words[randomNum].substring(i, i + 1))) {
                      tiles[row][i].setBackground(Color.GREEN);
                      temp.add(guess.substring(i, i + 1));
                  }
              }
              for (int i = 0; i < guess.length(); i++) {
                  if (words[randomNum].indexOf(guess.substring(i, i + 1)) != -1 && !(guess.substring(i, i + 1).equals(words[randomNum].substring(i, i + 1))) && ((getLetterCountWord(guess.substring(i, i + 1), guess.substring(0, i + 1))) + getLetterCountArrayList(guess.substring(i, i + 1), temp) <= getLetterCountWord(guess.substring(i, i + 1), words[randomNum]))) {
                      tiles[row][i].setBackground(Color.YELLOW);
                  } else if (tiles[row][i].getBackground() != Color.GREEN){
                          tiles[row][i].setBackground(Color.DARK_GRAY);
                  }
              }
          }
          row++;
          col = -1;
          last = false;
      }
      if(!last)               
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
        Codle game = new Codle();
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