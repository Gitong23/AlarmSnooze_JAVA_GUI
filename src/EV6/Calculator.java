package EV6;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Calculator {
	public double result;
	public double memory;
	public String lastOper;
	//public boolean hasResult;
	public boolean hasMemory;
	
	public void add (double n) {
		result +=n;
	}
	
	public void sub (double n) {
		result -= n;
	}
	
	public void div (double n) {
		if(n==0) {
			JFrame f = new JFrame();
			JOptionPane.showMessageDialog(f, "Cannot divide by zero");
			return;
		}
		result /= n;
	}
	
	public void mul (double n) {
		result *= n;
	}
	
	public double getPercent (double n) {
		return (n/100)*result;
	}
	
	public void memoAdd(double n) {
		memory += n;
	}
}
