package code;

public interface EmailService extends java.rmi.Remote {
    public boolean validateEmailAddress(java.lang.String arg0) throws java.rmi.RemoteException;
    public boolean sendEmail(java.lang.String url, java.lang.String payload) throws java.rmi.RemoteException;
    public boolean sendEmailBatch(java.lang.String[] arg0, java.lang.String arg1) throws java.rmi.RemoteException;
}
