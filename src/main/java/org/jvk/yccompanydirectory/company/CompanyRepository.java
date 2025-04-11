package org.jvk.yccompanydirectory.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    void deleteByName(String name);
    Optional<Company> findByName(String name); // Optional for case where name doesn't exist
}
