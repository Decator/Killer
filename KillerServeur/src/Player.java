import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Player extends UnicastRemoteObject implements PlayerInterface {

private static final long serialVersionUID = 1L;

  private String name;
  private int healthPoints;
  

  public Player(String name) throws RemoteException {
	  super();
    this.name = name;
    this.healthPoints = 30;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getHealthPoints() {
    return this.healthPoints;
  }

  @Override
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
    
  }

}
