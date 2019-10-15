import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PlayerInterface extends Remote {
	public String getName() throws RemoteException;
	public boolean getCurrentPlayer() throws RemoteException;
	public int getHealthPoints() throws RemoteException;
	public void waiting(String msg) throws RemoteException;
	public void initialisation(boolean currentPlayer) throws RemoteException;
	public void setDices(int[] rollTheDice) throws RemoteException;
	public void setPlayers(ArrayList<PlayerInterface> players) throws RemoteException;
	public void setScore(int score) throws RemoteException;
}