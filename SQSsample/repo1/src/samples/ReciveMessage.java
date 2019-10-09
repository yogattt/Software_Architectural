package samples;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

public class ReciveMessage extends JFrame {
	
	
	void initWindow()
	{
		this.setTitle("Recive");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Container contentPane = this.getContentPane();    
	    JPanel jPanel2 = new JPanel();
	    jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.X_AXIS));
	    jPanel2.add(new Label("队列URL:"));
	    URL=new  TextField(50);
	    jPanel2.add(URL);
	    contentPane.add(jPanel2);
	    
	    contentPane.add(new Label("消息显示框"));
	    result=new TextArea();
	    result.setEditable(false);
	    contentPane.add(result);
	    
	    BSearch= new JButton("查询队列");
	    Recive= new JButton("开始接收消息");
	    BSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Search();
            }
        });
	    Recive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReciveMessage();
            }
        });
	    JPanel jPanel3 = new JPanel();
	    jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.X_AXIS));
	    jPanel3.add(BSearch);
	    jPanel3.add(Box.createHorizontalGlue());
	    jPanel3.add(Recive);
	    contentPane.add(jPanel3);
	    this.setSize(400, 400);

	    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(50, 50);
	    this.setResizable(false);
	    this.pack();
	    initSQS();
		this.setVisible(true);
	}
	void initSQS()
	{
		credentialsProvider = new ProfileCredentialsProvider();
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
            result.append("Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (~\\.aws\\credentials), and is in valid format.");
        }
        sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.US_WEST_2)
                .build();
        message_ID = new ArrayList();
        t = new Thread(new Runnable(){  
            public void run(){  
           // run方法具体重写
            	while(true) {
            		myQueueUrl=URL.getText();
            		messages= sqs.receiveMessage(
            			    new ReceiveMessageRequest(myQueueUrl)
            			        .withMessageAttributeNames("Type")
            			        .withWaitTimeSeconds(5)
            			).getMessages();
                    for (Message message : messages) {
                    	if(message_ID.contains(message.getMessageId()))  //忽略已经接收的消息。
                    		continue;
                    	message_ID.add(message.getMessageId());
                    	result.append("  Message\n");
                    	result.append("    MessageId:     " + message.getMessageId()+"\n");
                    	result.append("    Body:          " + message.getBody()+"\n");
                    	if(message.getMessageAttributes().get("Type").getStringValue().equals("P2P"))  //点对点方式，收到消息后删除；
                    	{
                    		// Delete a message
                    		result.append("the queue is deleting this message(P2P type).\n");
                            sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl,message.getReceiptHandle()));
                    	}
                    }
            	}
            }
            });
        result.append("-----------------------------------------------------\n");
        result.append("Getting Started with Amazon SQS\n");
        result.append("-----------------------------------------------------\n");
	}
	void Search()
	{
		result.append("正在查询......\n");
		ListQueuesResult lq_result = sqs.listQueues();
		result.append("Your SQS Queue URLs:\n");
		for (String url : lq_result.getQueueUrls()) {
			result.append(url+"\n");
		}
		result.append("-----------------------------------------------------\n");
	}
	void ReciveMessage()
	{
		if(flag==1)
		{
			myQueueUrl=URL.getText();
			if(myQueueUrl.length()==0)
			{
				result.append("队列URL不能为空\n");
				return;
			}
			flag=0;
			Recive.setText("停止接收消息");
			try{
				t.start();
				result.append("开始接收消息\n");
			}catch(Exception e)
			{
				t.resume();;
			}
		}
		else
		{
			flag=1;
			Recive.setText("开始接收消息");
			t.suspend();;

		}
		
	}
	JButton BSearch,Recive;
	TextField URL;
	TextArea result;
	ProfileCredentialsProvider credentialsProvider;
	AmazonSQS sqs;
	List<Message> messages;
	List message_ID;
	String myQueueUrl;
	Thread t;
	int flag=1;
	 public static void main(String[] args) throws Exception {
		 ReciveMessage win=new ReciveMessage();
		 win.initWindow();
	    }
	}
