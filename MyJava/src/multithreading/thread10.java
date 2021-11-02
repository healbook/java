package multithreading;
//BAI TAP 10 ( CO LOI GIAI)//
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class thread10 extends JFrame implements ActionListener,Runnable {

	JButton createClock = new JButton("new Clock");
	JLabel clock = new JLabel();
	Thread t;
	Random r = new Random();
	public void setVisible(boolean visible){
	    super.setVisible(visible);
	    if (visible) {
	        Random r = new Random();
	        // Find the screen size
	        Toolkit tk = Toolkit.getDefaultToolkit();
	        Dimension d = tk.getScreenSize();
	        // randomize new location taking into account
	        // the screen size, and current size of the window
	        int x = r.nextInt(d.width - getWidth());
	        int y = r.nextInt(d.height - getHeight());
	        setLocation(x, y);
	    }
	}
	public thread10() {
		Container cont = this.getContentPane();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		clock = new JLabel(sdf.format(cal.getTime()),JLabel.CENTER);
		clock.setFont(new Font(clock.getFont().getName(),Font.PLAIN, 40));
		clock.setForeground(Color.red);
		cont.add(createClock,"North");
		cont.add(clock);
		this.pack();
		this.setVisible(true);
		createClock.addActionListener(this);
		Thread t = new Thread(this);
		t.start();
	}
	public void tick() {
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			clock.setText(sdf.format(cal.getTime()));
			Thread.sleep(1000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		while(true) {
			tick();
		}
	}
	public void actionPerformed(ActionEvent e) {
		Thread t = new Thread(new thread10());
		t.start();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new thread10();
	}

}
