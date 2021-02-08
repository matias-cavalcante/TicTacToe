package demoFiles;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class TicTacToe {

public static String choice; //HUMAN PLAYER 
public static String rival;  //MACHINE PLAYER
public static Button [] Buttons;

public static int Score; 


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
public static Vector<Integer> Pos = new Vector<Integer>(); //ALL taken positions (machine and human)
public static Vector<Integer> Clicked = new Vector<Integer>(); //Human positions taken (clicked)
public static Vector<Integer> Painted = new Vector<Integer>(); //Machine positions taken (painted)


public static void main(String[] args) {

	Dimension D = Toolkit.getDefaultToolkit().getScreenSize();
	Frame Gframe = new Frame();
	Gframe.setLocation(D.width/2-Gframe.getSize().width/2, D.height/2-Gframe.getSize().height/2);
	Gframe.setResizable(false);
	
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
			

			Button LeftSup = new Button(5,95,195, 195,choice, Gframe, Gpanel);
			LeftSup.setName("LeftSup");
			Gpanel.add(LeftSup);
			
			
			Button MidSup = new Button(205,95,195, 195,choice, Gframe, Gpanel);
			MidSup.setName("MidSup");
			Gpanel.add(MidSup);


			Button RigSup = new Button(405,95,195, 195, choice, Gframe, Gpanel);
			RigSup.setName("RigSup");
			Gpanel.add(RigSup);

			
			Button LeftMid = new Button(5,295,195, 195, choice, Gframe, Gpanel);
			LeftMid.setName("LeftMid");
			Gpanel.add(LeftMid);


			Button MidMid = new Button(205,295,195, 195, choice, Gframe, Gpanel);
			MidMid.setName("MidMid");
			Gpanel.add(MidMid);


			Button RigMid = new Button(405,295,195, 195,choice, Gframe, Gpanel);
			RigMid.setName("RigMid");
			Gpanel.add(RigMid);


			Button LeftInf = new Button(5,495,195, 195,choice, Gframe, Gpanel);
			LeftInf.setName("LeftInf");
			Gpanel.add(LeftInf);

			
			Button MidInf = new Button(205,495,195, 195,choice, Gframe, Gpanel);
			MidInf.setName("MidInf");
			Gpanel.add(MidInf);


			Button RigInf = new Button(405,495,195, 195,choice, Gframe, Gpanel);
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
		/*Receives and 2 integers and an array of 3 integers (that represent a row
		 in the board). It finds the array to which the 2 received integers belong.
		 Then finds the missing number to complete the array and returns it*/
	
		int select = 0;
		int result = 0;
		for (int i = 0; i < 3; i++) {
			if (a[i] == numa || a[i] == numb) {
				result ++;
				}
		}
		if (result == 2) {
			for (int h = 0; h < 3; h++) {
				if ((a[h] != numa) && (a[h] != numb)) {
					select = a[h];
				}
			}
		}
		return select;
	}
	
	
	
	public static int returnsPosition(Button b) {
		/*APPARENTLY ! Receives a button (that has been clicked) and 
		 finds to then return, it´s position on the board (0-8)*/
		
		int position = 0;
		for (int n = 0; n < 9 ; n++) {
			Buttons[n].repaint();
			if (Buttons[n] == b) {
				position = n;
				if (Pos.contains(position) == false) {
					Pos.add(position);
				}

				break;
			}
		}
		return position;
	}

	
	public static boolean excludesArray(int[] ar) {
		/*Receives an integers array and checks if its 3 positions are occupied or not.
		If they are the method returns FALSE. If not it returns TRUE, and that means
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
		}

		return result;
	}
	
	
	public static boolean fillMachineRow(int[] r) {
		/*Tries to complete a row in which the MACHINE PLAYER has already
		 taken two positions*/
		
		boolean viable = false;
		int amount = 0;
		for (int i = 0;  i < r.length; i ++) {
			if (Buttons[r[i]].getText() == rival) {
				amount++;
			}
		if (amount == 2){
			viable = true;
			}
		}
		return viable;
	}
	

	public static int secondaryMoves(Vector<Integer> pressed, int[][] raws, JFrame gf){
		/*Takes care of 'playing' for the machine after it has made it´s first move.
		  Receives a vector with buttons (positions) pressed by HUMAN PLAYER, and 
		  a bidimensional array with the lines in the board.*/
		
		int select = 10; 
		
		for (int y = 0; y < raws.length; y ++) {
			if (excludesArray (raws[y]) == true ) {
				if (select == 10 && Pos.size() >= 3) {
					select = findsArrayForPositions(pressed, raws[y]);
					
					if(findsArrayForPositions(getTheDigits(choice), raws[y]) != 10){
						select = findsArrayForPositions(getTheDigits(choice), raws[y]);	
					}
				}
				if (select != 10) {
					break;
				}
			}
		}
	
		if (select == 10) {
			Random ran = new Random();
			int r = ran.nextInt(8);
			while (select == 10 ) {
				if(Buttons[r].getText() != "X" && Buttons[r].getText() != "O") {
					select = r;
				}
			}
		}
				
		return select;
		
	}

	
	public static int initialMoves(int move){
		/*It makes the first move for the MACHINE. It attempts to take the center,
		 which if position 4. If not possible, then it calls the method randomSteps() to take
		 care of choosing another position*/
		
		int boxToSelect = 0;

		if (move == 4) {
			boxToSelect = randomSteps();
		}

		if (move != 4){
			boxToSelect = 4;
		}

		return boxToSelect;
	}
	
	
	public static int randomSteps() {
		/*It chooses a position for the MACHINE. It will try to take the 
		 0 position. If it is not possible, it will choose any other of the
		 corner positions in the board.*/
		
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

	
	public static void machine(int player) {
		/*It takes care of the initial move of the MACHINE.*/
		
		int firstMove = initialMoves(player);
		Buttons[firstMove].painter(rival);

		if (Pos.contains(firstMove) == false) {
			Pos.add(firstMove);
		}
	}
	
	
	public static void machine2(int[][] r, JFrame fram, JPanel gp) {
		/*It takes care of the MACHINE moves after the first one.*/
		
		Vector <Integer> v =  getMachinePositions();
		
		int second = secondaryMoves(v, r, fram);
		
		
		if (Pos.contains(second) == false) {
			Buttons[second].painter(rival);
			Pos.add(second);
			
			gameResultWatcher(combinations, fram, gp);
			ShootSignWhenDraw(fram, Buttons, gp);
			
		}
	}
	

	public static boolean contains(int value, int[] container) {
		/*Receives an int value and an array of integers. It finds if the value
		it´s contained in the array. If so, returns true, if not false.*/
		
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
		/* Receives a vector with clicked buttons (only human player * updated also machine), and an array with
		3 integers representing a row in the board. It iterates through the values of "pressed"
		finding couples that matches at least 2 values inside the "lines" array. Then it returns
		the missing number inside of it to complete it*/

		int complete = 10;

		for (int pr = 0; pr < pressed.size(); pr ++){
			//System.out.println("Lines are :" + Arrays.toString(lines));
			for (int  p = pr + 1; p < pressed.size(); p ++) {
				if (contains(pressed.get(pr), lines) == true && contains(pressed.get(p), lines) ) {
					
					complete = completesArray(pressed.get(pr),pressed.get(p),lines);
					break;
				}
			}
			if(complete < 10) {
				break;
			}
		}
		
		
		return complete;
	}

	
	public static Vector<Integer> getTheDigits(String player) {
		/*Returns a vector filled with integers representing the buttons clicked by user
		or machine according to what is given in the parameters*/
		
		if (Pos.size() >= 3) { 
			for (int i = 0; i < Pos.size() ; i++) { 
				if (  Clicked.contains(Pos.get(i)) == false && Buttons[Pos.get(i)].getText() == player  ) {
					Clicked.add(Pos.get(i));
				}
			}		
		}
		return Clicked;
	}
	
	
	public static Vector<Integer> getMachinePositions() {
		/*Returns a vector filled with integers representing the buttons clicked by user
		or machine according to what is given in the parameters*/
		
		if (Pos.size() >= 2) { 
			for (int i = 0; i < Pos.size() ; i++) {
				if (  Painted.contains(Pos.get(i)) == false && Buttons[Pos.get(i)].getText() == rival  ) {
				
					Painted.add(Pos.get(i));
				}
			}		
		}
		return Painted;
	}
	
	
	public static void gameResultWatcher(int[][] rows, JFrame fr, JPanel pnl) {
		
		JOptionPane result = new JOptionPane();
	
		for (int line = 0; line < 8; line ++) { 
			int machine = 0;
			int player = 0;
		
			
			int [] row = rows[line];
			System.out.println(Arrays.toString(row));

			for (int pos = 0; pos <3; pos++) {
				
				if(Buttons[row[pos]].getText() == rival) {
					machine ++ ;
				}
				else if(Buttons[row[pos]].getText() == choice) {
					player ++ ;
				}
			}
		
		
		
		if (machine == 3 ){
			SignsAndFlowControl(fr, pnl, "MACHINE WINS\nSCORE: " + Score + "\nTry again?");
			

			
		}
		else if (player == 3){
			Score ++;
			SignsAndFlowControl(fr, pnl, "PLAYER WINS\nSCORE: " + Score + "\nTry again?");
			
			
		}
		
		
		}

		
	}
	
	
	public static void ShootSignWhenDraw(JFrame j, Button[] bo, JPanel Gpanel){
		JOptionPane drawResulted = new JOptionPane();
		
		int paintedOnes = 0;
		
		for (int b = 0; b < 9; b ++){
			if(bo[b].getText() == "X" || bo[b].getText() == "O") {
				paintedOnes++;
			}
		}
		
		if (paintedOnes == 9) {
			SignsAndFlowControl(j, Gpanel, "DRAW\nSCORE: " + Score + "\nTry again?");
		
	}
	
	}
	
	
	
	
	public static void SignsAndFlowControl(JFrame jf, JPanel display, String message) {
		
		int result = JOptionPane.showConfirmDialog(display, message, "Tic Tac Toe", 0);
		
		
		if(result == 0 ) {
			for(int b = 0; b < 9; b++) {
				Buttons[b].setText(null);
			}
			
			Pos.clear();
			Clicked.clear();
			Painted.clear();
			
		}
		
		if (result == 1) {
			jf.dispose();
		}
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
	public Button(int x, int y, int w, int h, String ch, JFrame frm, JPanel GPan){
		setBounds(x, y, w, h);
		setBackground(new Color(77,176,160));
		setVisible(true);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setFont(new Font("Verdana",20,90));
				setText(ch);
				Button.this.repaint();
				int p = met.returnsPosition(Button.this);
				if (met.Pos.size() == 1) {
					met.machine(p);
				}

				else if (met.Pos.size() >= 3 && met.Pos.size() < 9 ) {
					met.machine2(met.combinations, frm, GPan);
				}
				
				met.ShootSignWhenDraw(frm, met.Buttons, GPan);
				
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




