import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	final static boolean RIGHT_TO_LEFT = false;
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	public static String[] allColors = new String[] { "RED", "GREEN", "BLUE", "YELLOW", "CYAN", "MAGENTA", "WHITE",
			"BLACK", "GRAY", "LIGHT_GRAY", "DARK_GRAY", "ORANGE", "PINK" };
	// to avoid issues with static references to non static methods
	// need to find better solution to this issues as public variables aren't secure
	public static RightPanel rightPanel = new RightPanel();
	public static MiddlePanel middlePanel = new MiddlePanel();
	public static LeftPanel leftPanel = new LeftPanel();

	public static void addComponentsToPane(Container pane) {

		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		// Border border;
		// PaneTabs topTabs;
		BottomRightPanel bottomRightPanel;
		// JPanel panelText;
		JMenuBar menuBar;
		Insets i;

		pane.setLayout(new GridBagLayout());
		pane.setBackground(Color.DARK_GRAY);

		GridBagConstraints c = new GridBagConstraints();

		if (shouldFill) {
			c.fill = GridBagConstraints.HORIZONTAL;
		}

//---------------------------------------------------------//
// LEFT PANEL \\

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 2;

		// top, left, bottom, right
		i = new Insets(0, 10, 0, 10);
		// c.insets = i;
		pane.add(leftPanel, c);

//---------------------------------------------------------//
// RIGHT PANEL \\

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		c.gridheight = 1;

		i = new Insets(0, 10, 0, 10);
		c.insets = i;
		pane.add(rightPanel, c);

		rightPanel.addSelection("RED", "COMMUTE", false);
		rightPanel.addSelection("BLUE", "WORK", false);
		rightPanel.addSelection("GREEN", "SLEEP", false);

		// TODO
		// Does not limit amount of rows: DOES NOT WORK

//---------------------------------------------------------//
// BOTTOM RIGHT PANEL \\

		bottomRightPanel = new BottomRightPanel();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;

		i = new Insets(10, 10, 0, 10);
		c.insets = i;
		pane.add(bottomRightPanel, c);

//---------------------------------------------------------//
// MIDDLE PANEL \\

		middlePanel = new MiddlePanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 2;

		i = new Insets(0, 10, 0, 5);
		c.insets = i;
		pane.add(middlePanel, c);

//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//

//		menuBar = new JMenuBar();
//		panelText.add(menuBar, BorderLayout.NORTH);

//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//

//---------------------------------------------------------//

	}

	public static String backToString(Color color) {
		Color colorTemp = Color.BLACK;
		String colorHere = "BLACK";

		for (int i = 0; i < allColors.length; i++) {

			colorHere = allColors[i];

			try {
				colorTemp = (Color) Color.class.getField(colorHere).get(null);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (color == colorTemp) {
				return colorHere;
			}

		}

		return colorHere;

	}

	private static void createAndShowGUI() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		JFrame frame = new JFrame("Schedule Tracker");
		frame.setSize(screenSize);

		addComponentsToPane(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		System.out.println("Hello");
		
		//remove try catch block if you want to revert to default lookandfeel
//		try {
//		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//		        if ("Nimbus".equals(info.getName())) {
//		            UIManager.setLookAndFeel(info.getClassName());
//		            break;
//		        }
//		    }
//		} catch (Exception e) {
//		    // If Nimbus is not available, you can set the GUI to another look and feel.
//		}
		
		//if using App.java to run, remove surrounding runnable from createAndShowGUI()
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}