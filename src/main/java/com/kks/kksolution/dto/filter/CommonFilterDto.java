package com.kks.kksolution.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonFilterDto {
    private static final Logger log = LoggerFactory.getLogger(CommonFilterDto.class);
    private int page = 0;
    private int size = 10;
    private String sort = "createAt";
    private String order = "desc";

    public Pageable getPageable() {

        Sort.Direction direction = Sort.Direction.fromString(order);

        Sort.Order order = new Sort.Order(direction, sort);
        return PageRequest.of(page, size, Sort.by(order));
    }
}
