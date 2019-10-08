import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class Client {

	private static KillerService look_up;

	public static void main(String[] args) {
		try {
			look_up = (KillerService) Naming.lookup("rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI");
			String txt = JOptionPane.showInputDialog("What is your name?");
				
			String response = look_up.getInformation(txt);
			JOptionPane.showMessageDialog(null, response);
			
		} catch (RemoteException e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
	    } catch (MalformedURLException e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
	    } catch (UnknownHostException e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
	    } catch (NotBoundException e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}