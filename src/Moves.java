
//A class to store a possible move with its row and column number
public class Moves {
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getPossible4Conneteds() {
		return possible4Conneteds;
	}
	
	public int getPossible3Conneteds() {
		return possible3Conneteds;
	}
	
	public int getPossible2Conneteds() {
		return possible2Conneteds;
	}

	public Moves(int row, int column) {
		super();
		possible3Conneteds=0;
		possible4Conneteds=0;
		this.row = row;
		this.column = column;
	}
	
	//this method determines the number of possible n-connects that can be possibly achieved choosing this place
	public void setPossibleConneteds(int[][] VirtualBoard) {
		
		setAlmostConneteds(VirtualBoard);
		
		possible4Conneteds =  0;
		possible3Conneteds =0;
		possible2Conneteds=0;
		
		int UpperRowCurser = row;
		int RightColumnCurser=column+1;
		int LowerRowCurser = row;
		int LeftColumnCurser=column-1;
		int counter = 1;
		if(RightColumnCurser >6)
			RightColumnCurser=6;
		if(LeftColumnCurser <0)
			LeftColumnCurser=0;
		
		//For Horizontal 4-connect
		if(RightColumnCurser<=6)
		while(VirtualBoard[UpperRowCurser][RightColumnCurser] !=1 && RightColumnCurser<6 ){
			RightColumnCurser++;
			counter++;
		}
		
		if(LeftColumnCurser>=0)
		while(VirtualBoard[LowerRowCurser][LeftColumnCurser] !=1 && LeftColumnCurser>0 ){
			LeftColumnCurser--;
			counter++;
		}
		if(counter==4)
			possible4Conneteds++;
		else if(counter==3)
			possible3Conneteds++;
		else if(counter==2)
			possible2Conneteds++;
		
		
		//For Vertical 4-connect
		RightColumnCurser=column;
		LeftColumnCurser=column;
		UpperRowCurser =row -1;
		LowerRowCurser=row +1;
		counter =1;
		
		if(LowerRowCurser >5)
			LowerRowCurser=5;
		if(UpperRowCurser <0)
			UpperRowCurser=0;
		
		
		if(UpperRowCurser>=0)
		while(VirtualBoard[UpperRowCurser][RightColumnCurser] !=1 && UpperRowCurser>0 ){
			UpperRowCurser--;
			counter++;
		}
		
		if(LowerRowCurser<=5)
		while(VirtualBoard[LowerRowCurser][LeftColumnCurser] !=1 && LowerRowCurser<5 ){
			LowerRowCurser++;
			counter++;
		}
		
		if(counter==4)
			possible4Conneteds++;
		else if(counter==3)
			possible3Conneteds++;
		else if(counter==2)
			possible2Conneteds++;
		
		//From bottom left to top right
		RightColumnCurser=column+1;
		LeftColumnCurser=column-1;
		UpperRowCurser =row -1;
		LowerRowCurser=row +1;
		counter =1;
		
		if(RightColumnCurser >6)
			RightColumnCurser=6;
		if(LeftColumnCurser <0)
			LeftColumnCurser=0;
		
		if(LowerRowCurser >5)
			LowerRowCurser=5;
		if(UpperRowCurser <0)
			UpperRowCurser=0;
		
		if(UpperRowCurser>=0 && RightColumnCurser<=6)
		while(VirtualBoard[UpperRowCurser][RightColumnCurser] !=1 && UpperRowCurser>0 &&  RightColumnCurser<6){
			UpperRowCurser--;
			RightColumnCurser++;
			counter++;
		}
		
		if(LeftColumnCurser>=0 && LowerRowCurser<=5)
		while(VirtualBoard[LowerRowCurser][LeftColumnCurser] !=1 && LowerRowCurser<5 && LeftColumnCurser>0){
			LowerRowCurser++;
			LeftColumnCurser--;
			counter++;
		}
		if(counter==4)
			possible4Conneteds++;
		else if(counter==3)
			possible3Conneteds++;
		else if(counter==2)
			possible2Conneteds++;
		
		
		//From bottom right to top left
		RightColumnCurser=column+1;
		LeftColumnCurser=column-1;
		UpperRowCurser =row -1;
		LowerRowCurser=row +1;
		counter=1;
		
		if(RightColumnCurser >6)
			RightColumnCurser=6;
		if(LeftColumnCurser <0)
			LeftColumnCurser=0;
		
		if(LowerRowCurser >5)
			LowerRowCurser=5;
		if(UpperRowCurser <0)
			UpperRowCurser=0;
		
		
		if(LowerRowCurser<=5 && RightColumnCurser<=6)
		while(VirtualBoard[LowerRowCurser][RightColumnCurser] !=1 && LowerRowCurser<5 &&  RightColumnCurser<6){
			LowerRowCurser++;
			RightColumnCurser++;
			counter++;
		}
		
		if(LeftColumnCurser>=0 && UpperRowCurser>=0)
		while(VirtualBoard[UpperRowCurser][LeftColumnCurser] !=1 && UpperRowCurser>0 && LeftColumnCurser>0){
			
			UpperRowCurser--;
			LeftColumnCurser--;
			counter++;
		}
		if(counter==4)
			possible4Conneteds++;
		else if(counter==3)
			possible3Conneteds++;
		else if(counter==2)
			possible2Conneteds++;
		
		
	}
	
	//this method determines the number of almost n-connects that can be possibly achieved choosing this place
		public void setAlmostConneteds(int[][] VirtualBoard) {
			
			almost4Connecteds =  0;
			almost3Connecteds =0;
			
			int UpperRowCurser = row;
			int RightColumnCurser=column+1;
			int LowerRowCurser = row;
			int LeftColumnCurser=column-1;
			int counter =1;
			
			if(RightColumnCurser >6)
				RightColumnCurser=6;
			if(LeftColumnCurser <0)
				LeftColumnCurser=0;
			
			
			//For Horizontal 4-connect
			if(RightColumnCurser<=6)
			while(VirtualBoard[UpperRowCurser][RightColumnCurser] ==-1 && RightColumnCurser<6 ){
				RightColumnCurser++;
				counter++;
			}
			
			if(LeftColumnCurser>=0){
			while(VirtualBoard[LowerRowCurser][LeftColumnCurser] ==-1 && LeftColumnCurser>0 ){
				LeftColumnCurser--;
				counter++;
			}
			if(counter==4)
				almost4Connecteds++;
			else if(counter==3)
				almost3Connecteds++;
			}
			
			//For Vertical 4-connect
			RightColumnCurser=column;
			LeftColumnCurser=column;
			UpperRowCurser =row -1;
			LowerRowCurser=row +1;
			counter =1;
			
			if(LowerRowCurser >5)
				LowerRowCurser=5;
			if(UpperRowCurser <0)
				UpperRowCurser=0;
			
			if(UpperRowCurser>=0){
			while(VirtualBoard[UpperRowCurser][RightColumnCurser] ==-1 && UpperRowCurser>0 ){
				UpperRowCurser--;
				counter++;
			}
			
			if(LowerRowCurser<=5)
			while(VirtualBoard[LowerRowCurser][LeftColumnCurser] ==-1 && LowerRowCurser<5 ){
				LowerRowCurser++;
				counter++;
			}
			if(counter==4)
				almost4Connecteds++;
			else if(counter==3)
				almost3Connecteds++;
			}
			//From bottom left to top right
			RightColumnCurser=column+1;
			LeftColumnCurser=column-1;
			UpperRowCurser =row -1;
			LowerRowCurser=row +1;
			counter =1;
			
			if(RightColumnCurser >6)
				RightColumnCurser=6;
			if(LeftColumnCurser <0)
				LeftColumnCurser=0;
			
			if(LowerRowCurser >5)
				LowerRowCurser=5;
			if(UpperRowCurser <0)
				UpperRowCurser=0;
			
			if(UpperRowCurser>=0 && RightColumnCurser<=6)
			while(VirtualBoard[UpperRowCurser][RightColumnCurser] ==-1 && UpperRowCurser>0 &&  RightColumnCurser<6){
				UpperRowCurser--;
				RightColumnCurser++;
				counter++;
			}
			
			if(LeftColumnCurser>=0 && LowerRowCurser<=5)
			while(VirtualBoard[LowerRowCurser][LeftColumnCurser] ==-1 && LowerRowCurser<5 && LeftColumnCurser>0){
				LowerRowCurser++;
				LeftColumnCurser--;
				counter++;
			}
			if(counter==4)
				almost4Connecteds++;
			else if(counter==3)
				almost3Connecteds++;
			
			
			//From bottom right to top left
			RightColumnCurser=column+1;
			LeftColumnCurser=column-1;
			UpperRowCurser =row -1;
			LowerRowCurser=row +1;
			counter =1;
			
			if(RightColumnCurser >6)
				RightColumnCurser=6;
			if(LeftColumnCurser <0)
				LeftColumnCurser=0;
			
			if(LowerRowCurser >5)
				LowerRowCurser=5;
			if(UpperRowCurser <0)
				UpperRowCurser=0;
			
			
			if(LowerRowCurser<=5 && RightColumnCurser<=6)
			while(VirtualBoard[LowerRowCurser][RightColumnCurser] ==-1 && LowerRowCurser<5 &&  RightColumnCurser<6){
				LowerRowCurser++;
				RightColumnCurser++;
				counter++;
			}
			
			if(LeftColumnCurser>=0 && UpperRowCurser>=0){
			while(VirtualBoard[UpperRowCurser][LeftColumnCurser] ==-1 && UpperRowCurser>0 && LeftColumnCurser>0){
				
				UpperRowCurser--;
				LeftColumnCurser--;
				counter++;
			}
			if(counter==4)
				almost4Connecteds++;
			else if(counter==3)
				almost3Connecteds++;
			}
			
		}
		
	
	private int row;
	private int column;
	private int possible4Conneteds;
	private int possible3Conneteds;
	private int possible2Conneteds;
	
	private int almost4Connecteds;
	private int almost3Connecteds;
	
	public int getAlmost4Connecteds() {
		return almost4Connecteds;
	}
	public int getAlmost3Connceteds() {
		return almost3Connecteds;
	}
}
