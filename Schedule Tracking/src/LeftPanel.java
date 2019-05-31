import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class LeftPanel extends JPanel {

	// public Dimension dim = getPreferredSize();

	public Border border;

	public LeftPanel() {
		this.setPreferredSize(new Dimension(100, 300));
		this.setBackground(Color.DARK_GRAY);

		border = BorderFactory.createBevelBorder(1);
		this.setBorder(border);

		// TODO
		// Split left panel into 12 buttons that are just the months.
		// Upon clicking each button, the center panel displays the an amount
		// of tabs corresponding to the number of weeks in that month.

	}

}
