package com.project.jobapp.company.impl;

import com.project.jobapp.company.Company;
import com.project.jobapp.company.CompanyRepository;
import com.project.jobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompany(Company company) {
//        Company comp = new Company();
//        comp.setId(company.getId());
//        comp.setName(company.getName());
//        comp.setJobs(company.getJobs());
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company updatedCompany = companyOptional.get();
            updatedCompany.setDescription(company.getDescription());
            updatedCompany.setName(company.getName());
            updatedCompany.setJobs(company.getJobs());
            companyRepository.save(updatedCompany);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCompany(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
