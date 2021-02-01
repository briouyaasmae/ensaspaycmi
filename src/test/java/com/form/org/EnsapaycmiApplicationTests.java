package com.form.org;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;
import javax.xml.transform.Source;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EnsapaycmiApplicationTests {
	 @Autowired
	 private ApplicationContext applicationContext;
	 private MockWebServiceClient mockClient;
	 private Resource xsdSchema = new ClassPathResource("clientSchema.xsd");
	
    @Before
	public void init(){
	        mockClient = MockWebServiceClient.createClient(applicationContext);
	    }
    @Test
      public void getClientByPhoneTest() throws Exception {
        Source requestPayload = new StringSource(
        		"<getClientRequest xmlns=\"http//www.form.com/org/Client\">"
        	       +"<phone>1112225</phone>"
        	        +"</getClientRequest>");
        
        Source responsePayload = new StringSource(
        " <ns2:getClientResponse xmlns:ns2=\"http//www.form.com/org/Client\">"
        +"<ns2:client>"
        +" <ns2:identifiant>0522535</ns2:identifiant>"
        +" <ns2:nom>briouya</ns2:nom>"
        +" <ns2:prenom>asmae</ns2:prenom>"
         +" <ns2:phone>1112225</ns2:phone>"
         +" <ns2:solde>78500.0</ns2:solde>"
          +" </ns2:client>"
       +" </ns2:getClientResponse>");
 
        mockClient.sendRequest(withPayload(requestPayload))
                .andExpect(noFault())
                .andExpect(payload(responsePayload))
                .andExpect(validPayload(xsdSchema));
    }
    @Test
    public void AddClientNotExist() throws Exception {
      Source requestPayload = new StringSource(
      		"<addClientRequest xmlns=\"http//www.form.com/org/Client\">\n"
      		+ "            <client>\n"
      		+ "                <identifiant>5654</identifiant>\n"
      		+ "                <nom>ELHADI</nom>\n"
      		+ "                <prenom>Mouad</prenom>\n"
      		+ "                <phone>25889</phone>\n"
      		+ "                <solde>2552.10</solde>\n"
      		+ "            </client>\n"
      		+ "        </addClientRequest>");
      
      Source responsePayload = new StringSource(
      "        <ns2:addClientResponse xmlns:ns2=\"http//www.form.com/org/Client\">\n"
      + "            <ns2:message>Content Added Successfully</ns2:message>\n"
      + "        </ns2:addClientResponse>");

      mockClient.sendRequest(withPayload(requestPayload))
              .andExpect(noFault())
              .andExpect(payload(responsePayload))
              .andExpect(validPayload(xsdSchema));
  }
    @Test
    public void AddClientExist() throws Exception {
      Source requestPayload = new StringSource(
      		"<addClientRequest xmlns=\"http//www.form.com/org/Client\">\n"
      		+ "            <client>\n"
      		+ "                <identifiant>111258</identifiant>\n"
      		+ "                <nom>Briouya</nom>\n"
      		+ "                <prenom>Hasnae</prenom>\n"
      		+ "                <phone>025898</phone>\n"
      		+ "                <solde>900.0</solde>\n"
      		+ "            </client>\n"
      		+ "        </addClientRequest>");
      
      Source responsePayload = new StringSource(
      "        <ns2:addClientResponse xmlns:ns2=\"http//www.form.com/org/Client\">\n"
      + "            <ns2:message>Content Already Available</ns2:message>\n"
      + "        </ns2:addClientResponse>");

      mockClient.sendRequest(withPayload(requestPayload))
              .andExpect(noFault())
              .andExpect(payload(responsePayload))
              .andExpect(validPayload(xsdSchema));
  }

    @Test
    public void checkSoldeByPhoneTest() throws Exception {
      Source requestPayload = new StringSource(
      		"<checkSoldeRequest xmlns=\"http//www.form.com/org/Client\">"
      	       +"<phone>1112225</phone>"
      	        +"</checkSoldeRequest>");
      
      Source responsePayload = new StringSource(
      "         <ns2:checkSoldeResponse xmlns:ns2=\"http//www.form.com/org/Client\">\n"
      + "            <ns2:solde>78500.0</ns2:solde>\n"
      + "        </ns2:checkSoldeResponse>");

      mockClient.sendRequest(withPayload(requestPayload))
              .andExpect(noFault())
              .andExpect(payload(responsePayload))
              .andExpect(validPayload(xsdSchema));
  }
    @Test
    public void payFactureDone() throws Exception {
      Source requestPayload = new StringSource(
      		"        <payFactureRequest xmlns=\"http//www.form.com/org/Client\">\n"
      		+ "            <montant>100</montant>\n"
      		+ "            <phone>11122</phone>\n"
      		+ "            <phone2>01588995</phone2>\n"
      		+ "        </payFactureRequest>");
      
      Source responsePayload = new StringSource(
      "        <ns2:payFactureResponse xmlns:ns2=\"http//www.form.com/org/Client\">\n"
      + "            <ns2:serviceStatus>\n"
      + "                <ns2:message>the payement is done</ns2:message>\n"
      + "            </ns2:serviceStatus>\n"
      + "         </ns2:payFactureResponse>");

      mockClient.sendRequest(withPayload(requestPayload))
              .andExpect(noFault())
              .andExpect(payload(responsePayload))
              .andExpect(validPayload(xsdSchema));
  }
    @Test
    public void payFactureIsNotDone() throws Exception {
      Source requestPayload = new StringSource(
      		"        <payFactureRequest xmlns=\"http//www.form.com/org/Client\">\n"
      		+ "            <montant>1000000</montant>\n"
      		+ "            <phone>11122</phone>\n"
      		+ "            <phone2>01588995</phone2>\n"
      		+ "   </payFactureRequest>");
      
      Source responsePayload = new StringSource(
      "        <ns2:payFactureResponse xmlns:ns2=\"http//www.form.com/org/Client\">\n"
      + "            <ns2:serviceStatus>\n"
      + "                <ns2:message>your solde is low</ns2:message>\n"
      + "            </ns2:serviceStatus>\n"
      + "        </ns2:payFactureResponse>");

      mockClient.sendRequest(withPayload(requestPayload))
              .andExpect(noFault())
              .andExpect(payload(responsePayload))
              .andExpect(validPayload(xsdSchema));
  }
}
