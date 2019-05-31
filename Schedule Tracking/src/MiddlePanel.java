import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MiddlePanel extends JPanel {

	public MiddlePanel() {

		PaneTabs topTabs;

		this.setLayout(new BorderLayout());
		Dimension d = new Dimension(750, 300);

		this.setPreferredSize(d);
		this.setBackground(Color.DARK_GRAY);
		this.setBorder(BorderFactory.createBevelBorder(1));

		topTabs = new PaneTabs();
		topTabs.setBackground(Color.DARK_GRAY);
		topTabs.setForeground(Color.WHITE);
		topTabs.setBorder(BorderFactory.createBevelBorder(1));

		topTabs.addTab("Week 1", PaneTabs.createTextArea("1"));
		topTabs.addTab("Week 2", PaneTabs.createTextArea("2"));
		topTabs.addTab("Week 3", PaneTabs.createTextArea("3"));
		topTabs.addTab("Week 4", PaneTabs.createTextArea("4"));

		this.add(topTabs);

	}

}
