package vscode.gui;

import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.Toolkit;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -232216577877170594L;
	private static final ImageIcon amogusIcon = new ImageIcon(MainFrame.class.getResource("/vscode/assets/amogus.ico"));
	private static final Image MCU_IMAGE = Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/vscode/assets/mcu.ico"));
	public static final int MIN_WIDTH = 1;
	private JPanel contentPane;
	public JSlider potentiometer;
	public JTextArea mousePointerArea;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private class FrameListen extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent ev) {
			int height = potentiometer.getHeight();
			int width = (int) (0.86 * getWidth());
			potentiometer.setMaximumSize(new Dimension(width, height));
		}
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setIconImage(MCU_IMAGE);
		setTitle("SerialGUI\r\n");
		setSize(new Dimension(580, 600)); // Blah
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 5));
		addComponentListener(new FrameListen());
		JPanel MouseCoordinatePanel = new JPanel();
		Border bevel = new BevelBorder(BevelBorder.LOWERED);
		Border title = new TitledBorder(bevel, "Placeholder", TitledBorder.CENTER, TitledBorder.ABOVE_TOP);
		MouseCoordinatePanel.setBorder(title);
		contentPane.add(MouseCoordinatePanel);
		MouseCoordinatePanel.setLayout(new BoxLayout(MouseCoordinatePanel, BoxLayout.X_AXIS));
		
		mousePointerArea = new JTextArea();
		mousePointerArea.setBackground(UIManager.getColor("MenuItem.selectionBackground"));
		mousePointerArea.setEnabled(false);
		mousePointerArea.setEditable(false);
		mousePointerArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		MouseCoordinatePanel.add(mousePointerArea);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel(), panel_1 = new JPanel();
		tabbedPane.addTab("Controls", amogusIcon, panel, null);
		tabbedPane.addTab("Settings", panel_1);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		potentiometer = new JSlider();
		potentiometer.setMaximumSize(new Dimension((int) (getWidth() * 0.86), 60));
		potentiometer.setMaximum(255);
		potentiometer.setMajorTickSpacing(15);
		potentiometer.setMinorTickSpacing(3);

		potentiometer.setPaintTicks(true);
		potentiometer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		potentiometer.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setPotentiometerLabels();
		potentiometer.setPaintLabels(true);
		
		Border potBevel = new BevelBorder(BevelBorder.LOWERED), potTitle;
		potTitle = new TitledBorder(potBevel, "Throttle", TitledBorder.LEADING, TitledBorder.TOP);
		potentiometer.setBorder(potTitle);
		panel.add(potentiometer);
		
	}
	
	private void setPotentiometerLabels() {
		Hashtable<Integer, JLabel> table = new Hashtable<>(3);
		final int MAX = potentiometer.getMaximum();
		table.put(potentiometer.getMinimum(), new JLabel("slag"));
		table.put(MAX / 2, new JLabel("shmooves"));
		table.put(MAX, new JLabel("ZOOM"));
		potentiometer.setLabelTable(table);
	}

}
