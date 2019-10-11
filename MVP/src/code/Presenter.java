package code;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Presenter implements Ipresenter, Model.LoadDataCallback {
	private View view;
	private Model model;
	JPanel buttonPanel = new JPanel();
	JButton stepButton = new JButton("Step");
	public Presenter(View v)
	{
		view=v;
		model=new Model();
	}
	@Override
    public void call(int x,int y,int size)
    {
		view.showData(x, y, size);
    }
	
	@Override
	public void notify_()
	{
		model.makeOneStep(this);
	}
	
	public void setLimit(int a,int b)
	{
		model.setLimit(a,b);
	}
}
