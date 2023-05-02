package EV5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetSnoozeFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetSnoozeFrame frame = new SetSnoozeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public SetSnoozeFrame() {
		setTitle("SET SNOOZE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 245);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_confirm = new JButton("CONFIRM");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.alarm.snoozeMin = comboBox.getSelectedIndex();
				setVisible(false);
			}
		});
		btn_confirm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_confirm.setBounds(158, 152, 102, 31);
		contentPane.add(btn_confirm);
		
		
		String arr[] = new String[60];
		for(int i=0; i<arr.length; i++) {
			arr[i] = i+"";
		}
		
		comboBox = new JComboBox(arr);
		comboBox.setBounds(158, 78, 102, 31);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("SET SNOOZE TIME (MIN.)\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(113, 36, 197, 31);
		contentPane.add(lblNewLabel);
	}

}
