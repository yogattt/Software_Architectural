package code;

import java.util.Observable;

public class Model extends Observable {
	final int BALL_SIZE = 20;
	int xPosition = 0;
	int yPosition = 0;
	int xLimit, yLimit;
	int xDelta = 6;
	int yDelta = 4;
	void makeOneStep(final LoadDataCallback callback) {
	xPosition += xDelta;
	if (xPosition < 0) {
	xPosition = 0;
	xDelta = -xDelta;
	}
	if (xPosition >= xLimit) {
	xPosition = xLimit;
	xDelta = -xDelta;
	}
	yPosition += yDelta;
	if (yPosition < 0 || yPosition >= yLimit) {
	yDelta = -yDelta;
	yPosition += yDelta;
	}
	callback.call(xPosition,yPosition,BALL_SIZE);
	notifyObservers();
	}
	public void setLimit(int a,int b)
	{
		xLimit=a-BALL_SIZE;
		yLimit=b-BALL_SIZE;
	}
	public interface LoadDataCallback {
		void call(int x,int y,int size);
	}
}
