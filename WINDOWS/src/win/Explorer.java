package win;

import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import java.util.List;
public class Explorer extends JFrame {
	Vector data = new Vector();
	Vector columns = new Vector();
	JTextArea filepath;
	public JPanel contentPane;
	public JTextField txtpath;
	public JScrollPane sp;
	public JTable table;
	public JTree tree;
	public DefaultTreeModel treemodel;
	public DefaultMutableTreeNode root;
	public File currentFile;
	public DefaultTableModel model = new DefaultTableModel(0, 0);
	public JLabel namelb;
	public File disk;
	public DefaultMutableTreeNode Disk;
	public Desktop desktop;
	public File copysource,pastesource;
	
	public JPopupMenu popupMenu;
	public JPopupMenu tbpopupMenu;
	public JMenuItem openItem, copyItem, deleteItem, pasteItem,propertiesItem,renameItem,open,copy,delete,paste,properties;


	
	class PopupMenuListener extends MouseAdapter {
		public void mousePressed(MouseEvent me) {
			showPopup(me);
		}
		public void mouseReleased(MouseEvent me) {
			showPopup(me);
		}
		private void showPopup(MouseEvent me) {
			if (me.isPopupTrigger()) {
				popupMenu.show(me.getComponent(),
				me.getX(), me.getY());
			}
		}
	}
	
	      
	public  Explorer() {
		//POPUPMENU
		popupMenu = new JPopupMenu("");
		JMenuItem newItem = new JMenuItem("New");
		popupMenu.add(newItem);
		JMenuItem openItem = new JMenuItem("Open");
		popupMenu.add(openItem);
		popupMenu.addSeparator();
		JMenuItem copyItem = new JMenuItem("Copy");
		popupMenu.add(copyItem);
		JMenuItem deleteItem = new JMenuItem("Delete");
		popupMenu.add(deleteItem);
		JMenuItem pasteItem = new JMenuItem("Paste");
		popupMenu.add(pasteItem);
		renameItem = new JMenuItem("Rename");
		popupMenu.add(renameItem);
		popupMenu.addSeparator();
		JMenuItem propertiesItem = new JMenuItem("Properties");
		popupMenu.add(propertiesItem);

		PopupMenuListener pml = new PopupMenuListener();
		newItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File newfile = new File(filepath.getText()+"\\"+JOptionPane.showInputDialog("New File Name:"));
				try {
					if(newfile.createNewFile()) {
						JOptionPane.showMessageDialog(null, "Created");
					}
				} catch (HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
				setVisible(false);
				main(null);
			}
		});
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File treeselectedfile = new File(filepath.getText());
				if(treeselectedfile.isDirectory()) {
				 model = new DefaultTableModel(getdata(filepath.getText()),columns);
					table.setModel(model);
					
				}
				if(treeselectedfile.isFile()) {
					desktop = Desktop.getDesktop();
					try {
						desktop.open(treeselectedfile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			}
		});
		
		copyItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				copysource=new File(filepath.getText());
			}
		});
		pasteItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pastesource = new File(filepath.getText()+"\\"+copysource.getName()+"(Copy)");
				try {

					Files.copy(copysource.toPath(), pastesource.toPath());
				
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				main(null);
			}
		});
		deleteItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File deletefile = new File(filepath.getText());
				if(deletefile.isDirectory()) {
					if(deletefile.length()>0) {
					JOptionPane.showMessageDialog(null, "Folder must be empty!!!");
					}
					if(deletefile.length()==0) {
						if(JOptionPane.showConfirmDialog(null, "Delete "+deletefile.getName()+" ?","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
							deletefile.delete();
							if(!deletefile.exists()) {
								JOptionPane.showMessageDialog(null, "Deleted");
							}
						}
					}
				}
				if(deletefile.isFile()) {
					if(JOptionPane.showConfirmDialog(null, "Delete "+deletefile.getName()+" ?","",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
						deletefile.delete();
						if(!deletefile.exists()) {
							JOptionPane.showMessageDialog(null, "Deleted");
						}
					}
				}
				setVisible(false);
				main(null);
			}
		});
		renameItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Path source  = Paths.get(filepath.getText());
				try{

				    // rename a file in the same directory
				    Files.move(source, source.resolveSibling(JOptionPane.showInputDialog("Rename:")));

				  } catch (IOException d) {
				    JOptionPane.showMessageDialog(null, d);
				  }
				setVisible(false);
				main(null);
			}
		});
		propertiesItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				propertiesW(filepath.getText());
			}
		});
		//TABLEPOPUPMENU
		tbpopupMenu = new JPopupMenu("");
		JMenuItem open = new JMenuItem("Open");
		tbpopupMenu.add(open);
		tbpopupMenu.addSeparator();
		JMenuItem copy = new JMenuItem("Copy");
		tbpopupMenu.add(copy);
		JMenuItem delete = new JMenuItem("Delete");
		tbpopupMenu.add(delete);
		JMenuItem paste = new JMenuItem("Paste");
		tbpopupMenu.add(paste);
		tbpopupMenu.addSeparator();
		JMenuItem properties = new JMenuItem("Properties");
		tbpopupMenu.add(properties);
		PopupMenuListener tpml = new PopupMenuListener();
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tbselectedfilepath = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
				File tbfiletoopen = new File(tbselectedfilepath);
				Desktop tbdesktop = Desktop.getDesktop();
				if(tbfiletoopen.isFile()) {
				try {
					tbdesktop.open(tbfiletoopen);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
				}
				else {
					
					model = new DefaultTableModel(getdata(tbselectedfilepath),columns);
					table.setModel(model);
				}
			}
		});
		//
		
		ImageIcon treeic = new ImageIcon("icon.png");
		columns.addElement("Name");
		columns.addElement("Absolute Path");
		columns.addElement("Readable");
		columns.addElement("Writeable");
		columns.addElement("Size");
		columns.addElement("Type");
		columns.addElement("Date Modified");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setSize(1000, 700);
		filepath = new JTextArea();
		contentPane.add(filepath, BorderLayout.NORTH);
		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.WEST);
		panel1.setLayout(new GridLayout(0, 1, 0, 0));
		JButton ot = new JButton("OPEN IN TEXT");
		contentPane.add(ot,BorderLayout.SOUTH);

		JScrollPane scrollPane1 = new JScrollPane();
		panel1.add(scrollPane1);

		JTree tree = new JTree();
		tree.setSize(400, 200);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		scrollPane1.setViewportView(tree);
		panel1.setPreferredSize(new Dimension(200, 600));
        panel1.setMaximumSize(new Dimension(300, 600));
        tree.addMouseListener(pml);
        //Main table
		// New node
		root = new DefaultMutableTreeNode("this PC");
		
		File disk = new File("V:\\");
		DefaultMutableTreeNode Disk = new DefaultMutableTreeNode("V:\\");		
		DefaultMutableTreeNode DiskC = new DefaultMutableTreeNode("C:\\");	
		createChildren(disk, Disk);
		
		root.add(Disk);
		root.add(DiskC);
		// add data
		treemodel = new DefaultTreeModel(root);
		tree.setModel(treemodel);
	
		//OT
		ot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		
				if(table.getSelectedRow()==-1) {	
					JOptionPane.showMessageDialog(null, "Select file in table above to open");
				}
				if(table.getSelectedRow()>-1) {
				String tableselectedpath = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
				File tableselectedfile = new File(tableselectedpath);
				if(tableselectedfile.isFile()) {
					opentext(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
				}
				if(tableselectedfile.isDirectory()) {
					JOptionPane.showMessageDialog(null, "CANT OPEN FOLDER");
				}
				}
			}
		});
		
		//
		//TABLE
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(tpml);
		table.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2) {
					
					String selectedfilepath = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
					File filetoopen = new File(selectedfilepath);
					desktop = Desktop.getDesktop();
					if(filetoopen.isFile()) {
					try {
						desktop.open(filetoopen);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1);
					}
					
					
					}
					else {						
						model = new DefaultTableModel(getdata(selectedfilepath),columns);
						table.setModel(model);
						filepath.setText(selectedfilepath);
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stu
				
			}
		});
		//panel3
		JPanel panel3 = new JPanel();
		sp = new JScrollPane(table);
		panel3.setSize(500,500);
		panel3.add(sp);
		contentPane.add(panel3, BorderLayout.CENTER);
		sp.setViewportView(table);
		tree.addTreeSelectionListener(createSelectionListener());
		tree.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
	                tree.clearSelection();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		tree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2) {
					File treeselectedfile = new File(filepath.getText());
					if(treeselectedfile.isDirectory()) {
						File[] list = treeselectedfile.listFiles();
						if((list==null)==true) {
							JOptionPane.showMessageDialog(null, "Empty Folder");
						}
						else {
						model = new DefaultTableModel(getdata(filepath.getText()),columns);
						table.setModel(model);
						}
					}
					if(treeselectedfile.isFile()) {
						desktop = Desktop.getDesktop();
						try {
							desktop.open(treeselectedfile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1);
						}
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(tree.getRowForLocation(e.getX(),e.getY()) == -1) {
                    tree.clearSelection();
                }
			}
		});
	}
	 private TreeSelectionListener createSelectionListener() {
	        return new TreeSelectionListener() {
	            public void valueChanged(TreeSelectionEvent e) {
	            	filepath.setText("");
	                TreePath path = e.getPath();
	                Object pathe[] =path.getPath();
	                if(pathe.length==2) {
	                	filepath.setText(filepath.getText()+pathe[1]);
	                }
	                if(pathe.length==3) {
	                	for(int i =1;i<3;i++) {
	                		filepath.setText(filepath.getText()+pathe[i]);
	                	}
	                }
	                if(pathe.length>3) {
	                	for(int i =1;i<3;i++) {
	                		filepath.setText(filepath.getText()+pathe[i]);
	                	}
	                	for(int i=3;i<pathe.length;i++) {
	                		filepath.setText(filepath.getText()+"\\"+pathe[i]);
	                	}
	                }
	                
	            }
	        };
	    }
	 public Vector getdata(String folder) {
			data.clear();
			File file = new File(folder);
			try {
			File[] list = file.listFiles();
			if(list.length<0) {
				JOptionPane.showMessageDialog(null, "EMPTY FOLDER");
			}
			for (File files : list) {
				Vector row = new Vector(6);
				row.addElement(files.getName());
				row.addElement(files.getAbsolutePath());
				if (files.isFile()) {
					
					row.addElement(files.canRead());
					row.addElement(files.canWrite());
					float filesizeb =(float) files.length();
					float filesizekb=(float)files.length()/1000;
					float filesizemb=(float)filesizekb/1000;
					float filesizegb=(float)filesizemb/1000;
					if(filesizeb>=1e+9) {
						row.addElement(filesizegb+" GB");
					}
					if((filesizeb>=1e+6)&&(filesizeb<1e+9)) {
						row.addElement(filesizemb+" MB");
					}
					if((filesizeb>=1000)&&(filesizeb<1e+6)) {
						row.addElement(filesizekb+" KB");
					}
					if(filesizeb<1000) {
						row.addElement(filesizeb +" bytes");
					}
					if(filesizeb==0) {
						row.addElement("0 KB");
					}
				} else {
					row.addElement("Folder");
					row.addElement("Folder");
					row.addElement("Unspecified");
				}
				if (files.isDirectory()) {
					row.addElement("Folder");
				} else {
					row.addElement("File");
				}
				SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				String lastmodifieddate = date.format(files.lastModified());
				row.addElement(lastmodifieddate);
				data.add(row);
			}
			}catch(Exception e ) {
				JOptionPane.showMessageDialog(null, e);
			}
			return data;
		}
	
	public String FileNode(File file) {
		String name = file.getName();
		if (name.equals("")) {
			return file.getAbsolutePath();
		} else {
			return name;
		}
	}

	public void createChildren(File fileRoot, DefaultMutableTreeNode node) {
		File[] files = fileRoot.listFiles();
		if (files == null)
			return;
		for (File file : files) {
			if (file.isDirectory()) {
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(FileNode(file));
				node.add(childNode);
				if (file.isDirectory())
					createChildren(file, childNode);
			}
			else {
				DefaultMutableTreeNode simfile = new DefaultMutableTreeNode(FileNode(file));
				node.add(simfile);
			}

		}
	}
	public void opentext(String path) {
		Thread th = new Thread() 
		{
			@Override
			public void run() 
			{
				try 
				{
					JTextArea contentarea = new JTextArea();
					JFrame f1 = new JFrame(path);
					f1.setBounds(10, 10, 765, 650);
					f1.setLayout(null);
					f1.getContentPane().add(contentarea);			
					f1.setVisible(true);
					JButton saveb = new JButton("Save");
					saveb.setBounds(400, 550, 100, 40);
					f1.getContentPane().add(saveb);
					contentarea.setBounds(10, 10, 500, 500);
					JScrollPane sc = new JScrollPane(contentarea);
					f1.getContentPane().add(sc);
					sc.setBounds(10, 10, 700, 500);
					saveb.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							save(contentarea);
							f1.dispose();
						}
					});
					
					FileReader fr = new FileReader(path);
					BufferedReader bf = new BufferedReader(fr);
					String s;
					contentarea.setText("");
					while ((s = bf.readLine()) != null) 
					{
						contentarea.setText(contentarea.getText() + "\n" + s);
					}
				} 
				catch (Exception e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		th.start();
	}

	public void save(JTextArea contentarea) {
		Thread th = new Thread() {
			@Override
			public void run() {
				try {
					String content = contentarea.getText();
					FileWriter fw = new FileWriter(filepath.getText());
					fw.write(content);
					fw.flush();
					fw.close();
					JOptionPane.showMessageDialog(null, "Saved");
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		};
		th.start();
	}
	public void propertiesW(String path) {
		File propfile = new File(path);
		JFrame f;
		f = new JFrame();
		f.setSize(500, 680);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		f.getContentPane().setLayout(null);
		
		JLabel lb1 = new JLabel("File Path:");
		lb1.setBounds(10, 79, 135, 40);
		f.getContentPane().add(lb1);
		
		JTextField pathtf = new JTextField();
		pathtf.setBounds(144, 80, 342, 40);
		f.getContentPane().add(pathtf);
		pathtf.setEditable(false);
		pathtf.setColumns(10);
		
		JLabel lb2 = new JLabel("Readable:");
		lb2.setBounds(10, 147, 194, 40);
		f.getContentPane().add(lb2);
		
		JLabel lb3 = new JLabel("Writeable:");
		lb3.setBounds(10, 247, 194, 40);
		f.getContentPane().add(lb3);
		
		JLabel lb4 = new JLabel("Size:");
		lb4.setBounds(10, 347, 194, 40);
		f.getContentPane().add(lb4);
		
		JLabel lb5 = new JLabel("Type:");
		lb5.setBounds(10, 447, 194, 40);
		f.getContentPane().add(lb5);
		
		JLabel lb6 = new JLabel("Date Modified:");
		lb6.setBounds(10, 547, 194, 40);
		f.getContentPane().add(lb6);
		
		JLabel rlb = new JLabel("New label");
		rlb.setBounds(214, 147, 231, 40);
		rlb.setBorder(BorderFactory.createLineBorder(Color.black));
		f.getContentPane().add(rlb);
		
		JLabel wlb = new JLabel("New label");
		wlb.setBounds(214, 247, 231, 40);
		wlb.setBorder(BorderFactory.createLineBorder(Color.black));
		f.getContentPane().add(wlb);
		
		JLabel sizelb = new JLabel("New label");
		sizelb.setBounds(214, 347, 231, 40);
		sizelb.setBorder(BorderFactory.createLineBorder(Color.black));
		f.getContentPane().add(sizelb);
		
		JLabel typelb = new JLabel("New label");
		typelb.setBounds(214, 447, 231, 40);
		typelb.setBorder(BorderFactory.createLineBorder(Color.black));
		f.getContentPane().add(typelb);
		
		JLabel dmlb = new JLabel("New label");
		dmlb.setBounds(214, 547, 231, 40);
		dmlb.setBorder(BorderFactory.createLineBorder(Color.black));
		f.getContentPane().add(dmlb);
		
		JLabel	lb7 = new JLabel("Name:");
		lb7.setBounds(10, 10, 194, 40);
		
		f.getContentPane().add(lb7);
		
		JLabel namelb = new JLabel("New label");
		namelb.setBounds(214, 9, 231, 40);
		namelb.setBorder(BorderFactory.createLineBorder(Color.black));
		f.getContentPane().add(namelb);
		
		//prop
		
		namelb.setText(propfile.getName());
		pathtf.setText(propfile.getAbsolutePath());
		if(propfile.isDirectory()) {
			rlb.setText(String.valueOf(propfile.canRead()));
			wlb.setText(String.valueOf(propfile.canWrite()));
			sizelb.setText("Unspecified");
			typelb.setText("Folder");
	}
		if(propfile.isFile()) {
			rlb.setText(String.valueOf(propfile.canRead()));
			wlb.setText(String.valueOf(propfile.canWrite()));
			float filesizeb =(float)propfile.length();
			float filesizekb=(float)propfile.length()/1000;
			float filesizemb=(float)filesizekb/1000;
			float filesizegb=(float)filesizemb/1000;
			if(filesizeb>=1e+9) {
				sizelb.setText(filesizegb+" GB");
			}
			if((filesizeb>=1e+6)&&(filesizeb<1e+9)) {
				sizelb.setText(filesizemb+" MB");
			}
			if((filesizeb>=1000)&&(filesizeb<1e+6)) {
				sizelb.setText(filesizekb+" KB");
			}
			if(filesizeb<1000) {
				sizelb.setText(filesizeb +" bytes");
			}
			if(filesizeb==0) {
				sizelb.setText("0 KB");
			}
			typelb.setText("File");
		}
		SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String lastmodifieddate = date.format(propfile.lastModified());
		dmlb.setText(lastmodifieddate);
		//
		
		
		f.setVisible(true);
	}

    

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Explorer frame = new Explorer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

