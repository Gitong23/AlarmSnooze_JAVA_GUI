package EV6;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JLabel lb_display;
	//private boolean isDisplay;
	private Calculator calculator = new Calculator();
	
	private boolean isDot = false;
	private boolean isNeg = false;
	
	private String lastPress = "";
	
	private JLabel lb_result;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Main() {
		setTitle("EV6 Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 701);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_C = new JButton("C");
		btn_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isDot = false;
				isNeg = false;
				
				calculator.result = 0;
				calculator.lastOper = null;
				lastPress = "";
				
				lb_display.setText("0");
			}
		});
		
		btn_C.setBackground(new Color(255, 128, 0));
		btn_C.setForeground(new Color(255, 255, 255));
		btn_C.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_C.setBounds(10, 178, 70, 70);
		contentPane.add(btn_C);
		
		JButton btn_MC = new JButton("MC");
		btn_MC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.memory=0;
				calculator.hasMemory = false;
			}
		});
		btn_MC.setForeground(Color.WHITE);
		btn_MC.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_MC.setBackground(new Color(255, 128, 0));
		btn_MC.setBounds(90, 178, 70, 70);
		contentPane.add(btn_MC);
		
		JButton btn_MR = new JButton("MR");
		btn_MR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!calculator.hasMemory)return;
				
				if(calculator.memory == (int)(calculator.memory)) {
					lb_display.setText((int)(calculator.memory)+"");
				}
				else {
					lb_display.setText(calculator.memory+"");
				}
				
				lastPress = "n";
			}
		});
		btn_MR.setForeground(Color.WHITE);
		btn_MR.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_MR.setBackground(new Color(255, 128, 0));
		btn_MR.setBounds(170, 178, 70, 70);
		contentPane.add(btn_MR);
		
		JButton btn_Mplus = new JButton("M+");
		btn_Mplus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double display = Double.parseDouble(lb_display.getText());
				
				if(display == 0)return;
				
				
				if(calculator.hasMemory) {
					calculator.memoAdd(display);
				}
				else {
					calculator.memory = display;
					calculator.hasMemory = true;
				}
				
				
				lastPress = "M+";
				isDot=false;
				isNeg=false;
			}
		});
		btn_Mplus.setForeground(Color.WHITE);
		btn_Mplus.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_Mplus.setBackground(new Color(255, 128, 0));
		btn_Mplus.setBounds(250, 178, 70, 70);
		contentPane.add(btn_Mplus);
		
		JButton btn_del = new JButton("DEL");
		btn_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!lastPress.equals("n"))return;
				
				String s = lb_display.getText();
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					return;
				}
				
				else if(s.length() == 2 && isNeg) {
					lb_display.setText("0");
					isNeg = false;
				}
				
				else if(s.charAt(s.length()-1)== '.') {
					//lb_display.setText("0");
					lb_display.setText(s.substring(0,s.length()-1));
					isDot = false;
				}
				
				else if(s.length() == 1) {
					lb_display.setText("0");
				}
				
				else {	
					lb_display.setText(s.substring(0,s.length()-1));
				}
			}
		});
		btn_del.setForeground(Color.WHITE);
		btn_del.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_del.setBackground(new Color(255, 128, 0));
		btn_del.setBounds(10, 258, 70, 70);
		contentPane.add(btn_del);
		
		JButton btn_plusMinuse = new JButton("+/-");
		btn_plusMinuse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!lastPress.equals("n"))return;
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					return;
				}
				else {	
					if(!isNeg) {
						lb_display.setText("-"+lb_display.getText());
						isNeg = true;
					}
					else {
						lb_display.setText(lb_display.getText().substring(1));
						isNeg = false;
					}
				}
			}
		});
		btn_plusMinuse.setForeground(Color.WHITE);
		btn_plusMinuse.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_plusMinuse.setBackground(new Color(255, 128, 0));
		btn_plusMinuse.setBounds(90, 258, 70, 70);
		contentPane.add(btn_plusMinuse);
		
		JButton btn_percent = new JButton("%");
		btn_percent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double percent = calculator.getPercent(Double.parseDouble(lb_display.getText()));

				if(percent == (int)percent ) {
					lb_display.setText((int)percent+"");
				}
				else {
					lb_display.setText(percent+"");
				}
			}
		});
		btn_percent.setForeground(Color.WHITE);
		btn_percent.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_percent.setBackground(new Color(255, 128, 0));
		btn_percent.setBounds(170, 258, 70, 70);
		contentPane.add(btn_percent);
		
		JButton btn_divide = new JButton("/");
		btn_divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(lastPress.equals("/"))return;
				
				if(calculator.lastOper == null) {
					calculator.add(Double.parseDouble(lb_display.getText()));

				}
				else if(lastPress.equals("=") || lastPress.equals("M+")) {
					
				}
				else {
					if(calculator.lastOper.equals("+"))calculator.add(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("-"))calculator.sub(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("X"))calculator.mul(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("/"))calculator.div(Double.parseDouble(lb_display.getText()));
				}
						
				//to format
				if(calculator.result == (int)calculator.result ) {
					lb_display.setText((int)calculator.result+"");
				}
				else {
					lb_display.setText(calculator.result+"");
				}
				
				lastPress="/";
				calculator.lastOper="/";
				isDot = false;
				isNeg = false;
			}
		});
		btn_divide.setForeground(Color.WHITE);
		btn_divide.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_divide.setBackground(new Color(255, 128, 0));
		btn_divide.setBounds(250, 258, 70, 70);
		contentPane.add(btn_divide);
		
		JButton btn_7 = new JButton("7");
		btn_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( (lastPress.equals(""))  || (!lastPress.equals("n")) ){
					lb_display.setText("7");
					lastPress = "n";
					return;
				}
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					lb_display.setText("7");
					lastPress = "n";
				}
				else {	
					lb_display.setText(lb_display.getText()+"7");
				}
				
			}
		});
		btn_7.setForeground(Color.WHITE);
		btn_7.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_7.setBackground(new Color(128, 128, 128));
		btn_7.setBounds(10, 338, 70, 70);
		contentPane.add(btn_7);
		
		JButton btn_8 = new JButton("8");
		btn_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if((lastPress.equals(""))  || (!lastPress.equals("n"))) {
					lb_display.setText("8");
					lastPress = "n";
					return;
				}
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					lb_display.setText("8");
					lastPress = "n";
				}
				else {	
					lb_display.setText(lb_display.getText()+"8");
				}
			}
		});
		btn_8.setForeground(Color.WHITE);
		btn_8.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_8.setBackground(new Color(128, 128, 128));
		btn_8.setBounds(90, 338, 70, 70);
		contentPane.add(btn_8);
		
		JButton btn_9 = new JButton("9");
		btn_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if((lastPress.equals(""))  || (!lastPress.equals("n"))) {
					lb_display.setText("9");
					lastPress = "n";
					return;
				}
				
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					lb_display.setText("9");
					lastPress = "n";
				}
				else {	
					lb_display.setText(lb_display.getText()+"9");
				}
			}
		});
		btn_9.setForeground(Color.WHITE);
		btn_9.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_9.setBackground(new Color(128, 128, 128));
		btn_9.setBounds(170, 338, 70, 70);
		contentPane.add(btn_9);
		
		JButton btn_mul = new JButton("X");
		btn_mul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(lastPress.equals("X"))return;
				
				if(calculator.lastOper == null) {
					calculator.result = 1;
					calculator.mul(Double.parseDouble(lb_display.getText()));

				}
				else if(lastPress.equals("=") || lastPress.equals("M+")) {
					
				}
				else {
					if(calculator.lastOper.equals("+"))calculator.add(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("-"))calculator.sub(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("X"))calculator.mul(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("/"))calculator.div(Double.parseDouble(lb_display.getText()));
				}
						
				//to format
				if(calculator.result == (int)calculator.result ) {
					lb_display.setText((int)calculator.result+"");
				}
				else {
					lb_display.setText(calculator.result+"");
				}
				
				lastPress="X";
				calculator.lastOper="X";
				isDot = false;
				isNeg = false;
			}
		});
		btn_mul.setForeground(Color.WHITE);
		btn_mul.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_mul.setBackground(new Color(255, 128, 0));
		btn_mul.setBounds(250, 338, 70, 70);
		contentPane.add(btn_mul);
		
		JButton btn_4 = new JButton("4");
		btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if((lastPress.equals(""))  || (!lastPress.equals("n"))) {
					lb_display.setText("4");
					lastPress = "n";
					return;
				}
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					lb_display.setText("4");
					lastPress = "n";
				}
				else {	
					lb_display.setText(lb_display.getText()+"4");
				}
			}
		});
		btn_4.setForeground(Color.WHITE);
		btn_4.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_4.setBackground(Color.GRAY);
		btn_4.setBounds(10, 418, 70, 70);
		contentPane.add(btn_4);
		
		JButton btn_5 = new JButton("5");
		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if((lastPress.equals(""))  || (!lastPress.equals("n"))) {
					lb_display.setText("5");
					lastPress = "n";
					return;
				}
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					lb_display.setText("5");
					lastPress = "n";
				}
				else {	
					lb_display.setText(lb_display.getText()+"5");
				}
			}
		});
		btn_5.setForeground(Color.WHITE);
		btn_5.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_5.setBackground(Color.GRAY);
		btn_5.setBounds(90, 418, 70, 70);
		contentPane.add(btn_5);
		
		JButton btn_6 = new JButton("6");
		btn_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if((lastPress.equals(""))  || (!lastPress.equals("n"))) {
					lb_display.setText("6");
					lastPress = "n";
					return;
				}
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					lb_display.setText("6");
					lastPress = "n";
				}
				else {	
					lb_display.setText(lb_display.getText()+"6");
				}
			}
		});
		btn_6.setForeground(Color.WHITE);
		btn_6.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_6.setBackground(Color.GRAY);
		btn_6.setBounds(170, 418, 70, 70);
		contentPane.add(btn_6);
		
		JButton btn_sub = new JButton("-");
		btn_sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(lastPress.equals("-"))return;
				
				if(calculator.lastOper == null) {
					calculator.add(Double.parseDouble(lb_display.getText()));
				}
				else if(lastPress.equals("=") || lastPress.equals("M+")) {
					
				}
				else {
					if(calculator.lastOper.equals("+"))calculator.add(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("-"))calculator.sub(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("X"))calculator.mul(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("/"))calculator.div(Double.parseDouble(lb_display.getText()));
				}
						
				//to format
				if(calculator.result == (int)calculator.result ) {
					lb_display.setText((int)calculator.result+"");
				}
				else {
					lb_display.setText(calculator.result+"");
				}
				
				lastPress="-";
				calculator.lastOper="-";
				isDot = false;
				isNeg = false;
			}
		});
		btn_sub.setForeground(Color.WHITE);
		btn_sub.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn_sub.setBackground(new Color(255, 128, 0));
		btn_sub.setBounds(250, 418, 70, 70);
		contentPane.add(btn_sub);
		
		JButton btn_1 = new JButton("1");
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if((lastPress.equals(""))  || (!lastPress.equals("n"))) {
					lb_display.setText("1");
					lastPress = "n";
					return;
				}
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					lb_display.setText("1");
					lastPress = "n";
				}
				else {	
					lb_display.setText(lb_display.getText()+"1");
				}
			}
		});
		btn_1.setForeground(Color.WHITE);
		btn_1.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_1.setBackground(Color.GRAY);
		btn_1.setBounds(10, 498, 70, 70);
		contentPane.add(btn_1);
		
		JButton btn_2 = new JButton("2");
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if((lastPress.equals(""))  || (!lastPress.equals("n"))) {
					lb_display.setText("2");
					lastPress = "n";
					return;
				}
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					lb_display.setText("2");
					lastPress = "n";
				}
				else {	
					lb_display.setText(lb_display.getText()+"2" );
				}
			}
		});
		btn_2.setForeground(Color.WHITE);
		btn_2.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_2.setBackground(Color.GRAY);
		btn_2.setBounds(90, 498, 70, 70);
		contentPane.add(btn_2);
		
		JButton btn_3 = new JButton("3");
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if((lastPress.equals(""))  || (!lastPress.equals("n"))) {
					lb_display.setText("3");
					lastPress = "n";
					return;
				}
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					lb_display.setText("3");
					lastPress = "n";
				}
				else {	
					lb_display.setText(lb_display.getText()+"3");
				}
			}
		});
		btn_3.setForeground(Color.WHITE);
		btn_3.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_3.setBackground(Color.GRAY);
		btn_3.setBounds(170, 498, 70, 70);
		contentPane.add(btn_3);
		
		JButton btn_plus = new JButton("+");
		btn_plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(lastPress.equals("+"))return;
		
				if(calculator.lastOper == null) {
					calculator.add(Double.parseDouble(lb_display.getText()));
				}
				else if(lastPress.equals("=") || lastPress.equals("M+")) {
					
				}
				else {
					if(calculator.lastOper.equals("+"))calculator.add(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("-"))calculator.sub(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("X"))calculator.mul(Double.parseDouble(lb_display.getText()));
					if(calculator.lastOper.equals("/"))calculator.div(Double.parseDouble(lb_display.getText()));
				}
						
				//to format
				if(calculator.result == (int)calculator.result ) {
					lb_display.setText((int)calculator.result+"");
				}
				else {
					lb_display.setText(calculator.result+"");
				}
				
				lastPress="+";
				calculator.lastOper="+";
				isDot = false;
				isNeg = false;
			}
		});
		btn_plus.setForeground(Color.WHITE);
		btn_plus.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn_plus.setBackground(new Color(255, 128, 0));
		btn_plus.setBounds(250, 498, 70, 70);
		contentPane.add(btn_plus);
		
		JButton btn_0 = new JButton("0");
		btn_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if((lastPress.equals(""))  || (!lastPress.equals("n"))) {
					lb_display.setText("0");
					lastPress = "n";
					return;
				}
				
				if(lb_display.getText().charAt(0) == '0' && lb_display.getText().length()==1) {
					lb_display.setText("0");
					lastPress = "n";
				}
				else {	
					lb_display.setText(lb_display.getText()+"0");
				}
			}
		});
		btn_0.setForeground(Color.WHITE);
		btn_0.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_0.setBackground(Color.GRAY);
		btn_0.setBounds(10, 578, 150, 70);
		contentPane.add(btn_0);
		
		JButton btn_dot = new JButton(".");
		btn_dot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isDot) {
					
					if(lastPress.equals("") || lastPress.equals("n")) {
						lb_display.setText(lb_display.getText()+".");
						lastPress = "n";
						isDot = true;
					}
					
					if(lastPress.equals("=") || lastPress.equals("+") || lastPress.equals("-") || lastPress.equals("X") || lastPress.equals("/")) {
						lb_display.setText("0.");
						lastPress = "n";
						isDot = true;
					}
				}		
			}
		});
		btn_dot.setForeground(Color.WHITE);
		btn_dot.setFont(new Font("Tahoma", Font.BOLD, 35));
		btn_dot.setBackground(Color.GRAY);
		btn_dot.setBounds(170, 578, 70, 70);
		contentPane.add(btn_dot);
		
		JButton btn_equal = new JButton("=");
		btn_equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(lastPress.equals("="))return;
				
				if(calculator.lastOper.equals("+")) {
					if(lastPress.equals("n"))calculator.add(Double.parseDouble(lb_display.getText()));
				
				}
				
				if(calculator.lastOper.equals("-")) {
					if(lastPress.equals("n"))calculator.sub(Double.parseDouble(lb_display.getText()));
					
				}
				
				if(calculator.lastOper.equals("X")) {
					if(lastPress.equals("n"))calculator.mul(Double.parseDouble(lb_display.getText()));
					
				}
				
				if(calculator.lastOper.equals("/")) {
					if(lastPress.equals("n"))calculator.div(Double.parseDouble(lb_display.getText()));
					
				}
				
				//to format
				if(calculator.result == (int)calculator.result ) {
					lb_display.setText((int)calculator.result+"");
				}
				else {
					lb_display.setText(calculator.result+"");
				}
				
				//calculator.lastOper = null;
				lastPress = "=";
				isDot=false;
				isNeg=false;
			}
		});
		btn_equal.setForeground(Color.WHITE);
		btn_equal.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn_equal.setBackground(new Color(255, 128, 0));
		btn_equal.setBounds(250, 578, 70, 70);
		contentPane.add(btn_equal);
		
		lb_display = new JLabel("0");
		lb_display.setFont(new Font("Tahoma", Font.BOLD, 40));
		lb_display.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_display.setVerticalAlignment(SwingConstants.BOTTOM);
		lb_display.setForeground(new Color(255, 255, 255));
		lb_display.setBounds(10, 58, 310, 82);
		contentPane.add(lb_display);
		
		lb_result = new JLabel("0");
		lb_result.setVerticalAlignment(SwingConstants.BOTTOM);
		lb_result.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_result.setForeground(Color.WHITE);
		lb_result.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_result.setBounds(257, 10, 63, 38);
		contentPane.add(lb_result);
		
		JLabel lb_memo = new JLabel("0");
		lb_memo.setVerticalAlignment(SwingConstants.BOTTOM);
		lb_memo.setHorizontalAlignment(SwingConstants.LEFT);
		lb_memo.setForeground(Color.WHITE);
		lb_memo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lb_memo.setBounds(189, 10, 50, 38);
		contentPane.add(lb_memo);
		
		JLabel lb_M = new JLabel("M");
		lb_M.setVerticalAlignment(SwingConstants.BOTTOM);
		lb_M.setHorizontalAlignment(SwingConstants.LEFT);
		lb_M.setForeground(Color.WHITE);
		lb_M.setFont(new Font("Tahoma", Font.BOLD, 25));
		lb_M.setBounds(30, 12, 50, 38);
		contentPane.add(lb_M);
		
		lb_result.setVisible(false);
		lb_memo.setVisible(false);
		lb_M.setVisible(false);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					lb_result.setText("Result: "+calculator.result);
					lb_memo.setText("Memo: "+calculator.memory);
					
					if(calculator.hasMemory) {
						lb_M.setVisible(true);
					}
					else {
						lb_M.setVisible(false);
					}
				}
			}
		});
		
		t.start();
	}
}
