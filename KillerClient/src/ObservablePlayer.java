import java.util.Observable;

public class ObservablePlayer extends Observable {
	public void notifyFrame() {
        setChanged();
        notifyObservers();
	}
}
