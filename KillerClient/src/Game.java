import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {
	private static Frame frame;

	public Game(Frame frame) {
		super();
	
		this.setLayout(null);
		this.frame = frame;
	    
		titleLabel();
		infoLabel();
	}
	
	public void titleLabel() {
		JLabel title = new JLabel("Game");
		title.setBounds(375, 100, 250, 30);
		this.add(title);
	}
	
	public void infoLabel() {
		JLabel info = new JLabel(frame.getServiceClient().getPlayer().getName() +" - CurrentPlayer: "+ frame.getServiceClient().getPlayer().getCurrentPlayer());
		info.setBounds(310, 130, 300, 30);
		this.add(info);	
	}
}
