package BaiGiuaKy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.JPasswordField;

public class GiaoDien extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtLocalhost;
	private JTextField txtUserName;
	private JTextField tfDefault;
	private JTextField textField_4;
	private JTextField tfport;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDien frame = new GiaoDien();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GiaoDien() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeverHost = new JLabel("Sever Host");
		lblSeverHost.setBounds(31, 192, 100, 13);
		contentPane.add(lblSeverHost);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(31, 215, 100, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PassWord");
		lblNewLabel_1.setBounds(31, 238, 100, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Default Schema");
		lblNewLabel_2.setBounds(31, 261, 100, 13);
		contentPane.add(lblNewLabel_2);
		
		txtLocalhost = new JTextField();
		txtLocalhost.setText("localhost");
		txtLocalhost.setBounds(141, 189, 139, 19);
		contentPane.add(txtLocalhost);
		txtLocalhost.setColumns(10);
		
		txtUserName = new JTextField();
		txtUserName.setText("root");
		txtUserName.setBounds(141, 212, 139, 19);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		tfDefault = new JTextField("quanlydangkyxe");
		tfDefault.setBounds(141, 258, 139, 19);
		contentPane.add(tfDefault);
		tfDefault.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\lequa\\eclipse-workspace\\BaiGiuaKy\\anh\\1.png"));
		lblNewLabel_3.setBounds(176, 10, 201, 116);
		contentPane.add(lblNewLabel_3);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(164, 287, 85, 21);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(332, 287, 85, 21);
		contentPane.add(btnCancel);
		
		textField_4 = new JTextField();
		textField_4.setBounds(141, 166, 139, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("stored Connection");
		lblNewLabel_4.setBounds(31, 169, 108, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("port");
		lblNewLabel_5.setBounds(322, 169, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		tfport = new JTextField();
		tfport.setText("3306");
		tfport.setBounds(399, 166, 96, 19);
		contentPane.add(tfport);
		tfport.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 235, 139, 19);
		contentPane.add(passwordField);
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "OK") {
			String pass = String.valueOf(passwordField.getPassword());
			if(pass.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Nhập Mật Khẩu", "CẢNH BÁO",
						JOptionPane.ERROR_MESSAGE);
			}else {
				new BangDaTaBaSe(txtLocalhost.getText(),tfDefault.getText(),txtUserName.getText(),tfport.getText(),pass);
			}
			
		}
		else {
			System.exit(0);
		}
		
	}
}
