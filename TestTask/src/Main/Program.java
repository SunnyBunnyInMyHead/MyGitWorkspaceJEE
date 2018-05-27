package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

import Calculation.ArraysCalculation;
import Calculation.Point2D;
import Graph.ButtonPanel;
import Graph.PaintingPanel;

public class Program extends Thread {
	public static final Dimension dimension = new Dimension(700, 500);
	private JFrame frame;
	private PaintingPanel paintPanel;
	private ButtonPanel buttonPanel;
	private ArraysCalculation arrCalculation;

	public Program() {
		frame = new JFrame("Graphics");
		paintPanel = new PaintingPanel(new GridLayout());
		buttonPanel = new ButtonPanel();
		arrCalculation = new ArraysCalculation(0, 6, 120);
	}

	ActionListener buttonCalcPressEvent = (ActionEvent e) -> {
		List<Point2D> points = paintPanel.generatePointCoordinates();

		double[] X = new double[points.size()];
		double[] Y = new double[points.size()];
		for (int i = 0; i < points.size(); i++) {
			X[i] = points.get(i).x;
			Y[i] = points.get(i).y;
		}
		arrCalculation.setXYmeanings(X, Y);
		arrCalculation.genYMassivesOfMeanings();

		double[] xgraph = arrCalculation.getxgraph();
		double[] ylaggraph = arrCalculation.getyLagranj();
		double[] ysqrgraph = arrCalculation.getySqrPolinom();

		int[] graphX = new int[xgraph.length];
		int[] graphLagY = new int[xgraph.length];
		int[] graphSqrY = new int[xgraph.length];

		for (int i = 0; i < xgraph.length; i++) {
			graphX[i] = paintPanel.getScreenCoordinates(xgraph[i], ylaggraph[i]).x;
			graphLagY[i] = paintPanel.getScreenCoordinates(xgraph[i], ylaggraph[i]).y;
			graphSqrY[i] = paintPanel.getScreenCoordinates(xgraph[i], ysqrgraph[i]).y;
		}

		paintPanel.setLagrGraph(graphLagY);
		paintPanel.setSqrGraph(graphSqrY);
		paintPanel.setGraphX(graphX);
		paintPanel.repaint();
	};

	@Override
	public void run() {

		paintPanel.setDoubleBuffered(true);
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.add(paintPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(dimension);

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		buttonPanel.addButtonListener(buttonCalcPressEvent);
	}

}
