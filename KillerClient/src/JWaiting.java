import javax.swing.JLabel;
import javax.swing.JPanel;

public class JWaiting extends JPanel {

	public JWaiting() {
		super();
	
		this.setLayout(null);
	    
		waitingLabel();
	}
	
	public void waitingLabel() {
		JLabel waiting = new JLabel(Frame.frame.getServiceClient().getKiller().getWaiting());
		waiting.setBounds(300, 100, 250, 30);
		this.add(waiting);
	}
}
