package DoAn;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;

public class ChatMClient extends JFrame implements Runnable, ActionListener {
	JMenuBar mnb = new JMenuBar();
	JMenu mn;
	JTextArea text = new JTextArea(new PlainDocument(),"",20,30);
	DefaultListModel lt= new DefaultListModel();
	JList list = new JList(lt);
	JTextField tf = new JTextField("",20);
	JButton send = new JButton("Send to all",new ImageIcon("1.gif"));
	JButton sendp = new JButton("Send Private");
	JButton btexit = new JButton("Exit");
	GridBagLayout  gb = new GridBagLayout();
	GridBagConstraints gc= new GridBagConstraints();
	Container cnt;
	Socket kkSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    String fromServer;
    JScrollPane sp= new JScrollPane(text);
	
	public void ADD(JComponent o,int x,int y,int n,int m){
			gc.gridx=y;
			gc.gridy=x;
			gc.gridheight=n;
			gc.gridwidth=m;
			gb.setConstraints(o,gc);
			cnt.add(o);
	}
	
	public void exit(){
		try{
			out.close();
	        in.close();
		    kkSocket.close();
			System.exit(0);
		}catch (Exception ex){}
		finally{System.exit(0);}
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("Exit")) exit();
		if (e.getActionCommand().equals("About")) JOptionPane.showMessageDialog(this,"\"LAN Chat\" writeln in Java\nThanh Tuan 2004\n"+	System.getProperty("os.name")+"  "+System.getProperty("os.version"));
		if (e.getSource()==send) out.println("all"+tf.getText());
		if (e.getSource()==btexit) exit();
		try{
			if (e.getSource()==sendp) {
				Object s[]= (list.getSelectedValues());
				out.println("prv"+tf.getText());
				for (int i=0;i<s.length;i++ ) out.println("usr"+s[i]);
				out.println("end");
			}
		}catch (Exception ex){System.out.println(ex.toString());};
	}
	
	public void run(){
		try{
		 while (true){
            fromServer = in.readLine();
			System.out.println(fromServer);
			String header= fromServer.substring(0,3);
			//if (header.equals("msg"))
				text.append(fromServer+"\n");//.substring(3)+"\n");	
			/*if (header.equals("add")) lt.addElement(fromServer.substring(3));	
			if (header.equals("rmv")) lt.removeElement(fromServer.substring(3));	
			if (header.equals("err")) */
				if (fromServer.substring(3).equals("nick"))	out.println(JOptionPane.showInternalInputDialog(cnt,"Enter nick name"));				
		 }}catch (Exception ex){}
	}
	
	public ChatMClient(String host)throws IOException{
		cnt=getContentPane();
		JMenuItem mni;
		mnb.add(mn=new JMenu("File"));
		mn.add(mni=new JMenuItem("Exit"));
		mni.addActionListener(this);
		mnb.add(mn=new JMenu("Help"));
		mn.add(mni=new JMenuItem("About"));
		mni.addActionListener(this);
		setJMenuBar(mnb);
		gc.insets=new Insets(1,1,1,1);
		cnt.setLayout(gb);
		gc.fill=GridBagConstraints.BOTH;
		text.setEditable(false);
		ADD(sp,0,0,1,3);
		ADD(list,0,3,1,2);
		ADD(tf,1,0,1,1);
		ADD(send,1,1,1,2);
		send.addActionListener(this);
		sendp.addActionListener(this);
		btexit.addActionListener(this);
		ADD(sendp,1,3,1,1);
		ADD(btexit,1,4,1,1);
		Border low=BorderFactory.createBevelBorder(EtchedBorder.LOWERED);
		Border high=BorderFactory.createBevelBorder(EtchedBorder.RAISED);
		text.setBorder(low);
		tf.setBorder(low);
		list.setBorder(low);
		send.setBorder(high);
		sendp.setBorder(high);
		btexit.setBorder(high);
	    try {
            kkSocket = new Socket(host,9891);
            out = new PrintWriter(kkSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        }catch (UnknownHostException e) {
            System.err.println("Don't know about host:"+host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+host);
            System.exit(1);
        }
		pack();
		out.println(JOptionPane.showInternalInputDialog(cnt,"Enter nick name"));
		Thread th = new Thread(this);
		th.start();
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
			exit();
			}
		});
    }
	
	public static void main(String[] s) throws IOException {
		ChatMClient c = new ChatMClient("localhost");
		c.show();
	}
}