package EV5;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	public static Clock current_Clock = new Clock();
	public static Alarm alarm = new Alarm();
	public static AudioPlayer audioPlayer;
	public WakeUpSnooze wakeUpSnooze ;
	public SetSnoozeFrame setSnoozeFrame = new SetSnoozeFrame();

	
	public static void main(String[] args) {
		
		try {
			audioPlayer = new AudioPlayer();
			audioPlayer.stop();
			//audioPlayer.restart();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		MainFrame f = new MainFrame();
		f.setVisible(true);
	}
	
	public MainFrame() {
		
		
		setTitle("EV5 Alarm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("InternalFrame.border"));
		panel.setBackground(new Color(153, 180, 209));
		panel.setBounds(10, 10, 466, 359);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lb_hour = new JLabel("00");
		lb_hour.setFont(new Font("Tahoma", Font.BOLD, 90));
		lb_hour.setBounds(21, 80, 126, 123);
		panel.add(lb_hour);
		
		JLabel lb_colon = new JLabel(":");
		lb_colon.setFont(new Font("Tahoma", Font.PLAIN, 90));
		lb_colon.setBounds(142, 80, 38, 123);
		panel.add(lb_colon);
		
		JLabel lb_minute = new JLabel("00");
		lb_minute.setFont(new Font("Tahoma", Font.BOLD, 90));
		lb_minute.setBounds(174, 80, 140, 123);
		panel.add(lb_minute);
		
		JLabel lb_dayOfWeek = new JLabel("Day,");
		lb_dayOfWeek.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lb_dayOfWeek.setBounds(69, 215, 75, 70);
		panel.add(lb_dayOfWeek);
		
		JLabel lb_day = new JLabel("00");
		lb_day.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lb_day.setBounds(154, 215, 48, 70);
		panel.add(lb_day);
		
		JLabel lb_month = new JLabel("Jan");
		lb_month.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lb_month.setBounds(226, 215, 89, 70);
		panel.add(lb_month);
		
		JLabel lb_year = new JLabel("1997");
		lb_year.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lb_year.setBounds(325, 213, 89, 70);
		panel.add(lb_year);
		
		JLabel lb_colon_1 = new JLabel(":");
		lb_colon_1.setFont(new Font("Tahoma", Font.PLAIN, 90));
		lb_colon_1.setBounds(287, 80, 38, 123);
		panel.add(lb_colon_1);
		
		JLabel lb_sec = new JLabel("00");
		lb_sec.setFont(new Font("Tahoma", Font.BOLD, 90));
		lb_sec.setBounds(326, 80, 140, 123);
		panel.add(lb_sec);
		
		current_Clock.setTime();
		lb_hour.setText(String.format("%02d", current_Clock.hour));
		lb_minute.setText(String.format("%02d", current_Clock.minute));
		lb_sec.setText(String.format("%02d", current_Clock.sec));
		lb_day.setText(String.format("%02d", current_Clock.day));
		lb_dayOfWeek.setText(current_Clock.dayOfWeek);
		lb_month.setText(current_Clock.month);
		lb_year.setText(current_Clock.year+"");
		
		JLabel lb_wake = new JLabel("Plase Set Alarm");
		lb_wake.setHorizontalAlignment(SwingConstants.CENTER);
		lb_wake.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lb_wake.setBounds(21, 295, 393, 38);
		panel.add(lb_wake);
		
		JButton btn_SetAlarm = new JButton("Set Alarm");
		btn_SetAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 setAlarmFrame f = new setAlarmFrame();
				 f.setVisible(true);
			}
		});
		btn_SetAlarm.setBackground(Color.LIGHT_GRAY);
		btn_SetAlarm.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_SetAlarm.setBounds(10, 379, 231, 74);
		contentPane.add(btn_SetAlarm);
		
		JButton btn_SetSnooze = new JButton("Set Snooze");
		btn_SetSnooze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSnoozeFrame.setVisible(true);
			}
		});
		btn_SetSnooze.setFont(new Font("Tahoma", Font.BOLD, 27));
		btn_SetSnooze.setBackground(Color.LIGHT_GRAY);
		btn_SetSnooze.setBounds(245, 379, 231, 74);
		contentPane.add(btn_SetSnooze);
		

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					current_Clock.setTime();
					lb_hour.setText(String.format("%02d", current_Clock.hour));
					lb_minute.setText(String.format("%02d", current_Clock.minute));
					lb_sec.setText(String.format("%02d", current_Clock.sec));
					lb_day.setText(String.format("%02d", current_Clock.day));
					lb_dayOfWeek.setText(current_Clock.dayOfWeek);
					lb_month.setText(current_Clock.month);
					lb_year.setText(current_Clock.year+"");
					
					if (alarm.isSetted) {
						lb_wake.setText("Alarm ("+String.format("%02d", alarm.hour)+":"+String.format("%02d", alarm.minute)+" "+alarm.dayOfWeek+")");
					}
					else {
						lb_wake.setText("Please Set Alarm");
					}
				}
			}
		});
		
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(alarm.isSetted) {
						if(
								(alarm.hour == current_Clock.hour) && 
								(alarm.minute == current_Clock.minute) &&
								(alarm.year == current_Clock.year) &&
								(alarm.month.toLowerCase().equals(current_Clock.month.toLowerCase())) &&
								(alarm.day == current_Clock.day)
								)
						{
							wakeUpSnooze = new WakeUpSnooze();
							wakeUpSnooze.setVisible(true);
							
							
							try {
								audioPlayer.restart();
							} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							alarm.year=-1; //stop while loop temporary
							
						}	
					}
					
					if(wakeUpSnooze != null) {
						if(!wakeUpSnooze.isVisible() ) {
							if(audioPlayer != null) {
								try {	
									audioPlayer.stop();
									
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
	
				}
			}
		});
				
		t.start();
		t2.start();

	}
}
	
 

