package multithreading;
//BAI TAP 4 ( CO LOI GIAI)//
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class thread4 extends Frame {
	private highthread high;
	private lowthread low;
	private TextArea output;
	public thread4() {
		super("thread4");
		output = new TextArea(10,20);
		add(output);
		setSize(250,200);
		setVisible(true);
		high = new highthread(output);
		high.start();
		low = new lowthread(output);
		low.start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		thread3 app = new thread3();
		app.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
class highthread extends Thread {
	private TextArea display;
	public highthread(TextArea a) {
		display=a;
		setPriority(Thread.MAX_PRIORITY);
	}
	public void run() {
		for(int x =1;x<=5;x++) {
			display.append("High Priority Thread!!!!\n");
		}
		try {
			Thread.sleep(1000);
		} 
		catch(Exception e ) {
			
		}
	}
}
class lowthread extends Thread {
	private TextArea display;
	public lowthread(TextArea a) {
		display=a;
		setPriority(Thread.MIN_PRIORITY);
	}
	public void run() {
		for(int y =1;y<=5;y++) {
			display.append("Low Priority Thread!!!!\n");
		}
	}
}

}
