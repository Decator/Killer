import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KillerService extends Remote {
   
   public Player addPlayer(String name) throws RemoteException;

}