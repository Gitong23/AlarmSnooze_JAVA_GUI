package EV5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;

public class setAlarmFrame extends JFrame {

	private JPanel contentPane;
	public String[] month = new String[] {
			"Jan","Feb","Mar","Apr","May","Jun",
			"Jul","Aug","Sep","Oct","Nov","Dec"
	};
	public int[] day = new int[] {
			31,28,31,30,31,30,
			31,31,30,31,30,31
	};
	
	String soundFile;
	private JComboBox comboBox_Year;
	private JComboBox comboBox_month;
	private JComboBox comboBox_Day;
	private JComboBox comboBox_hour;
	private JComboBox comboBox_minute;
	private JRadioButton rdbtnSnooze;
	private JRadioButton rdbtnEveryDay;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setAlarmFrame frame = new setAlarmFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public setAlarmFrame() {
		setTitle("Set Alarm");
		setBounds(100, 100, 468, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String hour[] = new String[24];
		for(int i=0; i<hour.length; i++) {
			hour[i] = String.format("%02d", i);
		}
		
		String minute[] = new String[60];
		for(int i=0; i<minute.length; i++) {
			minute[i] = String.format("%02d", i);
		}
				
		
		comboBox_hour = new JComboBox(hour);
		comboBox_hour.setFont(new Font("Tahoma", Font.PLAIN, 19));
		comboBox_hour.setBounds(119, 120, 78, 32);
		contentPane.add(comboBox_hour);
		
		JLabel lbl_colon = new JLabel(":");
		lbl_colon.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_colon.setBounds(207, 113, 45, 38);
		contentPane.add(lbl_colon);
		
		comboBox_minute = new JComboBox(minute);
		comboBox_minute.setFont(new Font("Tahoma", Font.PLAIN, 19));
		comboBox_minute.setBounds(231, 120, 78, 32);
		contentPane.add(comboBox_minute);
		
		JButton btn_Confirm = new JButton("CONFIRM");
		btn_Confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainFrame.alarm.hour = comboBox_hour.getSelectedIndex();
				MainFrame.alarm.minute = comboBox_minute.getSelectedIndex();
				
				MainFrame.alarm.year = 2023+comboBox_Year.getSelectedIndex();
				MainFrame.alarm.month = month[comboBox_month.getSelectedIndex()];
				MainFrame.alarm.day = comboBox_Day.getSelectedIndex()+1;
				
				MainFrame.alarm.dayOfWeek = getDayOfWeek(MainFrame.alarm.year, comboBox_month.getSelectedIndex()+1, MainFrame.alarm.day);
				
				if(rdbtnEveryDay.isSelected()) {
					MainFrame.alarm.isEveryDay = true;
				}
				else {
					MainFrame.alarm.isEveryDay = false;
				}
					
				if(rdbtnSnooze.isSelected()) {
					MainFrame.alarm.isSnooze = true;
				}
				else {
					MainFrame.alarm.isSnooze = false;
				}
						
				MainFrame.alarm.isSetted = true;
				
				System.out.println("Alarm has been set "+MainFrame.alarm.isSetted);
				System.out.println("Every day "+MainFrame.alarm.isEveryDay);
				System.out.println("Snooze "+MainFrame.alarm.isSnooze);
				
				if(soundFile != null) {
					AudioPlayer.filePath=soundFile;
					System.out.println("sound file has been change");
					System.out.println(soundFile);
				}
				setVisible(false);
			}
		});
		btn_Confirm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_Confirm.setBounds(219, 317, 119, 32);
		contentPane.add(btn_Confirm);
		
		comboBox_month = new JComboBox(month);
		comboBox_month.setFont(new Font("Tahoma", Font.PLAIN, 19));
		comboBox_month.setBounds(163, 44, 78, 32);
		contentPane.add(comboBox_month);
		
		int idx=comboBox_month.getSelectedIndex();
		int arr[] = new int[day[idx]];
		for(int i=1;i<=arr.length; i++) {
			arr[i-1]=i;
		}
		
		JLabel lbl_month = new JLabel("MONTH");
		lbl_month.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_month.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_month.setBounds(163, 21, 78, 13);
		contentPane.add(lbl_month);
		
		JLabel lbl_month_1 = new JLabel("DAY");
		lbl_month_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_month_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_month_1.setBounds(273, 21, 78, 13);
		contentPane.add(lbl_month_1);
		
		JLabel lbl_hour = new JLabel("HOUR");
		lbl_hour.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_hour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_hour.setBounds(119, 97, 78, 13);
		contentPane.add(lbl_hour);
		
		JLabel lbl_minute = new JLabel("MINUTE");
		lbl_minute.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_minute.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_minute.setBounds(231, 97, 78, 13);
		contentPane.add(lbl_minute);
		
		JLabel lbl_Year = new JLabel("YEAR");
		lbl_Year.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Year.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Year.setBounds(61, 21, 78, 13);
		contentPane.add(lbl_Year);
		
		rdbtnEveryDay = new JRadioButton("SET EVERY DAY");
		rdbtnEveryDay.setBounds(163, 214, 119, 38);
		contentPane.add(rdbtnEveryDay);
		
		rdbtnSnooze = new JRadioButton("SNOOZE");
		rdbtnSnooze.setBounds(163, 256, 119, 38);
		contentPane.add(rdbtnSnooze);
		
		JButton btn_Cancel = new JButton("CANCEL ALARM");
		btn_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAlarmFrame f =new setAlarmFrame();
				MainFrame.alarm.isSetted = false;
				JOptionPane.showConfirmDialog(f,"Cancel Alarm");
				setVisible(false);
			}
		});
		btn_Cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Cancel.setBounds(78, 317, 119, 32);
		contentPane.add(btn_Cancel);
		
		JButton btn_Brows = new JButton("BROWS...");
		btn_Brows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == btn_Brows) {
					JFileChooser fileChooser = new JFileChooser();
					
					int response = fileChooser.showOpenDialog(null); // Select file to open
					
					if(response == JFileChooser.APPROVE_OPTION) {
						File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
						soundFile = file.toString();
					}
				}
			}
		});
		btn_Brows.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Brows.setBounds(244, 173, 94, 23);
		contentPane.add(btn_Brows);
		
		JLabel lbl_minute_1 = new JLabel("Select Sound File (.WAV)");
		lbl_minute_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_minute_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_minute_1.setBounds(45, 174, 189, 19);
		contentPane.add(lbl_minute_1);
		

		String strDay[] = new String[31];
		for(int i=1; i<=strDay.length; i++) {
			strDay[i-1] = i+"";
		}
		comboBox_Day = new JComboBox(strDay);
		comboBox_Day.setBounds(273, 44, 78, 31);
		contentPane.add(comboBox_Day);
		
		
		String strYear[] = new String[100];
		for(int i=0; i<100; i++) {
			strYear[i] = (2023+i)+"";
		}
		comboBox_Year = new JComboBox(strYear);
		comboBox_Year.setBounds(45, 44, 94, 31);
		contentPane.add(comboBox_Year);
	}
	
	private static String getDayOfWeek(int year , int month, int day) {
		LocalDate date = LocalDate.of(year, month, day); // replace with your desired date
	    DayOfWeek dayOfWeek = date.getDayOfWeek();
	    String s = dayOfWeek.toString();
	    return s.charAt(0)+s.substring(1,3).toLowerCase();
	}
}
