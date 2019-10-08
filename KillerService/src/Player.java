import java.rmi.Remote;

public interface Player extends Remote{
	public String getName();
	public int getHealthPoints();
	public void setHealthPoints(int healthPoints);
}
