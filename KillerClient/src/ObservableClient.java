
import java.util.Observable;

public class ObservableClient extends Observable {
	public void notifyFrame(String s) {
        setChanged();
        notifyObservers(s);
	}
}
