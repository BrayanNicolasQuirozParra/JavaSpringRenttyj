package co.com.usta.renttyj.models.services.company;

import co.com.usta.renttyj.entity.Company;
import co.com.usta.renttyj.handler.exceptions.CustomException;
import co.com.usta.renttyj.models.daos.CompanyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    final CompanyDAO repository;

    @Override
    public Company save(Company company) {
        return repository.save(company);
    }

    @Override
    public List<Company> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Company findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(
                        String.format("La empresa con código %s no fue encontrada", id)));
    }
}
