import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.html.StyleSheet;

public class Main {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    
    //each box is a place on the board
    private static JButton[][] boxes = new JButton[6][7];
    
    //This is for the game status in real time
    private static int[][] VirtualBoard = new int[6][7];         
    //Both board and virtualBoard start from upper left with 0 index
    
    private static Moves[] PossibleMoves = new Moves[7];
    
    
    private JPanel board;
    
    private static int movesNumber = 0;
    private static JLabel moveNumberLable;
    private static JLabel lastMoveColorLabe;
    private static JLabel scoreLable;
    
    private static Color PlayerColor= Color.RED;
    private static Color CPUColor = Color.BLUE;
    
    private static int firstOrSecond;
    
    private static int playerInput =1;
    private static int CPUInput = -1;
    private static boolean firstMove =true;
    
    private static String lastMoveColor = "";
    
    private static StringBuilder report = new StringBuilder("The Game starts!\n");
    
    Main() {
        initializeGui();
    }

    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);

        board = new JPanel(new GridLayout(0, 7));
        board.setBorder(new LineBorder(Color.BLACK));
        
        
        
        gui.add(board,1);

        
        Insets buttonMargin = new Insets(0,0,0,0);
        
        for (int ii = 0; ii < boxes.length; ii++) {
            for (int jj = 0; jj < boxes[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                
                // our pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.WHITE);
                }
                
                VirtualBoard[ii][jj]=0;
                boxes[ii][jj] = b;
                
                
            }
        }

        
        board.add(new JLabel("Click on the first", SwingConstants.CENTER));
        board.add(new JLabel(" cell to select a c", SwingConstants.CENTER));
        board.add(new JLabel("olumn                    ", SwingConstants.CENTER));
        //Make the rest of the line empty
        for(int i=0;i<4;i++){
        	board.add(new JLabel("", SwingConstants.CENTER));
        }
        
        board.add(new JLabel("Last Move:", SwingConstants.CENTER));
        
        lastMoveColorLabe = new JLabel(lastMoveColor, SwingConstants.CENTER);
        board.add(lastMoveColorLabe);

        //make the rest of the line empty
        for(int i=0;i<5;i++){
        	board.add(new JLabel("", SwingConstants.CENTER));
        }
        
        
        // fill the board with pieces(boxes)
        for (int ii = 0; ii < 6; ii++) {
            for (int jj = 0; jj < 7; jj++) {
            	board.add(boxes[ii][jj]);
            	
            }
            
        }
        
        
        board.add(new JLabel("Score:", SwingConstants.CENTER));
        scoreLable = new JLabel("0-0", SwingConstants.CENTER);
        board.add(scoreLable);
        
        //make the rest of the line empty
        board.add(new JLabel("", SwingConstants.CENTER));
        
        String firstColor = "Red";
        if(firstOrSecond ==1){
        	if(CPUColor == Color.BLUE)
        		firstColor = "Blue";
        	}
        else
        	if(PlayerColor== Color.BLUE)
        		firstColor = "Blue";
        
        board.add(new JLabel(firstColor + " First", SwingConstants.CENTER));
        
        board.add(new JLabel("", SwingConstants.CENTER));
        board.add(new JLabel("Moves so far:", SwingConstants.CENTER));
        moveNumberLable = new JLabel(Integer.toString(movesNumber), SwingConstants.CENTER);
        board.add(moveNumberLable);
        
        //Action listeners for first box of each column
        boxes[0][0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FallDown(0);
			}
		});
        
        boxes[0][1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FallDown(1);
			}
		});
        
        boxes[0][2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FallDown(2);
			}
		});
        
        boxes[0][3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FallDown(3);
			}
		});
        
        boxes[0][4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FallDown(4);
			}
		});
        
        boxes[0][5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FallDown(5);
			}
		});
        
        boxes[0][6].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FallDown(6);
			}
		});
    }

    public final JComponent getBoard() {
        return board;
    }

    public final JComponent getGui() {
        return gui;
    }

    
    //Starts from the lowest position and pin the first box which is available
    public final void FallDown(int colNumber){
    	int i=5;
		while (VirtualBoard[i][colNumber] != 0) {
			i--;
			if(i==-1){
				JOptionPane.showMessageDialog(new Frame(), "Invalid Move!");
				return;
			}
			
		}
    	VirtualBoard[i][colNumber] = playerInput;
    	//Add the move to the report
    	report.append("Player chooses position " + Integer.toString(6-i) + "," + Integer.toString(colNumber+1)  + "\n");
    	
    	boxes[i][colNumber].setBackground(PlayerColor);
    	movesNumber++;
    	
    	//set the last move color
    	if(PlayerColor == Color.RED)
    		lastMoveColor="Red";
    	else
    		lastMoveColor="Blue";
    	lastMoveColorLabe.setText(lastMoveColor);
    	
    	//update movesNumber and Scores
    	moveNumberLable.setText(Integer.toString(movesNumber));
    	
    	scoreLable.setText(Integer.toString(score(1)) + "-"
				+ Integer.toString(score(-1)));
    	//check to see if the game is over
		if (!finished()) {			
			CPUTurn();
		}
    }
    
    
    //A method to shuffle the array
    static void shuffleArray(Moves[] ar)
    {
      Random rnd = new Random();
//      for (int i = ar.length - 1; i > 0; i--)
//      {
//        int index = rnd.nextInt(i + 1);
//        // Simple swap
//        Moves a = ar[index];
//        ar[index] = ar[i];
//        ar[i] = a;
//      }
    	
    	Moves temp  = ar[3];
    	ar[3]=ar[6];
    	ar[6]=temp;
    	
    	temp  = ar[4];
    	ar[4]=ar[5];
    	ar[5]=temp;
    	
    	temp  = ar[0];
    	ar[0]=ar[3];
    	ar[3]=temp;
    	
    }
    
    
    //The same as FallDown method but the column number is chosen  by the greedy approach
    //choose the position that leads you to a longer sequence of coins
	public final static void CPUTurn() {
		
		Moves Chosen = new Moves(-1,-1);
		boolean found =false;
		
//		if(firstMove){
//			firstMove = false;
//			Chosen = new Moves(5,3);
//			found = true;
//		}
		//Puuting all possible moves in an array with the same name
		PossibleMoves = new Moves[7];
		for (int tempCol = 0; tempCol <= 6; tempCol++) {
			int i = 5;
			boolean full = false;
			while (VirtualBoard[i][tempCol] != 0) {
				i--;
				if (i == -1) {
					full = true;
					break;
				}

			}
			if (!full) {
				Moves newMove = new Moves(i, tempCol);
				newMove.setPossibleConneteds(VirtualBoard);
				PossibleMoves[tempCol] = newMove;
			}
		}
		
		//Just shuffling the array to prevent it from always starting the game from most left column
		shuffleArray(PossibleMoves);
		
		
		
		
		//if any move leads to a 4-connect that;s the choice
		if(!found){
				for (Moves move : PossibleMoves) {
					if (move != null) {
						if (move.getAlmost4Connecteds() > 0) {
							Chosen=move;
							found =true;
							System.out.println("almost4: " + move.getAlmost4Connecteds() + " " + move.getRow() + " " + move.getColumn());
						}
					}
				}
		}
		//if not try 3-connect
		if(!found){
					for (Moves move : PossibleMoves) {
						if (move != null) {
							if (move.getAlmost3Connceteds() > 0) {
								Chosen = move;
								found=true;
								System.out.println("almost3: " + move.getAlmost3Connceteds() + " " +  move.getRow() + " " + move.getColumn());
							}
						}
					}
				}
				
		

		//if any move leads to a 4-connect that;s the choice
		if (!found) {
			for (Moves move : PossibleMoves) {
				if (move != null) {
					if (move.getPossible4Conneteds() > 0) {
						Chosen = move;
						found = true;
						System.out.println("possible4: " + move.getRow() + " " + move.getColumn());
					}
				}
			}
		}
		//if not try 3-connect
		if(!found){
			for (Moves move : PossibleMoves) {
				if (move != null) {
					if (move.getPossible3Conneteds() > 0) {
						Chosen = move;
						found=true;
						System.out.println("possible4: " + move.getRow() + " " + move.getColumn());
					}
				}
			}
		}
		
		//At last let's try 2-connect
		if(!found){
			for (Moves move : PossibleMoves) {
				if (move != null) {
					if (move.getPossible2Conneteds() > 0) {
						Chosen = move;
						found=true;
					}
				}
			}
		}
		
		//If none worked just pick a random one
		if (!found) {			
			
			int randNumber = new Random().nextInt(6 - 0 + 1) + 0;
			while (PossibleMoves[randNumber] == null)
				randNumber = new Random().nextInt(6 - 0 + 1) + 0;

			Chosen = PossibleMoves[randNumber];
		}
		
		int colNumber = Chosen.getColumn();
		int rowNumber = Chosen.getRow();

			if (rowNumber>=0 && colNumber>=0 && VirtualBoard[rowNumber][colNumber] == 0 ) {
				VirtualBoard[rowNumber][colNumber] = CPUInput;
				report.append("CPU chooses position "
						+ Integer.toString(6 - rowNumber) + ","
						+ Integer.toString(colNumber + 1) + "\n");

				boxes[rowNumber][colNumber].setBackground(CPUColor);
				movesNumber++;
				if (CPUColor == Color.RED)
					lastMoveColor = "Red";
				else
					lastMoveColor = "Blue";
				lastMoveColorLabe.setText(lastMoveColor);

				
				scoreLable.setText(Integer.toString(score(1)) + "-"
						+ Integer.toString(score(-1)));
				moveNumberLable.setText(Integer.toString(movesNumber));
				finished();
				return;
			} else{
				System.out.println("CPU cannot play in " + rowNumber + ","
						+ colNumber + "\nPossible move: " );
				for(Moves mov : PossibleMoves){
					System.out.println(mov.getRow() + ", " + mov.getColumn() + "\n");
				}
				System.out.println("\n\n");
			}
		}


//		}
    
    
    //based on the number of moves check to see if the game is over
    public static boolean finished(){
    	if(movesNumber == 42){
    		int playerScore = score(1);
    		int CPUScore = score(-1);
    		String wow;
    		String winner;
    		
    		if(playerScore > CPUScore){
    			wow = "You Won!";
    			winner="Player";
    		}
    		else{
    			wow="You Lost!";
    			winner="CPU";
    		}
    		
    		
    		report.append("Final Score is " + playerScore + " to " + CPUScore + " So " + winner + " Wins\n");
    		OutputResult(report.toString());
    		
    		JOptionPane.showMessageDialog(new Frame(),  wow + "\nPlayer Score: " + playerScore + "\n CPU Score: " + CPUScore);
    		return true;
    	}
    	else return false;
    }
    
    /*Score for each player
     * param: 1 or -1 which is respectively for player or CPU
     * for each position on the board counts the number of quadruples that start with that position in each of 4 directions
    */
    public static int score(int toCount){    	
    	int score =0;
		for (int i = 0; i < VirtualBoard.length; i++) {
			for (int j = 0; j < VirtualBoard[i].length; j++) {
				score += quadrupleNumber(i,j,toCount);
			}
		}
		
		return score;
    }
        
    /*For each position returns the number of quadruples
     * params: coloumn and row and the input to compare with
     * */
    public static int quadrupleNumber(int row, int col, int toCount){
    	
    	return horizontalQuadruples(row, col, toCount)+verticallQuadruples(row, col, toCount)+rightToLeftQuadruples(row, col, toCount)+leftToRightQuadruples(row, col, toCount);
    	
    }
    
    
    //For each direction we have a method to see if there is a quadruple in that direction which starts with that box
    public static int horizontalQuadruples(int row, int col, int toCount){
    	
    	if(col >3)
    		return 0;
    	
    	int count =0;
    	while(count<4 && col<7 && row < 6){
			if (toCount != VirtualBoard[row][col]) {
				break;
			}
			
			count++;
			col++;
    		
    	}
    	if(count == 4)
    		return 1;
    	
    	return 0;
    	
    }
    
    public static int verticallQuadruples(int row, int col, int toCount){
    	if(row > 2)
    		return 0;
    	
    	int count =0;
    	while(count<4 && col<7 && row < 6){
			if (toCount != VirtualBoard[row][col]) {
				break;
			}
			
			count++;
			row++;
    		
    	}
    	if(count == 4)
    		return 1;
    	
    	return 0;
    }
    
    //bottom left to upper right
    public static int leftToRightQuadruples(int row, int col, int toCount){
    	if(row < 3 || col > 3)
    		return 0;
    	
    	int count =0;
    	while(count<4 && col<7  && row < 6){
			if (toCount != VirtualBoard[row][col]) {
				break;
			}
			
			count++;
			row--;
			col++;
    		
    	}
    	if(count == 4)
    		return 1;
    	
    	return 0;
    }
    
    //bottom right to upper left
    public static int rightToLeftQuadruples(int row, int col, int toCount){
    	if(row > 2 || col > 3)
    		return 0;
    	
    	int count =0;
    	while(count<4 && col<7 && row < 6){
			if (toCount != VirtualBoard[row][col]) {
				break;
			}
			
			count++;
			row++;
			col++;
    		
    	}
    	if(count == 4)
    		return 1;
    	
    	return 0;
    }
    
    
    //right the report
    public static void OutputResult(String toOutPut){
		try {
			 

			File file = new File("Game Report.txt");
 
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(toOutPut);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
            	
            Object[] options = {"First","Second"};
            firstOrSecond = JOptionPane.showOptionDialog(new JFrame(""),"Would you like to play first or second?","Welcome!",
            							JOptionPane.YES_NO_OPTION,
            							JOptionPane.QUESTION_MESSAGE,
            							null,
            							options,options[0]);
            
            Object[] ColorOptions = {"Red","Blue"};
            int color = JOptionPane.showOptionDialog(new JFrame(""),"Choose your Color","Welcome!",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					ColorOptions,ColorOptions[0]);
            
            //set the colors to players
            if(color ==1){
            	PlayerColor = Color.BLUE;
            	CPUColor = Color.RED;
            }
            	
                
                Main cb =
                        new Main();

                JFrame f = new JFrame("Max-Connect4");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
                
                //if player chooses to start second CPU starts the game
                if(firstOrSecond == 1){
                	report.append("CPU Starts \n");
                	CPUTurn();
                	
                	}else
                		report.append("Player Starts \n");
            }
        };
        SwingUtilities.invokeLater(r);
    }
}