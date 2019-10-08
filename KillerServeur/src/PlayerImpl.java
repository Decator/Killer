
public class PlayerImpl implements Player {
  
  private String name;
  private int healthPoints;
  
  public PlayerImpl(String name) {
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
