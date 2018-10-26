package Tests;

import javax.xml.soap.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class SOAPClientSAAJ {
	
	//SIT
	static String sPagoEnCajaSIT = "http://10.75.197.161:8080/services/ArServices";
	//UAT
	static String sPagoEnCajaUAT = "http://10.75.39.146:8080/services/ArServices";
		
	static String sPagoSimulado = "http://mdwtpbust1.telecom.com.ar:8701/notificarPago";
	static String sQueryCustomerInfoUAT = "http://10.75.39.146:8080/services/BcServices";
	static String sQueryCustomerInfoSIT = "http://10.75.197.163:8080/services/BcServices";
	static String sQueryFreeUnitSIT = "http://10.75.197.163:8080/services/BbServices";
	static String sQueryFreeUnitUAT = "http://10.75.39.146:8080/services/BbServices";
	
	
	public Document callSoapWebService(String soapMessageString, String sEndPoint) {
		Document doc = null;
		switch (sEndPoint.toLowerCase()) {
    		case "pago simulado":
	    		sEndPoint = sPagoSimulado;
	    		break;
	    	case "pago en caja":
	    		if (TestBase.urlAmbiente.contains("sit"))
	    			sEndPoint = sPagoEnCajaSIT;
	    		else
	    			sEndPoint = sPagoEnCajaUAT;
	    		break;
	    	case "datos usuario":
	    		if (TestBase.urlAmbiente.contains("sit"))
	    			sEndPoint = sQueryCustomerInfoSIT;
	    		else
	    			sEndPoint = sQueryCustomerInfoUAT;
	    		break;
	    	case "unidades libres":
	    		if (TestBase.urlAmbiente.contains("sit"))
	    			sEndPoint = sQueryFreeUnitSIT;
	    		else
	    			sEndPoint = sQueryFreeUnitUAT;
	    		break;
    	}
    	
    	try {
        	// Create SOAP Connection
        	SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            
            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSRequest(soapMessageString), sEndPoint);
            
            soapConnection.close();
            doc = soapResponse.getSOAPBody().extractContentAsDocument();
            return doc;
        } catch (Exception e) {
        	System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
            return doc;
        }
	}
    
	private static SOAPMessage createSRequest(String msg) {
		SOAPMessage request = null;
        try {
        	MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            request = msgFactory.createMessage();
            
            SOAPPart msgPart = request.getSOAPPart();
            SOAPEnvelope envelope = msgPart.getEnvelope();
            SOAPBody body = envelope.getBody();
            
            javax.xml.transform.stream.StreamSource _msg = new javax.xml.transform.stream.StreamSource(new java.io.StringReader(msg));
            msgPart.setContent(_msg);
            
            request.saveChanges();
            
            /* Print the request message, just for debugging purposes */
            //System.out.println("Request SOAP Message:");
            // request.writeTo(System.out);
            // System.out.println("\n");
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return request;
    }

}