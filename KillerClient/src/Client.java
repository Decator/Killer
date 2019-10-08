import java.awt.FlowLayout;
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
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener{

	private static KillerService look_up;
	private static ServiceClient serviceClient;
	
	private JButton joinGameButton;
	private JTextField nameField;
	
	public Client() {
		super();
		
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
		
		nameField = new JTextField();
		nameField.setColumns(10);
		panel.add(nameField);
 
		joinGameButton = new JButton("Rejoindez la game");
		joinGameButton.addActionListener(this);
		panel.add(joinGameButton);
 
		return panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		 
		if(source == joinGameButton){
			serviceClient.addPlayer(nameField.getText());
			System.out.println(serviceClient.getPlayer());
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
