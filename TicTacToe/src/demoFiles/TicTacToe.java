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


	public static int findsInArray(int numa, int numb, int[] a) {
		int select = 0;
		int result = 0;
		System.out.println("numero a " + numa + "// numero b " + numb);
		
		//System.out.println("ARRAY ---> " +  Arrays.toString(a));
		
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
		//System.out.println("Given button:  " + b.getName());
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
		boolean result = true;
		int riv = 0;
		int machine = 0;
		for (int b = 0; b < 3; b++) {
			if (Buttons[ar[b]].getText() == rival) {
				riv ++ ;
			}
			else if (Buttons[ar[b]].getText() == choice) {
				machine ++ ;
			}
		}
		
		if (riv + machine == 3) {
			result = false;
			System.out.println("EXCLUDE ARRAY " + Arrays.toString(ar));
		}
		return result;
		
	}
	
	
	
	public static int secondaryMoves(int mova, int movb, int[][] raws){
		int select = 0;
		//WORK HERE TO AVOID ANALIZING SAME ARRAY THAT HAS ALREADY BEEN ANALIZED
		//UPDATED: NOT SURE IF IT WORKED. KEEP TRYING...
		
		System.out.println("Given digits a and b :" + mova + " and " + movb);
		for (int y = 0; y < raws.length; y ++) {
			if (excludesArray ( raws[y]) == true ) {
				select = findsInArray(mova, movb, raws[y]);
				if (select != 0) {
					//System.out.println("SELECT BOX " + select);
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
	
	
	public static int fitsInArray(Vector<Integer> clicked, int[][] rowx) {
		
		
		//REVISAR TODA ESTA MOVIDA
		int toSelect = 0;
		
		for (int l = 0; l < rowx.length; l ++) {
		
		for (int cl = 0; cl < clicked.size(); cl++) {
			for (int mov = clicked.indexOf(cl) +1  ; mov < clicked.size(); mov ++) {
				if (findsInArray(cl, mov, rowx[l]) != 0) {
						toSelect = findsInArray(cl, mov, rowx[l]);
						break;
				}
			}
			if (toSelect != 0) {
				break;
			}
		}
		
		}
		return toSelect;
	}
	
	
	
	
	public static void machine2(int[][] r) {
		Vector <Integer> v =  getTheDigits();
		System.out.println("BOTONES CLICKEADOS... " + v);
		int second = secondaryMoves(v.get(v.size() - 2), v.get(v.size()-1), r);//EL PROBLEMA ESTA ACA. SIEMPE PASO 1RO Y 2DO CLICKEADOS
		//int second = fitsInArray(v, r); //TOQUE ACA...
		
		Buttons[second].painter(rival);
		if (Pos.contains(second) == false) {
			Pos.add(second);
		}
	}
	
	
	public static Vector<Integer> getTheDigits() {
		//Returns a vector filled with integers representing the buttons clicked by user
		if (Pos.size() >= 3) { 
			
			for (int i = 0; i < Pos.size() ; i++) { // SE ARMO QUILOMBO ACA...
				if (Buttons[Pos.get(i)].getText() == choice && Clicked.contains(Pos.get(i)) == false) {
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
					
					//SEEMS LIKE MAIN POBLEM COULD BE AROUND HERE
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





