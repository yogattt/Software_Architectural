package code;

public class EmailServicePortBindingSkeleton implements code.EmailService, org.apache.axis.wsdl.Skeleton {
    private code.EmailService impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("validateEmailAddress", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://code/", "validateEmailAddress"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("validateEmailAddress") == null) {
            _myOperations.put("validateEmailAddress", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("validateEmailAddress")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "url"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "payload"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("sendEmail", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://code/", "sendEmail"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("sendEmail") == null) {
            _myOperations.put("sendEmail", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("sendEmail")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("sendEmailBatch", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://code/", "sendEmailBatch"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("sendEmailBatch") == null) {
            _myOperations.put("sendEmailBatch", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("sendEmailBatch")).add(_oper);
    }

    public EmailServicePortBindingSkeleton() {
        this.impl = new code.EmailServicePortBindingImpl();
    }

    public EmailServicePortBindingSkeleton(code.EmailService impl) {
        this.impl = impl;
    }
    public boolean validateEmailAddress(java.lang.String arg0) throws java.rmi.RemoteException
    {
        boolean ret = impl.validateEmailAddress(arg0);
        return ret;
    }

    public boolean sendEmail(java.lang.String url, java.lang.String payload) throws java.rmi.RemoteException
    {
        boolean ret = impl.sendEmail(url, payload);
        return ret;
    }

    public boolean sendEmailBatch(java.lang.String[] arg0, java.lang.String arg1) throws java.rmi.RemoteException
    {
        boolean ret = impl.sendEmailBatch(arg0, arg1);
        return ret;
    }

}
