package JFrame;

import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu extends Frame{
	public MainMenu() {
		JFrame f = new JFrame("Menu chuc nang!!");
		JButton b1 = new JButton("Xem thu nhap hom nay.");
		JButton b2 = new JButton("Xem danh sach hoa don.");
		b1.setBounds(100, 100, 50, 50);
		b2.setBounds(50, 50,50,50);
		b1.setSize(50, 50);
		b2.setSize(50, 50);
		f.add(b1);
		f.add(b2);
		f.setSize(1000, 1000);
		f.setVisible(true);
		f.setLayout(new GridLayout(2,2,1,1));
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainMenu();
	}

}
