package com.kks.kksolution.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyFilterDto extends CommonFilterDto{
    private String keyword;
}
