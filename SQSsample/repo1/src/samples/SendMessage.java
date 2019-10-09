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

public class SendMessage extends JFrame {
	
	
	void initWindow()
	{
		this.setTitle("SendMessage");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		Container contentPane = this.getContentPane();
	    JPanel jPanel = new JPanel();
	    jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
	    jPanel.add(new Label("发送方式:"));
	    
	    dx1=new JRadioButton("点对点");
        dx2=new JRadioButton("发布-订阅");
        ButtonGroup dxz;
        dxz=new ButtonGroup();
        dxz.add(dx1);
        dxz.add(dx2);
	    jPanel.add(dx1);
	    jPanel.add(dx2);
	    contentPane.add(jPanel);
	    
	    JPanel jPanel2 = new JPanel();
	    jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.X_AXIS));
	    jPanel2.add(new Label("队列URL:"));
	    URL=new  TextField(50);
	    jPanel2.add(URL);
	    contentPane.add(jPanel2);
	    
	    contentPane.add(new Label("发送消息框"));
	    message=new TextArea(3,1);
	    contentPane.add(message);
	    
	    contentPane.add(new Label("结果显示框"));
	    result=new TextArea();
	    result.setEditable(false);
	    contentPane.add(result);
	    
	    BSearch= new JButton("查询队列");
	    Send_Mess= new JButton("发送消息");
	    BSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Search();
            }
        });
	    Send_Mess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage(message.getText());
            }
        });
	    JPanel jPanel3 = new JPanel();
	    jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.X_AXIS));
	    jPanel3.add(BSearch);
	    jPanel3.add(Box.createHorizontalGlue());
	    jPanel3.add(Send_Mess);
	    contentPane.add(jPanel3);
	    this.setSize(400, 400);

	    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) screensize.getWidth() / 2 - this.getWidth() / 2;
	    int y = (int) screensize.getHeight() / 2 - this.getHeight() / 2;	
	    this.setLocation(x, y);
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
	void sendMessage(String var)
	{
		if(var.length()==0)
		{
			result.append("消息不能为空\n");
			return;
		}
		Boolean way;
		myQueueUrl=URL.getText();
		if(myQueueUrl.length()==0)
		{
			result.append("队列URL不能为空\n");
			return;
		}
		if(dx1.isSelected())
			way=true;
		else if(dx2.isSelected())
			way=false;
		else
			{
				result.append("请选择发送方式");
				return;
			}
		final Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
		if (way==true)
			messageAttributes.put("Type", new MessageAttributeValue()
			        .withDataType("String")
			        .withStringValue("P2P"));
		else
			messageAttributes.put("Type", new MessageAttributeValue()
			        .withDataType("String")
			        .withStringValue("ALL"));
		result.append("Sending a message to Queue.\n");
		try {
			final SendMessageRequest sendMessageRequest = new SendMessageRequest();
			sendMessageRequest.withMessageBody(var);
			sendMessageRequest.withQueueUrl(myQueueUrl);
			sendMessageRequest.withMessageAttributes(messageAttributes);
			SendMessageResult cur=sqs.sendMessage(sendMessageRequest);
			result.append("SendMessage succeed with messageId " +cur.getMessageId()+"\n");
			result.append("-----------------------------------------------------\n");
			message.setText("");
		}catch(Exception e)
		{
			result.append(e.getMessage());
			return;
		}
	}
	JRadioButton dx1,dx2;
	TextField URL;
	TextArea message,result;
	JButton BSearch ,Send_Mess;
	ProfileCredentialsProvider credentialsProvider;
	AmazonSQS sqs;
	String myQueueUrl;
	
	 public static void main(String[] args) throws Exception {
		 SendMessage win=new SendMessage();
		 win.initWindow();
	    }
	}
