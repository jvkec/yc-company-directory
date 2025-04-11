package org.jvk.yccompanydirectory.company;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component // A spring annotation that marks a Java class as a component
           // Spring will manage its life cycle
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public List<Company> getCompaniesWithTags(List<String> tags) {
        return companyRepository.findAll().stream()
                .filter(company -> new HashSet<>(company.getTags()).containsAll(tags))
                .collect(Collectors.toList());
    }

    public List<Company> getCompanyByName(String name) {
        return companyRepository.findAll().stream()
                .filter(company -> company.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Company> getCompaniesByBatch(String batch) {
        return companyRepository.findAll().stream()
                .filter(company -> company.getBatch() != null &&
                        company.getBatch().equalsIgnoreCase(batch))
                .collect(Collectors.toList());
    }

    public String getCompanyWebsiteURL(String name){
        return companyRepository.findByName(name)
                .map(Company::getWebsiteURL)
                .orElse(null);
    }

    public String getCompanyYcURL(String name){
        return companyRepository.findByName(name)
                .map(Company::getYcURL)
                .orElse(null);
    }

    public String getCompanyDescription(String name){
        return companyRepository.findByName(name)
                .map(Company::getDescription)
                .orElse(null);
    }

    public List<Company> getCompaniesByTagsAndBatch(List<String> tags, String batch) {
        return companyRepository.findAll().stream()
                .filter(company -> company.getBatch().equalsIgnoreCase(batch) &&
                        new HashSet<>(company.getTags()).containsAll(tags))
                .collect(Collectors.toList());
    }

    public Company getCompanyByWebsiteURL(String websiteURL) {
        return companyRepository.findAll().stream()
                .filter(company -> company.getWebsiteURL().equalsIgnoreCase(websiteURL))
                .findFirst().orElse(null);
    }

    public Company addCompany(Company company) {
        companyRepository.save(company);
        return company;
    }

    public Company updateCompany(Company company) {
        Optional<Company> existingCompany = companyRepository.findByName(company.getName());

        if (existingCompany.isPresent()) {
            Company companyToUpdate = existingCompany.get();
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setWebsiteURL(company.getWebsiteURL());
            companyToUpdate.setYcURL(company.getYcURL());
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setTags(company.getTags());
            // Saving updated/new player to db repo
            companyRepository.save(companyToUpdate);
            return companyToUpdate;
        }
        // If player not in dbase case
        return null;
    }

    public void deleteCompany(String name) {
        companyRepository.deleteByName(name);
    }
}
