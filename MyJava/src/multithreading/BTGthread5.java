package multithreading;
//BAI TAP 5 (TU GIAI)//
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
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class BTGthread5 extends JFrame implements Runnable {

	JButton createClock = new JButton("new VietNam Clock");
	JButton c1 = new JButton("New Shanghai Clock(China)");
	JButton c2 = new JButton("New Chicago Clock(USA)");
	JButton c3 = new JButton("New Moscow Clock(Russian)");
	JLabel clock = new JLabel();
	Random r = new Random();
	
	public BTGthread5() {
		JFrame f = new JFrame("");
		f.setLayout(null);
		createClock.setBounds(100, 100, 300, 40);
		f.add(createClock);
		createClock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t = new Thread(new thread1());
				t.start();
			}
		});
		clock.setBounds(150, 10, 200, 50);
		f.add(clock);
		c1.setBounds(100, 200, 300, 40);
		c1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t2 = new Thread(new thread2());
				t2.start();
			}
		});
		f.add(c1);
		c2.setBounds(100, 300, 300, 40);
		c2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t3 = new Thread(new thread3());
				t3.start();
			}
		});
		f.add(c2);
		c3.setBounds(100, 400, 300, 40);
		c3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread t4 = new Thread(new thread4());
				t4.start();
			}
		});
		f.add(c3);
		f.setVisible(true);
		f.setSize(500, 500);
		f.setLocation(100,100);
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BTGthread5();
	}
	class thread1 extends JFrame implements Runnable {
		JLabel title = new JLabel("VietNam Clock is ticking!!!");
		JLabel clock = new JLabel();
		Thread t2;
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
		public thread1() {
			Container cont = this.getContentPane();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			clock = new JLabel(sdf.format(cal.getTime()),JLabel.CENTER);
			clock.setFont(new Font(clock.getFont().getName(),Font.PLAIN, 40));
			clock.setForeground(Color.red);
			cont.add(clock);
			cont.add(title,"North");
			this.pack();
			this.setVisible(true);
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
		
	}	
	
class thread2 extends JFrame implements Runnable {
	JLabel title = new JLabel("Shanghai Clock is ticking!!!");
	JLabel clock = new JLabel();
	Thread t2;
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
	public thread2() {
		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
		Container cont = this.getContentPane();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(tz);
		clock = new JLabel(sdf.format(Calendar.getInstance().getTime()),JLabel.CENTER);
		clock.setFont(new Font(clock.getFont().getName(),Font.PLAIN, 40));
		clock.setForeground(Color.red);
		cont.add(title,"North");
		cont.add(clock);
		this.pack();
		this.setVisible(true);
		Thread t2 = new Thread(this);
		t2.start();
	}
	public void tick() {
		try {
			TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			sdf.setTimeZone(tz);
			clock.setText(sdf.format(Calendar.getInstance().getTime()));
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
	
}
class thread3 extends JFrame implements Runnable {
	JLabel title = new JLabel("Chicago Clock is ticking!!!");
	JLabel clock = new JLabel();
	Thread t3;
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
	public thread3() {
		TimeZone tz = TimeZone.getTimeZone("America/Chicago");
		Container cont = this.getContentPane();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(tz);
		clock = new JLabel(sdf.format(Calendar.getInstance().getTime()),JLabel.CENTER);
		clock.setFont(new Font(clock.getFont().getName(),Font.PLAIN, 40));
		clock.setForeground(Color.red);
		cont.add(title,"North");
		cont.add(clock);
		this.pack();
		this.setVisible(true);
		Thread t3 = new Thread(this);
		t3.start();
	}
	public void tick() {
		try {
			TimeZone tz = TimeZone.getTimeZone("America/Chicago");
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			sdf.setTimeZone(tz);
			clock.setText(sdf.format(Calendar.getInstance().getTime()));
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
	
}
class thread4 extends JFrame implements Runnable {
	JLabel title = new JLabel("Moscow Clock is ticking!!!");
	JLabel clock = new JLabel();
	Thread t4;
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
	public thread4() {
		TimeZone tz = TimeZone.getTimeZone("Europe/Moscow");
		Container cont = this.getContentPane();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(tz);
		clock = new JLabel(sdf.format(Calendar.getInstance().getTime()),JLabel.CENTER);
		clock.setFont(new Font(clock.getFont().getName(),Font.PLAIN, 40));
		clock.setForeground(Color.red);
		cont.add(title,"North");
		cont.add(clock);
		this.pack();
		this.setVisible(true);
		Thread t4 = new Thread(this);
		t4.start();
	}
	public void tick() {
		try {
			TimeZone tz = TimeZone.getTimeZone("Europe/Moscow");
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			sdf.setTimeZone(tz);
			clock.setText(sdf.format(Calendar.getInstance().getTime()));
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
	
}
}
