package code;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Business_Logic{
	private static final long serialVersionUID = 1L;
	public void init()
	{
		t = new Thread(new Runnable(){  
            public void run(){  
            	try {
            		socketRecive1=new ServerSocket(48651);
            		while(true) {
            			socketSend1=socketRecive1.accept();
            			InputStream inputStream=socketSend1.getInputStream();
            			InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            			BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            			operation(bufferedReader);
            		}
            	}catch (IOException e) {
            			e.printStackTrace();
            		}
            }
         });
		DAL=new Database_Access_Layer();
		t.start();
	}
	public void operation(BufferedReader bufferedReader)
	{
		int temp=0;
		try {
			temp=Integer.parseInt(bufferedReader.readLine());
			switch (temp)
			{
			case 0:context="[成交金额(元)]";break;
			case 1:context="[换手率(%)]";break;
			case 2:context="[开盘价(元)],[最高价(元)],[最低价(元)],[收盘价(元)] ";break;
			case 3:context="[涨跌幅(%)] ";break;
			}
			start="\'"+bufferedReader.readLine()+"\'";
			end="\'"+bufferedReader.readLine()+"\'";
			rs=DAL.search(context, start, end);
			doResult(rs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doResult(ResultSet rs)
	{
		try {
			int num=rs.getMetaData().getColumnCount();
			ArrayList<String> mes=new ArrayList();
			while(rs.next())
				for(int i=0;i<num;i++)
					mes.add(rs.getString(i+1));
			OutputStream op=socketSend1.getOutputStream();
			String cur=String.valueOf(num)+"\n";
			op.write(cur.getBytes());                               //写入有列数
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i=1;i<=num;i++)
				op.write((rsmd.getColumnName(i)+"\n").getBytes());  //写入列名
			for(String x:mes)
				op.write((x+"\n").getBytes());                             //写入内容
			socketSend1.shutdownOutput();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Business_Logic BLL=new Business_Logic ();
		BLL.init();
		}
	ServerSocket socketRecive1;
	Socket socketSend1;
	Thread t;
	String context;
    String start,end;
    ResultSet rs;
    Database_Access_Layer DAL;
}
