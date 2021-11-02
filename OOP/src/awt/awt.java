package awt;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.TextField;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class awt extends JFrame  {

	public awt() {
		
		TextField tf = new TextField();
		JFrame f = new JFrame();
		f.setTitle("Test");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton b = new JButton("Click here");
		b.setBounds(100, 100, 50, 50);;
		f.add(b);
		f.add(tf);

		Font testfont = new Font("Serif", Font.ITALIC, 30);
		f.setSize(500, 500);
		f.setLayout(null);
		f.setVisible(true);
		tf.setVisible(false);
		awt obj = new awt8/
		//Associate ActionListener with button
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ImageIcon ic = new ImageIcon("download.jfif");
				JLabel lb = new JLabel(ic);
				f.add(lb);
				f.pack();
				f.setSize(400, 400);
			}
		});
	}
	public void paint(Graphics g) {
		g = 
	}
	public static void main(String[] args) {
		new awt();
	}
 }


	
