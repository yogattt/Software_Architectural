package code;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
//import com.aliyuncs.http.MethodType;
public class EmailDispach {
    private IClientProfile profile;
    private  IAcsClient client;
    public EmailDispach(){ init();};
    public void init() {
        profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FrW3k9xRuaPumpxcGuF", "g3w0oBDL1k7yqRJSyPC1Y5dXoyjKM1");
        client = new DefaultAcsClient(profile);
    }
    public boolean sendEmail(String url,String contest) {
        try {
            SingleSendMailRequest request = new SingleSendMailRequest();
            request.setProtocol(com.aliyuncs.http.ProtocolType.HTTPS);
            request.setAccountName("sa@email.cugttt.cn");
            request.setAddressType(1);
            request.setReplyToAddress(true);
            request.setToAddress(url);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject("测试");
            request.setHtmlBody(contest);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
            return true;
        } catch (ServerException e) {
            e.printStackTrace();
            return false;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }
}


