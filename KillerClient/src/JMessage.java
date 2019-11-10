import javax.swing.JLabel;
import javax.swing.JPanel;

public class JMessage extends JPanel {

	public JMessage() {
		super();
	
		this.setLayout(null);
	    
		messageLabel();
	}
	
	public void messageLabel() {
		JLabel message = new JLabel(MainFrame.frame.getServiceClient().getClient().getServerMessage());
		message.setBounds(300, 100, 250, 30);
		this.add(message);
	}
}
