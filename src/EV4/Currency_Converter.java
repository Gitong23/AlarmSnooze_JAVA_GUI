package EV4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Currency_Converter extends JFrame {

	private JPanel contentPane;
	private JTextField txt_amount;
	private JTextField txt_result;

	String cur[] = new String[]{
			"THB - Thai Baht",
			"USD - US Dollar",
			"EUR - Euro",
			"GBP - British Pound",
			"JPY - Japanese Yen",
			"LAK - Lao Kip",
			"AUD - AustDollar",
			"SGD - Singapore Dollar",
			"CNY - Chinese Yuan",
			"KRW - Sout Korean Won"};
	
	double rate_per_baht[] = new double[]{
			1,
			0.028831227,
			0.027133122,
			0.023872437,
			3.8529264,
			486.08667,
			0.043399013,
			0.038873143,
			0.19876423,
			37.927712};
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Currency_Converter frame = new Currency_Converter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Currency_Converter() {
		setTitle("EV4 Currency Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt_amount = new JTextField();
		txt_amount.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_amount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_amount.setBounds(107, 44, 203, 25);
		contentPane.add(txt_amount);
		txt_amount.setColumns(10);
		
		JLabel lb_amount = new JLabel("AMOUNT");
		lb_amount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_amount.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_amount.setBounds(157, 23, 153, 25);
		contentPane.add(lb_amount);
		
		JLabel lb_from = new JLabel("FROM");
		lb_from.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_from.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_from.setBounds(157, 79, 153, 25);
		contentPane.add(lb_from);
		
		JComboBox comboBox_from = new JComboBox(cur);
		comboBox_from.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_from.setBounds(107, 107, 203, 25);
		contentPane.add(comboBox_from);
		
		JComboBox comboBox_to = new JComboBox(cur);
		comboBox_to.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_to.setBounds(107, 165, 203, 25);
		contentPane.add(comboBox_to);
		
		JLabel lb_to = new JLabel("TO");
		lb_to.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_to.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_to.setBounds(157, 142, 153, 25);
		contentPane.add(lb_to);
		
		txt_result = new JTextField();
		txt_result.setBackground(new Color(255, 255, 128));
		txt_result.setEditable(false);
		txt_result.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_result.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txt_result.setColumns(10);
		txt_result.setBounds(107, 236, 203, 25);
		contentPane.add(txt_result);
		
		JLabel lb_convert = new JLabel("CONVERT  CURRENCY");
		lb_convert.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_convert.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_convert.setBounds(157, 210, 153, 25);
		contentPane.add(lb_convert);
		
		JButton btn_convert = new JButton("CONVERT");
		btn_convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double amount;
				
				try {
					amount = Double.parseDouble(txt_amount.getText());
				} catch (Exception e2) {
					// TODO: handle exception
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "Invalid Input");
					return;
				}
				
				int idxFrom = comboBox_from.getSelectedIndex();
				int idxTo = comboBox_to.getSelectedIndex();
				double converted =0;
				
				converted = (amount / rate_per_baht[idxFrom])*rate_per_baht[idxTo];
				converted = (Math.round(converted*100)*1.0)/100;
				
				String unit = cur[idxTo].substring(0,cur[idxTo].indexOf(" "));
				txt_result.setText(converted+" "+unit);
				txt_amount.setText("");
				
			}
		});
		btn_convert.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_convert.setBounds(215, 288, 116, 32);
		contentPane.add(btn_convert);
		
		JButton btn_reset = new JButton("RESET");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_amount.setText("");
				txt_result.setText("");
				
				comboBox_from.setSelectedIndex(0);
				comboBox_to.setSelectedIndex(0);
			}
		});
		btn_reset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_reset.setBounds(89, 288, 116, 32);
		contentPane.add(btn_reset);
		
		JLabel lb_exchange = new JLabel("exchange rate from 03/15/2023");
		lb_exchange.setHorizontalAlignment(SwingConstants.LEFT);
		lb_exchange.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_exchange.setBounds(10, 330, 195, 25);
		contentPane.add(lb_exchange);
	}
}
