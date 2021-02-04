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
        		"        <getClientRequest xmlns=\"http//www.form.com/org/Client\">\n"
        		+ "            <phone>52147441174</phone>\n"
        		+ "            <banqueName>CIH</banqueName>\n"
        		+ "        </getClientRequest>");
        
        Source responsePayload = new StringSource(
        "  <ns2:getClientResponse xmlns:ns2=\"http//www.form.com/org/Client\">\n"
        + "            <ns2:client>\n"
        + "                <ns2:identifiant>123</ns2:identifiant>\n"
        + "                <ns2:nom>br</ns2:nom>\n"
        + "                <ns2:prenom>as</ns2:prenom>\n"
        + "                <ns2:phone>52147441174</ns2:phone>\n"
        + "                <ns2:solde>10800.0</ns2:solde>\n"
        + "                <ns2:banqueName>CIH</ns2:banqueName>\n"
        + "            </ns2:client>\n"
        + "        </ns2:getClientResponse>");
 
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
      		+ "                <identifiant>sssdd</identifiant>\n"
      		+ "                <nom>lxdkcndfn</nom>\n"
      		+ "                <prenom>test</prenom>\n"
      		+ "                <phone>125458</phone>\n"
      		+ "                <solde>2552.10</solde>\n"
            + "            <banqueName>CIH</banqueName>\n"
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
      		+ "                <identifiant>23563</identifiant>\n"
      		+ "                <nom>briouya</nom>\n"
      		+ "                <prenom>asmae</prenom>\n"
      		+ "                <phone>11154</phone>\n"
      		+ "                <solde>900.0</solde>\n"
      		+ "                <solde>CMI</solde>\n"
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
      		"<checkSoldeRequest xmlns=\"http//www.form.com/org/Client\">\n"
      		+ "            <phone>52147441174</phone>\n"
      		+ "            <banqueName>CIH</banqueName>\n"
      		+ "        </checkSoldeRequest>");
      
      Source responsePayload = new StringSource(
      "      <ns2:checkSoldeResponse xmlns:ns2=\"http//www.form.com/org/Client\">\n"
      + "            <ns2:solde>10800.0</ns2:solde>\n"
      + "        </ns2:checkSoldeResponse>");

      mockClient.sendRequest(withPayload(requestPayload))
              .andExpect(noFault())
              .andExpect(payload(responsePayload))
              .andExpect(validPayload(xsdSchema));
  }
    @Test
    public void payFactureDone() throws Exception {
      Source requestPayload = new StringSource(
    		  "   <payFactureRequest xmlns=\"http//www.form.com/org/Client\">\n"
    		      		+ "            <montant>10</montant>\n"
    		      		+ "            <phone>11154</phone>\n"
    		      		+ "            <phone2>11154</phone2>\n"
    		      		+ "            <banqueName>CIH</banqueName>\n"
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
      		"   <payFactureRequest xmlns=\"http//www.form.com/org/Client\">\n"
      		+ "            <montant>100000000000</montant>\n"
      		+ "            <phone>11154</phone>\n"
      		+ "            <phone2>11154</phone2>\n"
      		+ "            <banqueName>CIH</banqueName>\n"
      		+ "        </payFactureRequest>");
      
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
