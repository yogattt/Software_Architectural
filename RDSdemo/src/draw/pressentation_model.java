package draw;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class pressentation_model {
	ChartPanel frame1;
	JFreeChart jfreechart;
	public pressentation_model() {}
	public pressentation_model(String Yname,int num,String[] name,ArrayList<ArrayList<String>> queue){
		XYDataset xydataset = createDataset(num,name,queue);
		jfreechart = ChartFactory.createTimeSeriesChart("浦发银行股票走势", "日期", Yname,xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
        frame1=new ChartPanel(jfreechart,true);
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
 
	}
	 private static XYDataset createDataset(int num,String[] name,ArrayList<ArrayList<String>> queue) {
		 
	        TimeSeries timeseries[] = new TimeSeries[num-1];
	        for(int i=0;i<num-1;i++)
	        	timeseries[i]=new TimeSeries(name[i],org.jfree.data.time.Day.class);
	        int len=queue.get(0).size();
	        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
	        for(int i=0;i<len;i++)
	        {
				try {
					Date cur = formatter.parse(queue.get(num-1).get(i));
					Calendar calendar = Calendar.getInstance();
	        		calendar.setTime(cur);
		        	for(int j=0;j<num-1;j++)
		        	{
		        		if(queue.get(j).get(i).equals("N/A"))
		        			continue;
		        		timeseries[j].add(
		        				new Day(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR)),
		        				Double.valueOf(queue.get(j).get(i)));
		        	}
				} catch (ParseException e) {
					e.printStackTrace();
				}
	        }
	        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
	        for(int i=0;i<num-1;i++)
	        	timeseriescollection.addSeries(timeseries[i]);
	        return timeseriescollection;
	    }
	  public ChartPanel getChartPanel(){
	    	return frame1;
	    }
	  public JFreeChart getjfreechart()
	  {
		  return jfreechart;
	  }
}
