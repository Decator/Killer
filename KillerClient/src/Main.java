import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {
	
	public static void main(String[] args) {
		try {
			ServerInterface look_up = (ServerInterface) Naming.lookup("rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI"); // To change
			
			MainFrame client = new MainFrame(look_up);
			client.setVisible(true);
			
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
