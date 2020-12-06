package demoFiles;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class TicTacToe {

public static String choice;
public static String rival;
public static Button [] Buttons;


public static int[] horSup = new int[]{0,1,2};
public static int[] horMid = new int[]{3,4,5};
public static int[] horInf = new int[]{6,7,8};


public static int[] verLeft = new int[]{0,3,6};
public static int[] verMid = new int[]{1,4,7};
public static int[] verRight = new int[]{2,5,8};


public static int[] diagUpDown = new int[]{0,4,8};
public static int[] diagDownUp = new int[]{6,4,2};


public static int[][] combinations = {horSup, horMid, horInf, verLeft, verMid, verRight, diagUpDown, diagDownUp};


public static Vector<Button> F = new Vector<Button>();
public static Vector<Integer> Pos = new Vector<Integer>();
public static Vector<Integer> Clicked = new Vector<Integer>();


public static void main(String[] args) {

	
	Frame Gframe = new Frame();
	Panel Gpanel = new Panel();
	Gframe.add(Gpanel);
	
	ChoiceButton x = new ChoiceButton("X",200 , 35);
	JButton y = new ChoiceButton("O", 350, 35);

	
	Gpanel.add(x);
	Gpanel.add(y);


	ActionListener Listen = new ActionListener() {
		public void actionPerformed(ActionEvent arg) {
			if(arg.getSource()==x){
				choice = "X";
				rival = "O";
			}

			else if(arg.getSource() == y){
				 choice = "O";
				 rival = "X";
			}
			

			Button LeftSup = new Button(5,95,195, 195,choice);
			LeftSup.setName("LeftSup");
			Gpanel.add(LeftSup);
			
			
			Button MidSup = new Button(205,95,195, 195,choice);
			MidSup.setName("MidSup");
			Gpanel.add(MidSup);


			Button RigSup = new Button(405,95,195, 195, choice);
			RigSup.setName("RigSup");
			Gpanel.add(RigSup);

			
			Button LeftMid = new Button(5,295,195, 195, choice);
			LeftMid.setName("LeftMid");
			Gpanel.add(LeftMid);


			Button MidMid = new Button(205,295,195, 195, choice);
			MidMid.setName("MidMid");
			Gpanel.add(MidMid);


			Button RigMid = new Button(405,295,195, 195,choice);
			RigMid.setName("RigMid");
			Gpanel.add(RigMid);


			Button LeftInf = new Button(5,495,195, 195,choice);
			LeftInf.setName("LeftInf");
			Gpanel.add(LeftInf);

			
			Button MidInf = new Button(205,495,195, 195,choice);
			MidInf.setName("MidInf");
			Gpanel.add(MidInf);


			Button RigInf = new Button(405,495,195, 195,choice);
			RigInf.setName("RigInf");
			Gpanel.add(RigInf);

	
			Gpanel.repaint();

		
			Buttons = new Button[9];

			Buttons[0] = LeftSup;
			Buttons[1] = MidSup;
			Buttons[2] = RigSup;
			Buttons[3] = LeftMid;
			Buttons[4] = MidMid;
			Buttons[5] = RigMid;
			Buttons[6] = LeftInf;
			Buttons[7] = MidInf;
			Buttons[8] = RigInf;
		}
	};

	
	x.addActionListener(Listen);
	y.addActionListener(Listen);
}


	public static int completesArray(int numa, int numb, int[] a) {
		int select = 0;
		int result = 0;
		System.out.println("numero a " + numa + "// numero b " + numb);
		for (int i = 0; i < 3; i++) {
			if (a[i] == numa || a[i] == numb) {
				result ++;
				}
			if(result == 2) {
				System.out.println("FOUND IT ...");
			}
		}

		if (result == 2) {
			for (int h = 0; h < 3; h++) {
				if ((a[h] != numa) && (a[h] != numb)) {
					select = a[h];
				}
			}
		}
		System.out.println("SELECT WILL BE " + select);
		return select;
	}

	
	
	public static int returnsPosition(Button b) {
		int position = 0;
		for (int n = 0; n < 9 ; n++) {
			Buttons[n].repaint();
			if (Buttons[n] == b) {
				position = n;
				if (Pos.contains(position) == false) {
					Pos.add(position);
				}

				for( int larg = 0; larg < Pos.size() ; larg++  ) {
					System.out.println("Botones ocupados -> " + Pos.elementAt(larg)+ " /Status: " + Buttons[Pos.elementAt(larg)].getText());
				}
				break;
			}
		}
		return position;
	}

	
	public static boolean excludesArray(int[] ar) {
		/*Receives an integer array and checks if its 3 positions are occupied or not.
		if they are the method returns false. If not it returns true, and that means
		that there is a line on the board that is about to be completed*/
		boolean result = true;
		int machine = 0;
		int player = 0;
		
		for (int b = 0; b < 3; b++) {
			if (Buttons[ar[b]].getText() == rival) {
				machine ++ ;
			}
			else if (Buttons[ar[b]].getText() == choice) {
				player ++ ;
			}
		}

		if (machine + player == 3) {
			result = false;
			System.out.println("EXCLUDE ARRAY " + Arrays.toString(ar));
		}

		return result;
	}
	
	
	public static int [] returnsExcludedArray(int[][]arr) {
		/*Searches through an array of arrays and returns the one that
		should be exclude of analysis after (the one that is fully occupied)*/
		int[] exc = null;
		for (int a = 0; a < arr.length; a ++) {
			if (excludesArray(arr[a]) == false) {
				exc = arr[a];
				break;
			}
		}
		return exc;
	}
	
	
	public static int findsMachineBox(int[]raw){
		/*Receives an array with integers representing positions on the board.
		It finds and returns the position of the button 'clicked' by the machine*/
		int box = 0;
		for(int pos = 0; pos < raw.length; pos++) {
			System.out.println("CRUSHES WITH? " + Arrays.toString(raw));
			if (Buttons[raw[pos]].getText() == rival) {
			
				box = raw[pos];
				break;
			}
		}
		return box;
	}
	
	
	public static int alternativeBox(int[][]raws, int[]excluded){
		/*Receives an array of arrays, and an array to exclude of the previous one. It checks one by one
		except for the excluded, searching for a common number between it an another one. Then in this
		other array that will contain this common number, chooses a free box next to it and returns it*/
		int common = findsMachineBox(excluded); //this is the number i need to find in a different array
		int toMark = 20;//?
		
		
		for (int r = 0; r < raws.length; r++) {
			if (raws[r] != excluded) {
				if (contains(common, raws[r]) == true) {
					for (int number = 0; number < 3; number++) {
						if (raws[r][number] != common) {
							if(Buttons[raws[r][number]].getText().length() == 0) {
								toMark = raws[r][number];
								break;
							}
						}
					}
					if(toMark != 20) {
						break;
					}
				}
			}
		}
		return toMark;
	}
	

	public static int secondaryMoves(Vector<Integer> pressed, int[][] raws){
		int select = 0; //Esto me esta trayendo muchos quilombos (12/10);
		for (int y = 0; y < raws.length; y ++) {
			if (excludesArray (raws[y]) == true ) {
				select = findsArrayForPositions(getTheDigits(rival), raws[y]);
				//select = findsArrayForPositions(pressed, raws[y]);
				if (select == 0 && Pos.size() >= 3) { //SEEMS LIKE IT WORKED...
					/*select = findsArrayForPositions(getTheDigits(rival), raws[y]);*/
					select = findsArrayForPositions(pressed, raws[y]);
				}
				if (select != 0) {
					break;
				}
			}
		}

		return select;
	}

	
	public static int randomSteps() {
		int square = 0;
		Random r = new Random();
		int randomized = r.nextInt(3);

		if (randomized == 0) {
			square = 0;
		}
		else if (randomized == 1){
			square = 2;
		}
		else if (randomized == 2){
			square = 6;
		}
		else if (randomized == 3){
			square = 8;
		}
		return square;
	}

	
	public static int initialMoves(int move){
		int boxToSelect = 0;

		if (move == 4) {
			boxToSelect = randomSteps();
		}

		if (move != 4){
			boxToSelect = 4;
		}

		return boxToSelect;
	}

	
	public static void machine(int player) {
		int firstMove = initialMoves(player);
		Buttons[firstMove].painter(rival);

		if (Pos.contains(firstMove) == false) {
			Pos.add(firstMove);
		}
	}

	
	public static boolean contains(int value, int[] container) {
		boolean answer = false;
		for (int numbers = 0; numbers < container.length; numbers ++) {
			if(container[numbers] == value) {
				answer = true;
				break;
			}
		}

		return answer;
	}

	

	public static int findsArrayForPositions(Vector<Integer> pressed, int[] lines) {
		/* Receives a vector with clicked buttons (only human player), and an array with
		3 integers representing a row in the board. It iterates through the values of "pressed"
		finding couples that matches at least 2 values inside the "lines" array. Then it returns
		the missing number inside of it to complete it*/

		System.out.println("In this case pressed is: " + pressed +  "   "+ Arrays.toString(lines));
		int complete = 0;

		for (int pr = 0; pr < pressed.size(); pr ++){
			System.out.println("Lines are :" + Arrays.toString(lines));
			for (int p = pr + 1; p < pressed.size(); p ++) {
				if (contains(pressed.get(pr), lines) == true && contains(pressed.get(p), lines) ) {
					complete = completesArray(pressed.get(pr),pressed.get(p),lines);
					break;
				}
			}
			if(complete != 0) {
				break;
			}
		}
		
		//System.out.println("Complete will be: " + complete);
		return complete;
	}



	public static void machine2(int[][] r) {
		Vector <Integer> v =  getTheDigits(choice);
		//System.out.println("BOTONES CLICKEADOS... " + v);
		int second = secondaryMoves(v, r);
		
		
		if (Pos.size() >= 3 && second == 22) {			
			int [] e = returnsExcludedArray(r);
			second = alternativeBox(r, e);
		}
		if (Pos.contains(second) == false) {
			Buttons[second].painter(rival);
			Pos.add(second);
		}
	}

	
	public static boolean validatesGetDigits(Vector <Integer> dig) {
		boolean result = false;
		if (dig.size() >= 2) {
			result = true;
		}
		return result;
	}
	
	
	
	
	public static Vector<Integer> getTheDigits(String player) {
		/*Returns a vector filled with integers representing the buttons clicked by user
		or machine according to what is given in the parameters*/
		if (Pos.size() >= 3) { 
			for (int i = 0; i < Pos.size() ; i++) { // SE ARMO QUILOMBO ACA...
				if (Buttons[Pos.get(i)].getText() == player && Clicked.contains(Pos.get(i)) == false) {
					Clicked.add(Pos.get(i));
				}
			}		
		}
		return Clicked;
	}
}


//FRAME AND PANEL CLASSES


class Frame extends JFrame{
	public Frame() {
		setBounds(325, 100, 620, 735);
		setVisible(true);
	}
}


class Panel extends JPanel{
	public Panel() {
		setBounds(0, 0, 620, 735);
		setBackground(new Color(63,115,102));
		setVisible(true);
		setLayout(null);
	}
}


//BOTONES (INICIALMENTE SOLO RECIBIRAN MEDIDAS)


class Button extends JButton{
	TicTacToe met = new TicTacToe();
	public String draw;
	public Button(int x, int y, int w, int h, String ch){
		setBounds(x, y, w, h);
		setBackground(new Color(77,176,160));
		setVisible(true);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setFont(new Font("Verdana",20,90));
				setText(ch);
				Button.this.repaint();
				System.out.println(Button.this.getName());
				int p = met.returnsPosition(Button.this);
				if (met.Pos.size() == 1) {
					met.machine(p);
				}

				else if (met.Pos.size() >= 3 && met.Pos.size() < 9 ) {
					met.machine2(met.combinations);
				}
			}		
		});	
	}

	
	public void painter(String m) {
		setFont(new Font("Verdana",20,90));
		setText(m);
		repaint();
	}

	
	public String drawed () {
		return this.getText();
	}

}


//BOTONES ELECCION FIGURA


class ChoiceButton extends JButton{
	public ChoiceButton(String b, int x, int y){
		setBounds(x, y, 70, 40);
		setText(b);
		setBackground(new Color(77,176,160));
		setVisible(true);
	}
}