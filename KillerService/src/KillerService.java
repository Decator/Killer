import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KillerService extends Remote {

   public String getInformation(String txt) throws RemoteException;

}