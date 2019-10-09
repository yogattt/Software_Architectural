package code;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import javax.swing.*;

import draw.pressentation_model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class Presentation_Layer extends JFrame {
    void initWindow()
    {
        this.setTitle("�ַ����й�Ʊ����");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        Container contentPane = this.getContentPane();
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.X_AXIS));
        jPanel1.add(new Label("ѡ���ѯ����"));
        choice = new Choice();
        choice.add("�ɽ��ܶ�");
        choice.add("ת����");
        choice.add("��Ʊ�۸�");
        choice.add("�ǵ���");
        jPanel1.add(choice);
        contentPane.add(jPanel1);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.X_AXIS));

        jPanel2.add(new Label("����"));
        year1=new JTextField(10);
        year1.setHorizontalAlignment(JTextField.RIGHT);
        year1.setMaximumSize(new Dimension(10,40));
        jPanel2.add(year1);
        jPanel2.add(new Label("��"));
        month1=new JTextField(10);
        month1.setHorizontalAlignment(JTextField.RIGHT);
        month1.setMaximumSize(new Dimension(50,40));
        jPanel2.add(month1);
        jPanel2.add(new Label("��"));
        day1=new JTextField(10);
        day1.setHorizontalAlignment(JTextField.RIGHT);
        day1.setMaximumSize(new Dimension(50,40));
        jPanel2.add(day1);
        jPanel2.add(new Label("��"));
        jPanel2.add(new Label("��"));
        year2=new JTextField(10);
        year2.setMaximumSize(new Dimension(50,40));
        year2.setHorizontalAlignment(JTextField.RIGHT);
        jPanel2.add(year2);
        jPanel2.add(new Label("��"));
        month2=new JTextField(10);
        month2.setHorizontalAlignment(JTextField.RIGHT);
        month2.setMaximumSize(new Dimension(50,40));
        jPanel2.add(month2);
        jPanel2.add(new Label("��"));
        day2=new JTextField(10);
        day2.setHorizontalAlignment(JTextField.RIGHT);
        day2.setMaximumSize(new Dimension(50,40));
        jPanel2.add(day2);
        jPanel2.add(new Label("��"));
        jPanel2.add(Box.createHorizontalGlue());
        Search= new JButton("��ѯ");
        Search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Search();
                }
            });
        jPanel2.add(Search);
        contentPane.add(jPanel2);
        this.setSize(600, 200);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    void send()
    {
        try {
        	Socket socket= new Socket(host, port);
        	OutputStream outputStream= socket.getOutputStream();
			InputStream inputStream=socket.getInputStream();
			outputStream.write(message.getBytes());
			socket.shutdownOutput();
			recive(inputStream);
			socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    void recive(InputStream inputStream)
    {
    	byte[] bytes = new byte[1024];
        int len;
        StringBuilder result = new StringBuilder();
    	try {
			while ((len = inputStream.read(bytes)) != -1) {
			      // ע��ָ�������ʽ�����ͷ��ͽ��շ�һ��Ҫͳһ
			      result.append(new String(bytes, 0, len));
			    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String Result=result.toString();
    	show(Result);
    }
    public void show(String Result)
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(Result.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));  
		String line;
		int num=0;  //�������
		try {
			num=Integer.parseInt(br.readLine());
			String name[]=new String[num];
			for(int i=0;i<num;i++)
				name[i]=br.readLine();       //�������
			ArrayList<ArrayList<String>> queue= new ArrayList<ArrayList<String>>();
			for(int i=0;i<num;i++)
				queue.add(new ArrayList<String>());
			int index=0;
			while((line=br.readLine())!=null)
			{
				queue.get(index).add(line);
				index=(index+1)%num;
			}
			String Yname="";
			
			if(num>2)
				Yname="�۸�";
			else
				Yname=name[0];
			pressentation_model pic=new pressentation_model(Yname,num,name,queue);
			JFrame cur=new JFrame();
	        cur.add(pic.getChartPanel());
	        cur.setSize(1200, 600);
	        cur.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void Search()
    {
    	if(year1.getText().length()!=4 ||month1.getText().length()!=2||day1.getText().length()!=2
    			||year2.getText().length()!=4 ||month2.getText().length()!=2||day2.getText().length()!=2)
    	{
    		JOptionPane.showMessageDialog(null, "��ʾ:���ڵĸ�ʽΪyyyy-MM-dd\n����������");
    		return;
    	}
    	message=String.valueOf(choice.getSelectedIndex())+"\n";  //��ѯ����
    	message+=year1.getText()+"-"+month1.getText()+'-'+day1.getText()+
    			"\n"+year2.getText()+"-"+month2.getText()+'-'+day2.getText(); //��ѯʱ��ڵ�
    	send();
    }
    public static void main(String[] args)
    {
        Presentation_Layer c=new Presentation_Layer();
        c.initWindow();

    }
    String message;
    JButton Search;
    Choice choice;
    JTextField year1,month1,day1,year2,month2,day2;
    TextArea result;
    int port=48651;
    String host="localhost";
}