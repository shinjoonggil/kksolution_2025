package com.kks.kksolution.dto.filter;

import com.kks.kksolution.dto.common.CommonFilterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContentFilterDto extends CommonFilterDto {
    private String keyword;
}
