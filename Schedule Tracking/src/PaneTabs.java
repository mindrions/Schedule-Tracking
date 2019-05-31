import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class PaneTabs extends JTabbedPane {

	public PaneTabs() {

		this.setBackground(Color.DARK_GRAY);

	}

	public static JTextArea createTextArea(String text) {
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.DARK_GRAY);
		JLabel label = new JLabel(text);
		textArea.add(label);
		return textArea;
	}

}
