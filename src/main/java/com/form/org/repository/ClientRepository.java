package com.form.org.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import com.form.org.Entity.Client;

public interface ClientRepository  extends CrudRepository<Client, String>,MongoRepository<Client, String> {
    List<Client> findByPhone(String phone);
}
