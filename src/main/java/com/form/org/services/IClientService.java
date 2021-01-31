package com.form.org.services;

import org.springframework.stereotype.Service;

import com.form.org.Entity.Client;
@Service
public interface IClientService {
     boolean addClient(Client cl);
	 Client getClientByPhone(String phone);
	 Double checkSolde(String phone);
	 boolean payFacture(String phone,String phone2,Double montant);
}
