package repository;

import model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepositoryInterface {
    List<Company> findAllCompanies();
    Optional<Company> findByIdCompany(String id);
    Company addCompany(Company company);
    Company updateCompany(Company company);
    boolean deleteByIdCompany(String id);
}