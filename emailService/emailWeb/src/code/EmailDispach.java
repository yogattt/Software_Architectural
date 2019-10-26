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
            //���Ը�����ռ��˷����ʼ����ռ���֮���ö��ŷֿ����������Ž���ʹ��BatchSendMailRequest��ʽ
            //request.setToAddress("����1,����2");
            request.setSubject("����");
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


