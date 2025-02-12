package com.kks.kksolution.service;

import com.kks.kksolution.dto.company.CompanyDto;
import com.kks.kksolution.dto.company.CompanyFormDto;
import com.kks.kksolution.dto.filter.CompanyFilterDto;
import com.kks.kksolution.entity.Company;
import com.kks.kksolution.repository.CompanyRepository;
import com.kks.kksolution.vo.common.MessageVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final HttpServletRequest request;

    public UUID companyProcess(CompanyFormDto form) {
        String message = "company.success.create";
        Company company = companyRepository.findById(form.getId()).orElse(null);
        if (company == null) {//create
            company = Company.builder().id(form.getId()).createIp(request.getRemoteAddr()).build();
            message = "company.success.update";
        }

        company.setName(form.getName().trim());
        company.setBusinessNumber(form.getBusinessNumber().trim());
        company.setOwnerName(form.getOwnerName().trim());
        company.setZipcode(form.getZipcode().trim());
        company.setAddress(form.getAddress().trim());
        company.setAddressDetail(form.getAddressDetail().trim());
        company.setContact(form.getContact().trim());
        company.setEmail(form.getEmail().trim());
        company.setAgentName(form.getAgentName().trim());
        company.setAgentContact(form.getAgentContact().trim());
        company.setAgentEmail(form.getAgentEmail().trim());
        company.setDescription(form.getDescription().trim());
        company.setUpdateIp(request.getRemoteAddr());
        company = companyRepository.save(company);


        request.getSession().setAttribute("message", MessageVO.SUCCESS(message));
        return company.getId();
    }

    public CompanyDto getEmptyCompany() {
        Company company = Company.builder().id(UUID.randomUUID()).build();
        return new CompanyDto(company);
    }

    public CompanyDto getCompany(UUID id) {
        return new CompanyDto(getCompanyById(id));
    }

    public Page<CompanyDto> getCompanies(CompanyFilterDto filter) {
        Page<Company> companyPage = companyRepository.findAll(filter.getPageable());
        return companyPage.map(CompanyDto::new);
    }

    private Company getCompanyById(UUID id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("company.error.null"));
    }
}
