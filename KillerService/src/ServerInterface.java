import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
   public void addPlayer(PlayerInterface name) throws RemoteException;
   public void getPlayers(String name) throws RemoteException;
   public void rollTheDice(int diceNumber) throws RemoteException;
   public void setScore(int score) throws RemoteException;
   public void endTurn() throws RemoteException;
   public void startAttack(String attacker, String target) throws RemoteException;
}