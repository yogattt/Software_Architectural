package code;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("RestService")
public class RestEmail {
 EmailDispach ED=new EmailDispach();
 @POST
 @Path("send")
 @Consumes("application/x-www-form-urlencoded")
 public String sendEmail(@FormParam("url") String address,@FormParam("context") String context) {
	 Boolean res=ED.sendEmail(address,context);
	 return "<B>" + "Sending result:"+res.toString()+ "</B>";
 }
 @POST
 @Path("sends")
 @Consumes("application/x-www-form-urlencoded")
 public String sendEmailBatch(@FormParam("url") String address,@FormParam("context") String context) {
	String []adds=address.split(",");
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
 		tag2=ED.sendEmail(cur,context);
	 return "<B>" + "Sending result:"+(tag1&&tag2)+ "</B>";
 }
 public boolean validateEmailAddress(String arg0){
 	 String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
     Pattern pattern;
     Matcher matcher;
     pattern = Pattern.compile(rule);
     matcher = pattern.matcher(arg0);
     return matcher.matches();
 }
}