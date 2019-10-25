import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {
	public static void main(String[] args) {		
		try {
		      LocateRegistry.createRegistry(1099);

		      String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI";
		      Server server = new Server();
		      Naming.rebind(url, server);
		      System.out.println("Serveur lance");
		      
		    } catch (RemoteException e) {
				System.err.println("Server exception: " + e.toString());
				e.printStackTrace();
		    } catch (MalformedURLException e) {
				System.err.println("Server exception: " + e.toString());
				e.printStackTrace();
		    } catch (UnknownHostException e) {
				System.err.println("Server exception: " + e.toString());
				e.printStackTrace();
		    }
	}
}