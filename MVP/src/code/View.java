package code;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

public class View  extends Applet implements Observer{
	JPanel buttonPanel = new JPanel();
	JButton stepButton = new JButton("Step");
	Canvas canvas;
	int stepNumber = 0;
	int xPosition,yPosition,BALL_SIZE;
	Ipresenter pre;
	
	public void showData(int x,int y,int size)
	{
		xPosition=x;
		yPosition=y;
		BALL_SIZE=size;
	}
	public void update(Observable obs, Object arg) {
		repaint();
		}
	public void init() 
	{
		canvas=new Canvas() {
			public void paint(Graphics g) {
				g.setColor(Color.red);
				g.fillOval(xPosition, yPosition, BALL_SIZE,BALL_SIZE);
				};
		};
		canvas.setSize(300,400);
		pre=new Presenter(this);
		setLayout(new BorderLayout());
		buttonPanel.add(stepButton);
		this.add(BorderLayout.SOUTH, buttonPanel);
		this.add(BorderLayout.CENTER, canvas);
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				pre.setLimit(canvas.getSize().width,canvas.getSize().height);
				pre.notify_();
				canvas.repaint();
				showStatus("Step " + (stepNumber++) + ", x = "
						+ xPosition + ", y = " + yPosition);
			}
		});
	 }
	public void start() {
	 pre.notify_();
	 repaint();
	 } 
}
