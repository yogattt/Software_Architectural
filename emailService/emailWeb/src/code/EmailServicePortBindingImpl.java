package code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailServicePortBindingImpl implements code.EmailService{
	EmailDispach ED=new EmailDispach();
    public boolean validateEmailAddress(java.lang.String arg0) throws java.rmi.RemoteException {
    	String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(rule);
        matcher = pattern.matcher(arg0);
        return matcher.matches();
    }

    public boolean sendEmail(java.lang.String url, java.lang.String payload) throws java.rmi.RemoteException {
    	String []adds=url.split(",");
    	String cur="";
     	boolean tag1=true,tag2=true;
     	for(String s :adds)
     	{
     		if(validateEmailAddress(s))
     			cur+=s+",";
     		else
     			tag1=false;
     	}
     	if(cur.length()>1)
     		tag2=ED.sendEmail(cur,payload);
    	return tag1&&tag2;
    }

    public boolean sendEmailBatch(java.lang.String[] arg0, java.lang.String arg1) throws java.rmi.RemoteException {
    	String cur="";
    	boolean tag1=true,tag2=true;
    	for(String s :arg0)
    	{
    		if(validateEmailAddress(s))
    			cur+=s+",";
    		else
    			tag1=false;
    	}
    	if(cur.length()>1)
    		tag2=sendEmail(cur,arg1);
        return tag1&&tag2;
    }

}
