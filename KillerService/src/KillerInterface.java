import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KillerInterface extends Remote {
   public void addPlayer(PlayerInterface name) throws RemoteException;
   public void getPlayers() throws RemoteException;
   public void rollTheDice(int diceNumber) throws RemoteException;
   public void setScore(int score) throws RemoteException;
   public void endTurn() throws RemoteException;
}