package com.example.YP.Repository;

import com.example.YP.Models.Employee;
import com.example.YP.Models.ProviderCompany;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProviderCompanyRepository extends CrudRepository<ProviderCompany, Long> {
    public List<ProviderCompany> findByName(String name);
    public List<ProviderCompany> findByNameContaining(String name);
}
