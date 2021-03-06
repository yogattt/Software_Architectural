
package code;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "emailService", targetNamespace = "http://code/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EmailService {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sendEmail", targetNamespace = "http://code/", className = "code.SendEmail")
    @ResponseWrapper(localName = "sendEmailResponse", targetNamespace = "http://code/", className = "code.SendEmailResponse")
    @Action(input = "http://code/emailService/sendEmailRequest", output = "http://code/emailService/sendEmailResponse")
    public boolean sendEmail(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sendEmailBatch", targetNamespace = "http://code/", className = "code.SendEmailBatch")
    @ResponseWrapper(localName = "sendEmailBatchResponse", targetNamespace = "http://code/", className = "code.SendEmailBatchResponse")
    @Action(input = "http://code/emailService/sendEmailBatchRequest", output = "http://code/emailService/sendEmailBatchResponse")
    public boolean sendEmailBatch(
        @WebParam(name = "arg0", targetNamespace = "")
        List<String> arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "validateEmailAddress", targetNamespace = "http://code/", className = "code.ValidateEmailAddress")
    @ResponseWrapper(localName = "validateEmailAddressResponse", targetNamespace = "http://code/", className = "code.ValidateEmailAddressResponse")
    @Action(input = "http://code/emailService/validateEmailAddressRequest", output = "http://code/emailService/validateEmailAddressResponse")
    public boolean validateEmailAddress(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
