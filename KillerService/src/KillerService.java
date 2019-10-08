import java.rmi.Remote;

public interface KillerService extends Remote {
   
   public Player addPlayer(String name);

}