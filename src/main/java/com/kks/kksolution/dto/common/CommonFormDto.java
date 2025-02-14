package com.kks.kksolution.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommonFormDto {
    private int page;
    private int size;
    private String sort;
    private String order;
}
