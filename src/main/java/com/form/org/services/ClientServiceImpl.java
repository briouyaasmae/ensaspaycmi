package com.form.org.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.form.org.Entity.Client;
import com.form.org.repository.ClientRepository;
@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private ClientRepository repository;
	
	@Override
	public synchronized boolean addClient(Client cl) {
 	  List<Client> result= repository.findByPhoneAndBanqueName(cl.getPhone(),cl.getBanqueName());
 		if(result.size()>0) {
 			return false;
 			
 		}
 		else {
 			repository.save(cl);
		    return true;
 		}
	}


	@Override
	public Client getClientByPhoneAndBanqueName(String phone,String BanqueName) {
		 Client client1=repository.findByPhoneAndBanqueName(phone,BanqueName).get(0);
		if(client1!=null) {
		  return client1;
		}
		else
		  return null;
	}

	
	@Override
	public Double checkSolde(String phone,String banqueName) {
		Client client1=repository.findByPhoneAndBanqueName(phone,banqueName).get(0);
		return client1.getSolde();
	}

	@Override
	public synchronized boolean payFacture(String phone, String phone2,Double montant,String banqueName) {
		Client client1=repository.findByPhoneAndBanqueName(phone,banqueName).get(0);
		Client client2=repository.findByPhone(phone2).get(0);
        String message =null;
		Double solde1=checkSolde(phone,banqueName);
		if(solde1<montant) {
			return false;
		}
		else {
		client1.setSolde(client1.getSolde()-montant);
		client2.setSolde(client2.getSolde()+montant);
		repository.save(client1);
		repository.save(client2);
		return true;
		}
	}



}
