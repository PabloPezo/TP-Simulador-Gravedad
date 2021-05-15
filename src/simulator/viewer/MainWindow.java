package simulator.viewer;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import simulator.control.Controller;

public class MainWindow extends JFrame 
{
	private static final long serialVersionUID = 1L;
	Controller _ctrl;
	public MainWindow(Controller ctrl) 
	{
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}

	private void initGUI() 
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		mainPanel.add(contentPanel, BorderLayout.CENTER);


		ControlPanel ctrlPanel = new ControlPanel(_ctrl);

		BodiesTable bodiesTable = new BodiesTable(_ctrl);

		Viewer universeViewer = new Viewer(_ctrl);

		StatusBar statusBar = new StatusBar(_ctrl);

		mainPanel.add(ctrlPanel, BorderLayout.PAGE_START);
		mainPanel.add(statusBar, BorderLayout.PAGE_END);

		bodiesTable.setPreferredSize(new Dimension(800, 300));
		contentPanel.add(bodiesTable);

		universeViewer.setPreferredSize(new Dimension(800, 600));
		contentPanel.add(new JScrollPane(universeViewer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		mainPanel.setVisible(true);

		this.setLocation(400,3);
		this.setVisible(true);
		this.pack();
	}
}