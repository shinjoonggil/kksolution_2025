package com.kks.kksolution.service;

import com.kks.kksolution.dto.company.CompanyFormDto;
import com.kks.kksolution.entity.Company;
import com.kks.kksolution.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {
    private final CompanyRepository companyRepository;

    public void companyProcess(CompanyFormDto form) {
        log.info(form.toString());
        Company company = null;
        if(form.getId() != null){
            company = getCompanyById(form.getId());
        }else{
            company = Company.builder().build();
        }

        log.info(company.toString());
    }

    private Company getCompanyById(UUID id) {

        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("error.company.null"));
    }
}
