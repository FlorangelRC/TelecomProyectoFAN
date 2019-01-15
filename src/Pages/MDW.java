package Pages;

public class MDW {
	
	String usuariouat = "";
	String passworduat = "";
	String endpointuat = "";
	String headeruat = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"http://www.personal.com.ar/Common/RequestMessageHeader/v1.0\" xmlns:v11=\"http://www.personal.com.ar/ESB/MovimientoStock/v1.0\" xmlns:v12=\"http://www.personal.com.ar/Common/Entities/Recurso/Recurso/v1.0\" xmlns:v13=\"http://www.personal.com.ar/Common/Entities/Producto/Logistica/v1.0\">" 
						+ "<soapenv:Header>"
						+ "<v1:requestHeader>"
						+ "<v1:consumer code=\"WEB\" channel=\"WEB\" additionalData=\"WEB\">"
						+ "<v1:userID>" + usuariouat + "</v1:userID>"
						+ "<v1:credentials>"
						+ "<v1:userPassword>" + passworduat + "</v1:userPassword>"
						+ "</v1:credentials>"
						+ "</v1:consumer>"
						+ "<v1:message messageId=\"\" consumerMessageId=\"\">"
						+ "</v1:message>"
						+ "</v1:requestHeader>"
						+ "</soapenv:Header>"
						+ 
	
	String endpointtst = "";
	
	public String S095 (String ambiente, String seriales) {
		String resultado = "";
		
		return resultado;
	}
	
}
