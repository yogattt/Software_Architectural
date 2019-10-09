package code;
import java.awt.List;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Business_Logic extends JFrame{
	public void initWindow()
	{
		this.setSize(400, 400);
		this.setTitle("Business_Logic");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		recv=new TextArea();
		recv.setEditable(false);
		this.add(recv);
		init();
		t.start();
		this.setVisible(true);
	}
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
	}
	public void operation(BufferedReader bufferedReader)
	{
		int temp=0;
		try {
			temp=Integer.parseInt(bufferedReader.readLine());
			switch (temp)
			{
			case 0:context="[�ɽ����(Ԫ)]";break;
			case 1:context="[������(%)]";break;
			case 2:context="[���̼�(Ԫ)],[��߼�(Ԫ)],[��ͼ�(Ԫ)],[���̼�(Ԫ)] ";break;
			case 3:context="[�ǵ���(%)] ";break;
			}
			start="\'"+bufferedReader.readLine()+"\'";
			end="\'"+bufferedReader.readLine()+"\'";
			rs=DAL.search(context, start, end);
			recv.append("��ѯ����"+context+"\n"
			+"��ֹ���ڣ� "+start+" --- "+end+"\n");
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
			op.write(cur.getBytes());                               //д��������
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i=1;i<=num;i++)
				op.write((rsmd.getColumnName(i)+"\n").getBytes());  //д������
			for(String x:mes)
				op.write((x+"\n").getBytes());                             //д������
			socketSend1.shutdownOutput();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Business_Logic BLL=new Business_Logic ();
		BLL.initWindow();
		}
	ServerSocket socketRecive1;
	Socket socketSend1;
	Thread t;
	String context;
    String start,end;
    ResultSet rs;
    Database_Access_Layer DAL;
    TextArea recv;
}
