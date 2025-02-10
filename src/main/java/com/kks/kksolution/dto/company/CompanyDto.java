package com.kks.kksolution.dto.company;

import com.kks.kksolution.entity.Company;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CompanyDto {
    private UUID id;
    private String name;
    private String businessNumber;
    private String ownerName;
    private String zipcode;
    private String address;
    private String addressDetail;
    private String contact;
    private String email;

    private String agentName;
    private String agentContact;
    private String agentEmail;
    private String description;

    public CompanyDto(Company company) {
        if (company != null) {
            this.id = company.getId();
            this.name = company.getName();
            this.businessNumber = company.getBusinessNumber();
            this.ownerName = company.getOwnerName();
            this.zipcode = company.getZipcode();
            this.address = company.getAddress();
            this.addressDetail = company.getAddressDetail();
            this.contact = company.getContact();
            this.email = company.getEmail();
            this.agentName = company.getAgentName();
            this.agentContact = company.getAgentContact();
            this.agentEmail = company.getAgentEmail();
            this.description = company.getDescription();
        }

    }
}
