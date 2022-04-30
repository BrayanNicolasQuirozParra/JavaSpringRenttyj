package co.com.usta.renttyj.models.services.company;

import co.com.usta.renttyj.entity.Company;

import java.util.List;

public interface CompanyService {

    Company save(Company company);

    List<Company> findAll();

    void delete(Long id);

    Company findById(Long id) throws Exception;
}
