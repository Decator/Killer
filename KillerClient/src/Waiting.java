import javax.swing.JLabel;
import javax.swing.JPanel;

public class Waiting extends JPanel {
	private static Frame frame;
	private JLabel waiting;

	public Waiting(Frame frame) {
		super();
	
		this.setLayout(null);
		this.frame = frame;
	    
		waitingLabel();
	}
	
	public void waitingLabel() {
		this.waiting = new JLabel(frame.getServiceClient().getPlayer().getWaiting());
		this.waiting.setBounds(300, 100, 250, 30);
		this.add(this.waiting);
	}
}
