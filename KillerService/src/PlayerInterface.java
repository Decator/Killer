import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote {
	public String getName() throws RemoteException;
	public int getHealthPoints() throws RemoteException;
	public void setHealthPoints(int losePoint) throws RemoteException;
	boolean getCurrentPlayer() throws RemoteException;
	void setCurrentPlayer(boolean currentPlayer) throws RemoteException;
}
