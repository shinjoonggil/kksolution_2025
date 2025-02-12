package com.kks.kksolution.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TemplateHelper {
    private static final Logger log = LoggerFactory.getLogger(TemplateHelper.class);
    private final HttpServletRequest request;

    /**
     * @param column
     * @return String ?sort=name.desc , ?sort=name.asc
     */
    public String getSortLink(String column) {
        String url = request.getRequestURL().toString();


        String linkOrder = "desc";
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");

        if (sort != null && sort.equals(column)) {
            if (order.equals("desc")) {
                linkOrder = "asc";
            }
        }
        return String.format("%s?sort=%s&order=%s", url, column, linkOrder);
    }

    public String getSortClass(String column) {
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        String className = "";
        if (column.equals(sort) && order != null ) {
            className = order;
        }
        return className;
    }
}
