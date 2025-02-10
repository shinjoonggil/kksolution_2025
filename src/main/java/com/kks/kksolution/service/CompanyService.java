package com.kks.kksolution.service;

import com.kks.kksolution.dto.company.CompanyDto;
import com.kks.kksolution.dto.company.CompanyFormDto;
import com.kks.kksolution.entity.Company;
import com.kks.kksolution.repository.CompanyRepository;
import com.kks.kksolution.vo.common.MessageVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final HttpServletRequest request;

    public UUID companyProcess(CompanyFormDto form) {

        Company company = null;
        if (form.getId() != null) {
            company = getCompanyById(form.getId());
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
            company=companyRepository.save(company);
        } else {
            company = companyRepository.save(Company.builder()
                    .id(UUID.randomUUID())
                    .name(form.getName().trim())
                    .businessNumber(form.getBusinessNumber().trim())
                    .ownerName(form.getOwnerName().trim())
                    .zipcode(form.getZipcode())
                    .address(form.getAddress())
                    .addressDetail(form.getAddressDetail())
                    .contact(form.getContact())
                    .email(form.getEmail())
                    .agentName(form.getAgentName().trim())
                    .agentContact(form.getAgentContact())
                    .agentEmail(form.getAgentEmail().trim())
                    .description(form.getDescription())
                    .createIp(request.getRemoteAddr())
                    .updateIp(request.getRemoteAddr())
                    .build());
        }
        request.getSession().setAttribute("message", MessageVO.SUCCESS("등록되었습니다."));
        return company.getId();
    }
    public CompanyDto getCompany(UUID id){
        return new CompanyDto(getCompanyById(id));
    }
    private Company getCompanyById(UUID id) {

        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("error.company.null"));
    }
}
