package Tests;

import javax.xml.soap.*;

public class SOAPClientSAAJ {
	
	static String sPagoEnCaja = "http://10.75.197.161:8080/services/ArServices";
	static String sPagoSimulado = "http://mdwtpbust1.telecom.com.ar:8701/notificarPago";
	static String sAltaDeLinea = "http://10.75.39.146:8080/services/BcServices";
	
	public String callSoapWebService(String soapMessageString, String sEndPoint) {
    	switch (sEndPoint.toLowerCase()) {
    		case "pago simulado":
	    		sEndPoint = sPagoSimulado;
	    		break;
	    	case "pago en caja":
	    		sEndPoint = sPagoEnCaja;
	    		break;
	    	case "alta de linea":
	    		sEndPoint = sAltaDeLinea;
	    		break;
    	}
    	
    	try {
        	// Create SOAP Connection
        	SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            
            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSRequest(soapMessageString), sEndPoint);
            
            //Print the SOAP Response
            //System.out.println("Response SOAP Message:");
            //soapResponse.writeTo(System.out);
            //System.out.println();
            
            soapConnection.close();
            
            return soapResponse.getSOAPBody().getTextContent();
        } catch (Exception e) {
        	System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
            return "Somthing went wrong... please call Don Barredora";
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