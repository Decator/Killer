import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
   public void addClient(ClientInterface name) throws RemoteException;
   public void getClients(String name) throws RemoteException;
   public void rollTheDice(int diceNumber) throws RemoteException;
   public void setScore(int score) throws RemoteException;
   public void endTurn() throws RemoteException;
   public void startAttack(String attacker, String target) throws RemoteException;
   public void replay() throws RemoteException;
}