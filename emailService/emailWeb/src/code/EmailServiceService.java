
package code;

public interface EmailServiceService extends javax.xml.rpc.Service {
    public java.lang.String getemailServicePortAddress();

    public code.EmailService getemailServicePort() throws javax.xml.rpc.ServiceException;

    public code.EmailService getemailServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
