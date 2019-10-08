import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client extends JFrame implements ActionListener{

	private static KillerService look_up;
	private static ServiceClient serviceClient;
	
	public Client() {
		super();
		build();
	}
 
	private void build(){
		setTitle("Killer");
		setSize(800,600); 
		setLocationRelativeTo(null);
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
	}
	
	public JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
 
		JButton bouton = new JButton("Cliquez ici !");
		bouton.addActionListener(this);
		panel.add(bouton);
 
		return panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Clic");
		
		try {
			serviceClient.getInformation();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			look_up = (KillerService) Naming.lookup("rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI"); // To change
			serviceClient = new ServiceClient(look_up);
			
			Client client = new Client();
			client.show();
			
		} catch (MalformedURLException e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
	    } catch (UnknownHostException e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
	    } catch (NotBoundException e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
	    }
	}
}
