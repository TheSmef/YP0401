package com.example.YP.Repository;

import com.example.YP.Models.Category;
import com.example.YP.Models.ProviderAgreement;
import org.springframework.data.repository.CrudRepository;

public interface AgreementRepository extends CrudRepository<ProviderAgreement, Long> {
}
