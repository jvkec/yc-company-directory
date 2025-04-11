package org.jvk.yccompanydirectory.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/company")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getCompanies(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String websiteURL,
            @RequestParam(required = false) String batch,
            @RequestParam(required = false) List<String> tags) {
        if (name != null) {
            return companyService.getCompanyByName(name);
        } else if (websiteURL != null) {
            Company company = companyService.getCompanyByWebsiteURL(websiteURL);
            return company != null ? List.of(company) : Collections.emptyList();
        } else if (batch != null && tags != null) {
            return companyService.getCompaniesByTagsAndBatch(tags, batch);
        } else if (batch != null) {
            return companyService.getCompaniesByBatch(batch);
        } else if (tags != null) {
            return companyService.getCompaniesWithTags(tags);
        } else
            return companyService.getCompanies();
    }

    // Handles HTTP requests for adding
    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        Company createdCompany = companyService.addCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    // Handles HTTP put requests for updating
    @PutMapping
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        Company updatedCompany = companyService.updateCompany(company);
        if (updatedCompany != null)
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // For deleting companies by name
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteCompany(@PathVariable String name) {
        companyService.deleteCompany(name);
        return new ResponseEntity<>(name + " deleted successfully", HttpStatus.OK);
    }
}
