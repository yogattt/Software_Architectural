import code.EmailService;
import code.EmailServiceService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class emailClient extends JFrame {
    TextField url;
    TextArea context;
    EmailServiceService ESS;
    EmailService ES;
    Button Send;
    emailClient(){
        initWin();
        this.setVisible(true);
    }

    void initWin()
    {
        ESS=new EmailServiceService();
        ES=ESS.getEmailServicePort();
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        Container contentPane = this.getContentPane();
        contentPane.add(new Label("the aiming address"));
        url=new TextField();
        contentPane.add(url);
        contentPane.add(new Label("the context"));
        context=new TextArea();
        contentPane.add(context);
        Send=new Button("send");
        Send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(url.getText().length()==0)
                {
                    context.setText("false");
                    return;
                }
                if(ES.sendEmail(url.getText(),context.getText()))
                    context.setText("true");
                else
                    context.setText("false");
            }
        });
        contentPane.add(Send);
        this.setSize(400,300);
    }
    static public void main(String[] args)
    {
        emailClient C=new emailClient();
    }
}
