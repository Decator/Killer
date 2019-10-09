import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Serveur {
	public static void main(String[] args) {		
		try {
		      LocateRegistry.createRegistry(1099);

		      String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI";
		      Killer game = new Killer();
		      Naming.rebind(url, game);

		      System.out.println("Serveur lance");
		      
		      while(game.getPlayers().size() < 4) {
		      }
		      
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