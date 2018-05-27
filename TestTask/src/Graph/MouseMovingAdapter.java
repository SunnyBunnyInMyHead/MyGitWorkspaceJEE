package Graph;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import Main.MainClass;
import Main.Program;

class MouseMovingAdapter extends MouseAdapter {

	private int x;
	private int y;
	private Rectangle2D.Float rectangles[];
	private PaintingPanel jpanel;
	private int maxX = Program.dimension.width - 10;
	private int maxY = Program.dimension.height - 70;
	private Rectangle2D.Float holdRectangle;
	public MouseMovingAdapter(Rectangle2D.Float rectangles[], PaintingPanel jp) {
		this.rectangles = rectangles;
		jpanel = jp;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseReleased(e);
		holdRectangle = null;
	}

	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();

	}

	public void mouseDragged(MouseEvent e) {
		int dx = e.getX() - x;
		int dy = e.getY() - y;
		if (holdRectangle == null) {
			for (Rectangle2D.Float rectangle : rectangles) {
				if (rectangle.getBounds2D().contains(x, y)) {
					holdRectangle = rectangle;
					moveRectangle(rectangle, dx, dy);
					jpanel.repaint();
				}
			}
		} else {
			moveRectangle(holdRectangle, dx, dy);
			jpanel.repaint();
		}

		x += dx;
		y += dy;
	}
	
	private void moveRectangle(Rectangle2D.Float rectangle, int dx, int dy) {
		if (x < (rectangle.getWidth() / 2)) {
			rectangle.x = (float) (rectangle.getWidth() / 2);
		} else if (x > maxX) {
			rectangle.x = (float) (maxX - rectangle.getWidth());
		} else {
			rectangle.x += dx;
		}
		if (y < (rectangle.getHeight() / 2)) {
			rectangle.y = (float) rectangle.getHeight() / 2;
		} else if (y > maxY) {
			rectangle.y = (float) (maxY - rectangle.getHeight());
		} else {
			rectangle.y += dy;
		}
	}
}
