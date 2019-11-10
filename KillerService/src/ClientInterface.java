import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {
	public PlayerInterface getPlayer() throws RemoteException;
	public void setServerMessage(String msg) throws RemoteException;
	public void initialisation() throws RemoteException;
	public void setDices(int[] rollTheDice) throws RemoteException;
	public void setClients(ArrayList<ClientInterface> clients) throws RemoteException;
	public void setScore(int score) throws RemoteException;
	public void endTurn() throws RemoteException;
	public void startAttack(ClientInterface attackerPlayer, ClientInterface targetPlayer) throws RemoteException;
	public void attack(int damage) throws RemoteException;
	public void endGame() throws RemoteException;
	public void replay() throws RemoteException;
}