import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote {
	public String getName() throws RemoteException;
	public int getHealthPoints() throws RemoteException;
	public void setHealthPoints(int healthPoints) throws RemoteException;
	public void waiting(String msg) throws RemoteException;
	public void initialisation(boolean currentPlayer) throws RemoteException;
}
