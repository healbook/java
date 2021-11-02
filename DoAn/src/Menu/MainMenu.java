package Menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTree;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JEditorPane;
import net.miginfocom.swing.MigLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainMenu {
	private static JTextField textField;
	
	 public MainMenu() {
		 
	 }
	public static void main(String[] args) {
		JFrame f;
		f = new JFrame();
		f.getContentPane().setLayout(null);
		
		JButton b1 = new JButton("Select XML File");
		b1.setBounds(666, 10, 302, 48);
		f.getContentPane().add(b1);
		
		JButton b4 = new JButton("Edit Node");
		b4.setBounds(809, 599, 159, 48);
		f.getContentPane().add(b4);
		
		textField = new JTextField();
		textField.setBounds(404, 10, 96, 34);
		f.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Port");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel.setBounds(357, 10, 207, 36);
		f.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Activate");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(323, 84, 135, 34);
		f.getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(280, 0, 2, 137);
		f.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(-18, 139, 782, 9);
		f.getContentPane().add(separator_1);
		
		JLabel lblNewLabel_1 = new JLabel("Localhost server\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblNewLabel_1.setBounds(21, 2, 235, 48);
		f.getContentPane().add(lblNewLabel_1);
		
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClose.setBounds(468, 84, 135, 34);
		f.getContentPane().add(btnClose);
		
		JLabel lblNewLabel_2 = new JLabel("Status:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(23, 82, 79, 38);
		f.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Closed");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(130, 71, 104, 52);
		f.getContentPane().add(lblNewLabel_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.BLACK);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 27));
		textArea.setBounds(10, 167, 613, 513);
		f.getContentPane().add(textArea);
		f.setVisible(true);
	}
}
