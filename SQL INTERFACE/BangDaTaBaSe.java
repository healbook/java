package BaiGiuaKy;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BangDaTaBaSe extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Connection conn;
	private Statement stm;
	private DatabaseMetaData dbs;
	private ResultSet rst;
	private int SoBang;
	private ArrayList<String> tenBang = new ArrayList<String>();
	private JTextField tfCauLenhSql;
	private Vector vData = new Vector();
	private Vector vTitle = new Vector();
	private String tenBangDeXuat ;
	private int soLanNhan;
	private int ViTriNhan;
	private DefaultTableModel tablemodel ;
	private JTable table;
	private JScrollPane scp ;
	private JTextField textCauLenhSql;
	private String NoiDUngTuDong ;
	public BangDaTaBaSe(String localhost, String NameDaTaBaSe, String UserName, String port, String pass) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + localhost + ":" + port + "/" + NameDaTaBaSe, UserName,
					pass);
			dbs = conn.getMetaData();
			rst = dbs.getTables(null, null, "%", null);
			while (rst.next()) {
				SoBang++;
				tenBang.add(rst.getString(3));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1046, 672);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTree tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				soLanNhan = e.getClickCount();
			}
		});
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selected=  (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				ViTriNhan = e.getPath().getPathCount();
				tenBangDeXuat = selected.getUserObject().toString();
				if(ViTriNhan == 3) {
					reload("select * from "+tenBangDeXuat);
					contentPane.add(tree);
					tablemodel = new DefaultTableModel(vData,vTitle);
					table = new JTable(tablemodel);
					scp = new JScrollPane(table);
					scp.setBounds(50, 140, 725, 435);
					tablemodel.fireTableDataChanged();
					contentPane.add(scp);
					textCauLenhSql.setText(NoiDUngTuDong);
					
				}
				
			}
		});
		tree.setToolTipText("");
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(NameDaTaBaSe) {
			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("Table");
				for (String string : tenBang) {
					node_1.add(new DefaultMutableTreeNode(string));
				}
					add(node_1);

			}
			
		}));
		

		
		tree.setBounds(785, 27, 154, 548);
		contentPane.add(tree);
		
		textCauLenhSql = new JTextField();
		textCauLenhSql.setBounds(110, 27, 581, 69);
		contentPane.add(textCauLenhSql);
		textCauLenhSql.setColumns(10);
		
		JButton btnOK = new JButton("RUN");
		btnOK.addActionListener(this);
		btnOK.setBounds(349, 97, 85, 32);
		contentPane.add(btnOK);
		
		JButton btnThoat = new JButton("EXIT");
		btnThoat.setBounds(398, 592, 118, 33);
		contentPane.add(btnThoat);
		btnThoat.addActionListener(this);
		setVisible(true);
		
	}
	private void reload(String url) {
		try {
			vData.clear();
			vTitle.clear();
			stm = conn.createStatement();
			rst = stm.executeQuery(url);
			NoiDUngTuDong = url ;
			ResultSetMetaData rsmeta = rst.getMetaData();
			int cot = rsmeta.getColumnCount();
			for(int i = 1 ; i <= cot ; i++) {
				vTitle.add(rsmeta.getColumnLabel(i));
			}
			while(rst.next()) {
				Vector row = new Vector();
				for(int i = 1 ; i <= cot ; i++) {
					row.add(rst.getString(i));
				}
				vData.add(row);
			}
			rst.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("RUN")) {
			String s1 = "select";
			String s2 = "SELECT";
			if(textCauLenhSql.getText().contains(s1) || textCauLenhSql.getText().contains(s2)) {
				reload(textCauLenhSql.getText());
				tablemodel = new DefaultTableModel(vData,vTitle);
				table = new JTable(tablemodel);
				scp = new JScrollPane(table);
				scp.setBounds(50, 140, 725, 435);
				tablemodel.fireTableDataChanged();
				contentPane.add(scp);				
			}
			else {
				UpDate(textCauLenhSql.getText());
			}

		}
		else {
			dispose();
		}
		
	}
	private void UpDate(String url) {
		try {
			int x = -1 ;
			PreparedStatement pstm = conn.prepareStatement(url);
			x = (int) pstm.executeLargeUpdate();
			System.out.println("ok");
			if(x > 0) {
				JOptionPane.showMessageDialog(this, "Thành Công", "Thông Báo",
						JOptionPane.DEFAULT_OPTION);
			}

		} catch (SQLException e) {
//			 ?{
				JOptionPane.showMessageDialog(this, "Thất Bại", "Thông Báo",
						JOptionPane.DEFAULT_OPTION);
//			}
			e.printStackTrace();
		}
				
		
		
	}
}
