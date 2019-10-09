import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KillerService extends Remote {
   
   public PlayerInterface addPlayer(String name) throws RemoteException;

}