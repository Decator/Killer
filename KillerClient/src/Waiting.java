import javax.swing.JLabel;
import javax.swing.JPanel;

public class Waiting extends JPanel {
	private static Frame frame;

	public Waiting(Frame frame) {
		super();
	
		this.setLayout(null);
		this.frame = frame;
	    
		waitingLabel();
	}
	
	public void waitingLabel() {
		JLabel waiting = new JLabel(frame.getServiceClient().getPlayer().getWaiting());
		waiting.setBounds(300, 100, 250, 30);
		this.add(waiting);
	}
}
