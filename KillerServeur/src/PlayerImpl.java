import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PlayerImpl extends UnicastRemoteObject implements Player {

private static final long serialVersionUID = 1L;

  private String name;
  private int healthPoints;
  

  public PlayerImpl(String name) throws RemoteException {
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
