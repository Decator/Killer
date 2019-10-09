import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KillerService extends Remote {
   
   public void addPlayer(PlayerInterface name) throws RemoteException;

}