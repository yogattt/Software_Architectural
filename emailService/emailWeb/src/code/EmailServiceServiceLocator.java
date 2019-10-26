package code;

public class EmailServiceServiceLocator extends org.apache.axis.client.Service implements code.EmailServiceService {

    public EmailServiceServiceLocator() {
    }


    public EmailServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EmailServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for emailServicePort
    private java.lang.String emailServicePort_address = "http://localhost:8080/emailWeb/services/emailServicePort";

    public java.lang.String getemailServicePortAddress() {
        return emailServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String emailServicePortWSDDServiceName = "emailServicePort";

    public java.lang.String getemailServicePortWSDDServiceName() {
        return emailServicePortWSDDServiceName;
    }

    public void setemailServicePortWSDDServiceName(java.lang.String name) {
        emailServicePortWSDDServiceName = name;
    }

    public code.EmailService getemailServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(emailServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getemailServicePort(endpoint);
    }

    public code.EmailService getemailServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            code.EmailServicePortBindingStub _stub = new code.EmailServicePortBindingStub(portAddress, this);
            _stub.setPortName(getemailServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setemailServicePortEndpointAddress(java.lang.String address) {
        emailServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (code.EmailService.class.isAssignableFrom(serviceEndpointInterface)) {
                code.EmailServicePortBindingStub _stub = new code.EmailServicePortBindingStub(new java.net.URL(emailServicePort_address), this);
                _stub.setPortName(getemailServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("emailServicePort".equals(inputPortName)) {
            return getemailServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://code/", "emailServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://code/", "emailServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("emailServicePort".equals(portName)) {
            setemailServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
