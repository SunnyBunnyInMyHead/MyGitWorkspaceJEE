package graph;

public class RectSize {
	private int height;
	private int width;
	
	public RectSize() {
		height =0;
		width = 0;
	}
	public RectSize(int width,int height) {
		setHeight(height);
		setWidth(width);
		
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
