package Graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JPanel;

import Calculation.Point2D;
import Main.MainClass;
import Main.Program;

public class PaintingPanel extends JPanel {

	private MouseMovingAdapter mouseMoveAdapt;
	private final RectSize rectsize = new RectSize(6, 6);
	private Rectangle2D.Float rectangles[] = new Rectangle2D.Float[5];
	private int[] graphX, lagrGraphY, sqrGraphY;
	private Rectangle workingRectangle = new Rectangle(30, Program.dimension.height - 85, Program.dimension.width - 50,
			10);
	private Rectangle ortRectangle = new Rectangle(0, 0, 
			(workingRectangle.width - workingRectangle.x) / 7,
			-(workingRectangle.height - workingRectangle.y - 70) / 14);

	public PaintingPanel() {
		for (int i = 0; i < rectangles.length; i++) {
			rectangles[i] = generateRectangle(((i + 1) * 20), 40);
		}
		mouseMoveAdapt = new MouseMovingAdapter(rectangles, this);
		addMouseMotionListener(mouseMoveAdapt);
		addMouseListener(mouseMoveAdapt);
	}

	public PaintingPanel(GridLayout gridLayout) {
		super(gridLayout);
		for (int i = 0; i < rectangles.length; i++) {
			rectangles[i] = generateRectangle(((i + 1) * 20)+30, 60);
		}
		mouseMoveAdapt = new MouseMovingAdapter(rectangles, this);
		addMouseMotionListener(mouseMoveAdapt);
		addMouseListener(mouseMoveAdapt);
	}

	// set methods
	public void setLagrGraph(int[] lagrGraph) {
		this.lagrGraphY = lagrGraph;
	}

	public void setSqrGraph(int[] sqrGraph) {
		this.sqrGraphY = sqrGraph;
	}

	public void setGraphX(int[] xg) {
		this.graphX = xg;
	}

	// change the system of coordinates
	public Point2D getMathCoordinates(Rectangle2D.Float p) {

		Point2D newPoint = new Point2D((p.x - workingRectangle.x + rectsize.getWidth() / 2) / ortRectangle.width,
				(workingRectangle.y - p.y - workingRectangle.height + rectsize.getHeight() / 2) / ortRectangle.height);

		return (newPoint);
	}

	public Point getScreenCoordinates(double x, double y) {
		Point p = new Point((int) ((x * ortRectangle.width) + workingRectangle.x),
				(int) (((-y) * ortRectangle.height) - workingRectangle.height + workingRectangle.y));

		return (p);
	}

	// generation points, rectangle
	public ArrayList<Point2D> generatePointCoordinates() {
		ArrayList<Point2D> points = new ArrayList<Point2D>();

		for (Rectangle2D.Float p : rectangles) {
			points.add(getMathCoordinates(p));

		}

		// sort. Change if desired
		Arrays.sort(points.toArray(), new Comparator<Object>() {
			@Override
			public int compare(Object arg0, Object arg1) {
				if (((Point2D) arg0).x > ((Point2D) arg1).x) {
					return 1;
				} else if (((Point2D) arg0).x < ((Point2D) arg1).x) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		return (points);
	}

	public Rectangle2D.Float generateRectangle(float x, float y) {
		return new Rectangle2D.Float(x, y, rectsize.getWidth(), rectsize.getHeight());
	}

	// painting part
	public void paint(Graphics g0) {
		super.paint(g0);

		Graphics2D g = (Graphics2D) g0;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		paintRectangles(g);
		paintAxes(g);
		paintGraphics(g);
		paintSignatureOfGraphicsLines(g);
	}

	public void paintRectangles(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		for (Rectangle2D.Float rec : rectangles) {
			g.fill(rec);
		}
	}

	public void paintAxes(Graphics2D g) {

		Color c = g.getColor();
		g.setColor(Color.BLACK);

		// horizontal
		g.drawLine(workingRectangle.x, workingRectangle.y, workingRectangle.width, workingRectangle.y);

		// vartical
		g.drawLine(workingRectangle.x, workingRectangle.y, workingRectangle.x, workingRectangle.height);

		Rectangle horzNotch = new Rectangle(0, -ortRectangle.width / 10, 1, ortRectangle.width / 10);
		Point textOffset = new Point(-2, horzNotch.height + 12);

		// horizontal nothces
				for (int i = 1; i <= 6; i++) {
					System.out.println();
					g.drawLine((workingRectangle.x + ortRectangle.width * i) + horzNotch.x, (workingRectangle.y + horzNotch.y),
							(workingRectangle.x + ortRectangle.width * i) + horzNotch.width,
							(workingRectangle.y + horzNotch.height));

					g.drawString(Integer.toString(i), (workingRectangle.x + ortRectangle.width * i) + textOffset.x,
							workingRectangle.y + textOffset.y);
				}


		// vertical nothces
		g.drawString("10", workingRectangle.x - 20, workingRectangle.y - ortRectangle.height * 10);

		// arrow x
		Polygon xarrow = new Polygon();
		xarrow.addPoint(workingRectangle.width, workingRectangle.y);
		xarrow.addPoint(workingRectangle.width - 14, workingRectangle.y - 4);
		xarrow.addPoint(workingRectangle.width - 10, workingRectangle.y);
		xarrow.addPoint(workingRectangle.width - 14, workingRectangle.y + 4);

		g.fillPolygon(xarrow);

		// arrow y
		Polygon yarrow = new Polygon();
		yarrow.addPoint(workingRectangle.x, workingRectangle.height);
		yarrow.addPoint(workingRectangle.x - 4, workingRectangle.height + 14);
		yarrow.addPoint(workingRectangle.x, workingRectangle.height + 10);
		yarrow.addPoint(workingRectangle.x + 4, workingRectangle.height + 14);

		g.fillPolygon(yarrow);

		// Hints
		// Y
		g.drawString("Y", workingRectangle.x - 20, workingRectangle.y - ortRectangle.height * 12 + 15);

		// X
		g.drawString("X", workingRectangle.width - 15, workingRectangle.y + 18);

		g.setColor(c);
	}

	public void paintGraphics(Graphics2D g) {
		if (graphX != null && sqrGraphY != null && lagrGraphY != null) {

			System.out.println();
			g.setColor(Color.RED);
			g.drawPolyline(graphX, lagrGraphY, graphX.length);

			g.setColor(Color.ORANGE);
			g.drawPolyline(graphX, sqrGraphY, graphX.length);
		}
	}

	public void paintSignatureOfGraphicsLines(Graphics2D g) {
		Color oldcolor = g.getColor();
		
		g.setColor(Color.RED);
		g.drawLine(workingRectangle.width - 3*workingRectangle.x, workingRectangle.height, (int) (workingRectangle.width -  2*workingRectangle.x), workingRectangle.height);
		g.drawString("ÌÍÊ",(int) (workingRectangle.width -  1.5*workingRectangle.x), workingRectangle.height);
		
		g.setColor(Color.ORANGE);
		g.drawLine(workingRectangle.width - 3*workingRectangle.x, workingRectangle.height+20, (int) (workingRectangle.width -  2*workingRectangle.x), workingRectangle.height+20);
		g.drawString("ËÀÃÐÀÍÆÀ", (int) (workingRectangle.width -  1.5*workingRectangle.x), workingRectangle.height + 20);
		
		g.setColor(oldcolor);
	}
}
