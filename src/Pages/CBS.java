package Pages;

public class CBS {
	public String sRequestByOrder(String sOrder) {
		sOrder = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"http://www.personal.com.ar/Common/RequestMessageHeader/v1.0\" xmlns:v11=\"http://www.personal.com.ar/ESB/NotificarPago/v1.0\">\r\n"
				+ "\r\n   <soapenv:Header>\r\n"
				+ "\r\n      <v1:requestHeader>\r\n"
				+ "\r\n         <v1:consumer code=\"IVR\" channel=\"IVR\" additionalData=\"\">\r\n"
				+ "\r\n            <v1:userID>jack</v1:userID>\r\n"
				+ "\r\n            <v1:credentials>\r\n"
				+ "\r\n               <!--You have a CHOICE of the next 2 items at this level-->\r\n"
				+ "\r\n               <!--v1:userCertificate>?</v1:userCertificate-->\r\n"
				+ "\r\n               <v1:userPassword>jack</v1:userPassword>\r\n"
				+ "\r\n            </v1:credentials>\r\n"
				+ "\r\n         </v1:consumer>\r\n"
				+ "\r\n         <v1:message messageId=\"\" consumerMessageId=\"\">\r\n"
				+ "\r\n            <!--Optional:-->\r\n"
				+ "\r\n            <!--v1:timestamp>?</v1:timestamp-->\r\n"
				+ "\r\n         </v1:message>\r\n"
				+ "\r\n      </v1:requestHeader>\r\n"
				+ "\r\n   </soapenv:Header>\r\n"
				+ "\r\n   <soapenv:Body>\r\n"
				+ "\r\n      <v11:NotificarPagoRequest>\r\n"
				+ "\r\n         <v11:salesOrderId>" + sOrder;
		
		sOrder+= "</v11:salesOrderId>\r\n"
				+ "\r\n         <v11:status>payment succeed</v11:status>\r\n"
				+ "\r\n         <v11:statusInvoice>invoice succeed</v11:statusInvoice>\r\n"
				+ "\r\n      </v11:NotificarPagoRequest>\r\n"
				+ "\r\n   </soapenv:Body>\r\n"
				+ "\r\n</soapenv:Envelope>";
		return sOrder;
	}
	
	public String sCBS_Request_ServicioWeb_Validador(String sResponse) {
		String sAssert = "false";
		
		if (sResponse.equalsIgnoreCase("0OK")) {
			sAssert = "true";
		}
		else {
			sAssert = sResponse;
		}
		
		return sAssert;
	}
	
	public String sRequest(String sPaymentSerialNo, String sPaymentChannelID, String sAccountKey, String sPaymentMethod, String sAmount, String sInvoiceno) {
		String sRequest = "";
		sRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ars=\"http://www.huawei.com/bme/cbsinterface/arservices\" xmlns:cbs=\"http://www.huawei.com/bme/cbsinterface/cbscommon\" xmlns:arc=\"http://cbs.huawei.com/ar/wsservice/arcommon\">\r\n"
				+ "    <soapenv:Header/>\r\n"
				+ "\r\n    <soapenv:Body>\r\n"
				+ "\r\n        <ars:PaymentRequestMsg>\r\n"
				+ "\r\n            <RequestHeader>\r\n"
				+ "      	       		<cbs:Version>5.5</cbs:Version>\r\n"
				+ "      	       		<cbs:BusinessCode>Charge2AR</cbs:BusinessCode>\r\n"
				+ "       	       		<cbs:MessageSeq>"+sPaymentSerialNo;
		
		sRequest+="</cbs:MessageSeq>\r\n"
				+ "       	       		<cbs:OwnershipInfo>\r\n"
				+ "       	         		<cbs:BEID>10101</cbs:BEID>\r\n"
				+ "       	         		<cbs:BRID>101</cbs:BRID>\r\n"
				+ "         	   		</cbs:OwnershipInfo>\r\n"
				+ "             		<cbs:AccessSecurity>\r\n"
				+ "     	           		<cbs:LoginSystemCode>117</cbs:LoginSystemCode>\r\n"
				+ "         	       		<cbs:Password>jW6lRxU4leO5Xev+SISea/Ie7Dp5wDPgfGR9MNVDJRo=</cbs:Password>\r\n"
				+ "             	   		<cbs:RemoteIP>10.75.197.142</cbs:RemoteIP>\r\n"
				+ "       	      		</cbs:AccessSecurity>\r\n"
				+ "        	       		<cbs:OperatorInfo>\r\n"
				+ "        	        		<cbs:OperatorID>101</cbs:OperatorID>\r\n"
				+ "        	        		<cbs:ChannelID>1</cbs:ChannelID>\r\n"
				+ "        	       		</cbs:OperatorInfo>\r\n"
				+ "        	        	<cbs:AccessMode>A</cbs:AccessMode>\r\n"
				+ "        	        	<cbs:MsgLanguageCode>2002</cbs:MsgLanguageCode>\r\n"
				+ "        	        	<cbs:TimeFormat>\r\n"
				+ "        	        		<cbs:TimeType>1</cbs:TimeType>\r\n"
				+ "        	        		<cbs:TimeZoneID>8</cbs:TimeZoneID>\r\n"
				+ "        	       		</cbs:TimeFormat>\r\n"
				+ "        	        	<cbs:AdditionalProperty>\r\n"
				+ "        	        		<cbs:Code>108</cbs:Code>\r\n"
				+ "        	        		<cbs:Value>109</cbs:Value>\r\n"
				+ "        	        	</cbs:AdditionalProperty>\r\n"
				+ "     	      </RequestHeader>\r\n"
				+ "            <PaymentRequest>\r\n"
				+ "                <ars:PaymentSerialNo>" + sPaymentSerialNo;
		
		sRequest+= "</ars:PaymentSerialNo>\r\n"
				+ "                <ars:PaymentChannelID>" + sPaymentChannelID;
		
		sRequest+= "</ars:PaymentChannelID>\r\n"
				+ "                <ars:OpType>1</ars:OpType>\r\n"
				+ "                <ars:PaymentObj>\r\n"
				+ "                    <ars:AcctAccessCode>\r\n"
				+ "                        <arc:AccountKey>" + sAccountKey;
		
		sRequest+= "</arc:AccountKey>\r\n"
				+ "                    </ars:AcctAccessCode>\r\n"
				+ "                </ars:PaymentObj>\r\n"
				+ "                <ars:PaymentInfo>\r\n"
				+ "                    <ars:CashPayment>\r\n"
				+ "                        <ars:PaymentMethod>" + sPaymentMethod;
		
		sRequest+= "</ars:PaymentMethod>\r\n"
				+ "                        <ars:Amount>" + sAmount;
		
		sRequest+= "</ars:Amount>\r\n"
				+ "                        <ars:ApplyList>\r\n"
				+ "                            <ars:Invoiceno>" + sInvoiceno;
		
		sRequest+= "</ars:Invoiceno>                    \r\n"
				+ "                        </ars:ApplyList>\r\n"
				+ "                    </ars:CashPayment>\r\n"
				+ "                </ars:PaymentInfo>\r\n"
				+ "            </PaymentRequest>\r\n"
				+ "        </ars:PaymentRequestMsg>\r\n"
				+ "    </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
		
		return sRequest;
	}
	
	public boolean sCBS_Request_Validador(String sResponse) {
		boolean sAssert = false;
		
		if (sResponse.contains("Operation successfully")) {
			sAssert = true;
		}
		
		return sAssert;
	}
	
	public String sRequestByLinea(String sLinea, String sMessageSeq) {
		String sRequest = "";
		sRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:bcs=\"http://www.huawei.com/bme/cbsinterface/bcservices\" xmlns:cbs=\"http://www.huawei.com/bme/cbsinterface/cbscommon\" xmlns:bcc=\"http://www.huawei.com/bme/cbsinterface/bccommon\">\r\n"
				+ "\r\n   <soapenv:Header/>\r\n"
				+ "\r\n   <soapenv:Body>\r\n"
				+ "\r\n      <bcs:QueryCustomerInfoRequestMsg>\r\n"
				+ "\r\n                    <RequestHeader>\r\n"
				+ "\r\n                                               <cbs:Version>5.5</cbs:Version>\r\n"
				+ "\r\n                                               <cbs:BusinessCode>QueryCustomerInfo</cbs:BusinessCode>\r\n"
				+ "\r\n                                               <cbs:MessageSeq>" + sMessageSeq;
		
		sRequest += "</cbs:MessageSeq>\r\n"
				+ "\r\n                                               <cbs:OwnershipInfo>\r\n"
				+ "\r\n                                                               <cbs:BEID>10101</cbs:BEID>\r\n"
				+ "\r\n                                                               <cbs:BRID>101</cbs:BRID>\r\n"
				+ "\r\n                                               </cbs:OwnershipInfo>\r\n"
				+ "\r\n                				<cbs:AccessSecurity>\r\n"
				+ "\r\n                                                               <cbs:LoginSystemCode>117</cbs:LoginSystemCode>\r\n"
				+ "\r\n                                                               <cbs:Password>jW6lRxU4leO5Xev+SISea/Ie7Dp5wDPgfGR9MNVDJRo=</cbs:Password>\r\n"
				+ "\r\n                                                               <cbs:RemoteIP>10.138.22.65</cbs:RemoteIP>\r\n"
				+ "\r\n                                               </cbs:AccessSecurity>\r\n"
				+ "\r\n                                               <cbs:OperatorInfo>\r\n"
				+ "\r\n                                                               <cbs:OperatorID>101</cbs:OperatorID>\r\n"
				+ "\r\n                                                               <cbs:ChannelID>1</cbs:ChannelID>\r\n"
				+ "\r\n                                               </cbs:OperatorInfo>\r\n"
				+ "\r\n                                               <cbs:TimeFormat>\r\n"
				+ "\r\n                                                               <cbs:TimeType>1</cbs:TimeType>\r\n"
				+ "\r\n                                                               <cbs:TimeZoneID>8</cbs:TimeZoneID>\r\n"
				+ "\r\n                                               </cbs:TimeFormat>\r\n"
				+ "\r\n                                               <cbs:AdditionalProperty>\r\n"
				+ "\r\n                                                               <cbs:Code>108</cbs:Code>\r\n"
				+ "\r\n                                                               <cbs:Value>109</cbs:Value>\r\n"
				+ "\r\n                                               </cbs:AdditionalProperty>\r\n"
				+ "\r\n                             </RequestHeader> \r\n"
				+ "\r\n      		<QueryCustomerInfoRequest>\r\n"
				+ "\r\n            	  		<bcs:QueryObj>\r\n"
				+ "\r\n               			<bcs:SubAccessCode>\r\n"
				+ "\r\n                  			<bcc:PrimaryIdentity>" + sLinea;
		
		sRequest+= "</bcc:PrimaryIdentity>\r\n"
				+ "\r\n               			</bcs:SubAccessCode>\r\n"
				+ "\r\n            			</bcs:QueryObj>\r\n"
				+ "\r\n         	</QueryCustomerInfoRequest>      \r\n"
				+ "\r\n      </bcs:QueryCustomerInfoRequestMsg>\r\n"
				+ "\r\n   </soapenv:Body>\r\n"
				+ "\r\n</soapenv:Envelope>";
		return sRequest;
	}
	
	public String sCBS_Request_Validador_Alta_Linea(String sResponse, String sLinea, String sImsi, String sICCD, String sNombre, String sApellido) {
		String sAssert = "false";
		sLinea = "SubIdentity>" + sLinea;
		
		//if (sResponse.contains(sLinea) && sResponse.contains(sImsi) && sResponse.contains(sICCD)) {
		if (sResponse.contains(sLinea) && sResponse.contains(sNombre) && sResponse.contains(sApellido)) {
			sAssert = "true";
		}
		else {
			sAssert = sResponse;
		}
		
		return sAssert;
	}
	
	public String sRequestByTC(String sMessageSeq, String sPaymentChannelID, String sAccountKey, String sPaymentMethod, String sAmount, String sAccountNumber, String sAccountName, String sExpirationDate, String sCVV, String sInvoiceno, String sCardHolderName, String sCardHolderNumber) {
		String sRequest = "";
		sRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ars=\"http://www.huawei.com/bme/cbsinterface/arservices\" xmlns:cbs=\"http://www.huawei.com/bme/cbsinterface/cbscommon\" xmlns:arc=\"http://cbs.huawei.com/ar/wsservice/arcommon\">\r\n"
				+ "<soapenv:Header/>\r\n"
				+ "<soapenv:Body>\r\n"
				+ "<ars:PaymentRequestMsg>\r\n"
				+ "<RequestHeader>\r\n"
				+ "<cbs:Version>5.5</cbs:Version>\r\n"
				+ "<cbs:BusinessCode>Charge2AR</cbs:BusinessCode>\r\n"
				+ "<cbs:MessageSeq>" + sMessageSeq;
		
		sRequest+= "</cbs:MessageSeq>\r\n"
				+ "<cbs:OwnershipInfo>\r\n"
				+ "<cbs:BEID>10101</cbs:BEID>\r\n"
				+ "<cbs:BRID>101</cbs:BRID>\r\n"
				+ "</cbs:OwnershipInfo>\r\n"
				+ "<cbs:AccessSecurity>\r\n"
				+ "<cbs:LoginSystemCode>117</cbs:LoginSystemCode>\r\n"
				+ "<cbs:Password>jW6lRxU4leO5Xev+SISea/Ie7Dp5wDPgfGR9MNVDJRo=</cbs:Password>\r\n"
				+ "<cbs:RemoteIP>10.75.197.142</cbs:RemoteIP>\r\n"
				+ "</cbs:AccessSecurity>\r\n"
				+ "<cbs:OperatorInfo>\r\n"
				+ "<cbs:OperatorID>101</cbs:OperatorID>\r\n"
				+ "<cbs:ChannelID>1</cbs:ChannelID>\r\n"
				+ "</cbs:OperatorInfo>\r\n"
				+ "<cbs:AccessMode>A</cbs:AccessMode>\r\n"
				+ "<cbs:MsgLanguageCode>2002</cbs:MsgLanguageCode>\r\n"
				+ "<cbs:TimeFormat>\r\n"
				+ "<cbs:TimeType>1</cbs:TimeType>\r\n"
				+ "<cbs:TimeZoneID>8</cbs:TimeZoneID>\r\n"
				+ "</cbs:TimeFormat>\r\n"
				+ "<cbs:AdditionalProperty>\r\n"
				+ "<cbs:Code>108</cbs:Code>\r\n"
				+ "<cbs:Value>109</cbs:Value>\r\n"
				+ "</cbs:AdditionalProperty>\r\n"
				+ "</RequestHeader>\r\n"
				+ "<PaymentRequest>\r\n"
				+ "<ars:PaymentSerialNo>${=(new java.text.SimpleDateFormat(\"yyyyMMddHHmmss\")).format(new Date())}${=(int)(Math.random()*1000)}</ars:PaymentSerialNo>\r\n"
				+ "<ars:PaymentChannelID>" + sPaymentChannelID;
		
		sRequest+= "</ars:PaymentChannelID>\r\n"
				+ "<ars:OpType>1</ars:OpType>\r\n"
				+ "<ars:PaymentObj>\r\n"
				+ "<ars:AcctAccessCode>\r\n"
				+ "<arc:AccountKey>" + sAccountKey;
		
		sRequest+= "</arc:AccountKey>\r\n"
				+ "</ars:AcctAccessCode>\r\n"
				+ "</ars:PaymentObj>\r\n"
				+ "<ars:PaymentInfo>\r\n"
				+ "<ars:CashPayment>\r\n"
				+ "<ars:PaymentMethod>" + sPaymentMethod;
		
		sRequest+= "</ars:PaymentMethod>\r\n"
				+ "<ars:Amount>" + sAmount;
		
		sRequest+= "</ars:Amount>\r\n"
				+ "<ars:BankInfo>\r\n"
				+ "<arc:BankCode>11</arc:BankCode>\r\n"
				+ "<arc:AcctType>C</arc:AcctType>\r\n"
				+ "<arc:AcctNo>" + sAccountNumber;
		
		sRequest+= "</arc:AcctNo>\r\n"
				+ "<arc:CreditCardType>403</arc:CreditCardType>\r\n"
				+ "<arc:AcctName>" + sAccountName;
		
		sRequest+= "</arc:AcctName>\r\n"
				+ "<arc:ExpDate>" + sExpirationDate;
		
		sRequest+= "</arc:ExpDate>\r\n"
				+ "<arc:CVVNumber>" + sCVV;
		
		sRequest+= "</arc:CVVNumber>\r\n"
				+ "<arc:NumberOfInstallment>1</arc:NumberOfInstallment>\r\n"
				+ "</ars:BankInfo>\r\n"
				+ "<!--Zero or more repetitions:-->\r\n"
				+ "<ars:ApplyList>\r\n"
				+ "<ars:Invoiceno>" + sInvoiceno;
				
		sRequest+= "</ars:Invoiceno>\r\n"
				+ "</ars:ApplyList>\r\n"
				+ "<ars:PaymentPlan>0</ars:PaymentPlan>\r\n"
				+ "</ars:CashPayment>\r\n"
				+ "</ars:PaymentInfo>\r\n"
				+ "<ars:PointOfSaleID>782</ars:PointOfSaleID>\r\n"
				+ "<ars:PaymentOperationType>SalesInvoice</ars:PaymentOperationType>\r\n"
				+ "<ars:CurrencyID>1006</ars:CurrencyID>\r\n"
				+ "<ars:WondersoftInfo>\r\n"
				+ "<ars:OriginIP>10.70.26.101</ars:OriginIP>\r\n"
				+ "<ars:CardHolderName>" + sCardHolderName;
		
		sRequest+= "</ars:CardHolderName>\r\n"
				+ "<ars:CardHolderDocumentType>96</ars:CardHolderDocumentType>\r\n"
				+ "<ars:CardHolderDocumentNumber>" + sCardHolderNumber;
		
		sRequest+= "</ars:CardHolderDocumentNumber>\r\n"
				+ "<ars:BankPromotionCode>0</ars:BankPromotionCode>\r\n"
				+ "</ars:WondersoftInfo>\r\n"
				+ "</PaymentRequest>\r\n"
				+ "</ars:PaymentRequestMsg>\r\n"
				+ "</soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
		
		return sRequest;
	}
	
	public String sCBS_TC_Request_Validador(String sResponse) {
		String sAssert = "false";
		
		if (sResponse.contains("Operation successfully")) {
			sAssert = "true";
		}
		else {
			sAssert = sResponse;
		}
		
		return sAssert;
	}
}