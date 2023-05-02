package EV5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WakeUpSnooze extends JFrame {

	private JPanel contentPane;
	
	public static void main(String [] args) {
		WakeUpSnooze f = new WakeUpSnooze();
		f.setVisible(true);

	}
	
	public WakeUpSnooze() {
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 276, 183);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_WakeUp = new JLabel("WAKE UP!!!");
		lb_WakeUp.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_WakeUp.setBounds(65, 10, 132, 35);
		contentPane.add(lb_WakeUp);
		
		JButton btn_closeAlarm = new JButton("CLOSE ALARM");
		btn_closeAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					MainFrame.audioPlayer.stop();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(MainFrame.alarm.isEveryDay == false) {
					MainFrame.alarm.isSetted = false;
					System.out.println("Alarm has been set "+MainFrame.alarm.isSetted);
				}
				
				if(MainFrame.alarm.isEveryDay) {
					MainFrame.alarm.setTime();//set time tomorrow
				}
				
				setVisible(false);
			}
		});
		btn_closeAlarm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_closeAlarm.setBounds(10, 100, 122, 36);
		contentPane.add(btn_closeAlarm);

		
		JLabel lb_Hour = new JLabel("00");
		lb_Hour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_Hour.setBounds(75, 55, 28, 35);
		contentPane.add(lb_Hour);
		
		JLabel lb_colon = new JLabel(":");
		lb_colon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_colon.setBounds(113, 55, 7, 35);
		contentPane.add(lb_colon);
		
		JLabel lb_Minute = new JLabel("00");
		lb_Minute.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_Minute.setBounds(142, 55, 28, 35);
		contentPane.add(lb_Minute);
		
		JButton btn_snozze = new JButton("SNOOZE");
		btn_snozze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainFrame.audioPlayer.stop();
					MainFrame.alarm.setTimeSnooze();
					setVisible(false);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				MainFrame.alarm.setTimeSnooze();
			}
		});

		btn_snozze.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_snozze.setBounds(131, 100, 121, 36);
		contentPane.add(btn_snozze);
		
		lb_Hour.setText(String.format("%02d", MainFrame.alarm.hour));
		lb_Minute.setText(String.format("%02d", MainFrame.alarm.minute));
		
		//System.out.println("Check isSnooze frome Wake Up "+MainFrame.alarm.isSnooze);
		if(MainFrame.alarm.isSnooze == false) {
			btn_snozze.setVisible(false);
			btn_closeAlarm.setBounds(65, 100, 122, 36);
		}
		
	}
}
