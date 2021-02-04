package com.form.org.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.form.org.Entity.Client;
@Service
public interface IClientService {
     boolean addClient(Client cl);
	Double checkSolde(String phone, String banqueName);
	boolean payFacture(String phone, String phone2, Double montant, String banqueName);
	Client getClientByPhoneAndBanqueName(String phone, String BanqueName);
}
