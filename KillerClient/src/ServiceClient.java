import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class ServiceClient {
	private KillerService server;
	
	ServiceClient(KillerService server) {
		this.server = server;
	}
	
	public void getInformation() throws RemoteException {
		String txt = JOptionPane.showInputDialog("What is your name?");
		
		String response = this.server.getInformation(txt);
		JOptionPane.showMessageDialog(null, response);
	}
}