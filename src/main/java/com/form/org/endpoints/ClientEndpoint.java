package com.form.org.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.form.org.Entity.Client;
import com.form.org.services.IClientService;

import http.www_form_com.org.client.AddClientRequest;
import http.www_form_com.org.client.AddClientResponse;
import http.www_form_com.org.client.CheckSoldeRequest;
import http.www_form_com.org.client.CheckSoldeResponse;
import http.www_form_com.org.client.GetClientRequest;
import http.www_form_com.org.client.GetClientResponse;
import http.www_form_com.org.client.PayFactureRequest;
import http.www_form_com.org.client.PayFactureResponse;
import http.www_form_com.org.client.ServiceStatus;
@Endpoint
public class ClientEndpoint {
	private static final String NAMESPACE_URI = "http//www.form.com/org/Client";
	@Autowired
	private IClientService clientService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientRequest")
	@ResponsePayload
	public GetClientResponse getClient(@RequestPayload GetClientRequest request) {
		GetClientResponse response = new GetClientResponse();
		http.www_form_com.org.client.Client clientInfo = new http.www_form_com.org.client.Client();
		BeanUtils.copyProperties(clientService.getClientByPhone(request.getPhone()), clientInfo);
		response.setClient(clientInfo);
		return response;
	}


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addClientRequest")
	@ResponsePayload
	public AddClientResponse addClient(@RequestPayload AddClientRequest request) {
		AddClientResponse response = new AddClientResponse();
	    Client client =new Client();
	    client.setIdentifiant(request.getClient().getIdentifiant());
	    client.setNom(request.getClient().getNom());
	    client.setPrenom(request.getClient().getPrenom());
	    client.setPhone(request.getClient().getPhone());
	    client.setSolde(request.getClient().getSolde());
		boolean flag = clientService.addClient(client);
		if (flag == false) {
			response.setMessage("Content Already Available");
		} else {
			http.www_form_com.org.client.Client clientInfo = new http.www_form_com.org.client.Client();
			BeanUtils.copyProperties(client, clientInfo);
			response.setMessage("Content Added Successfully");
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "checkSoldeRequest")
	@ResponsePayload
	public CheckSoldeResponse checkSolde(@RequestPayload CheckSoldeRequest request) {
		CheckSoldeResponse response =new CheckSoldeResponse();
		double solde=clientService.checkSolde(request.getPhone());
		response.setSolde(solde);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "payFactureRequest")
	@ResponsePayload
	public PayFactureResponse payFacture(@RequestPayload PayFactureRequest request) {
		PayFactureResponse response=new PayFactureResponse();
		ServiceStatus status=new ServiceStatus();
		boolean flag = clientService.payFacture(request.getPhone(),request.getPhone2(),request.getMontant());
		if (flag == false) {
			status.setMessage("your solde is low");
			response.setServiceStatus(status);
		} else {
			status.setMessage("the payement is done");
			response.setServiceStatus(status);		}
		return response;
	}
}
