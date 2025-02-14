package com.kks.kksolution.service;

import com.kks.kksolution.dto.common.DeleteFormDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CommonServiceImpl<ENTITY,ITEM,FORM,FILTER> {
    Page<ITEM> getPageList(FILTER filter);

    ITEM getEmptyData();
    ITEM getData(UUID id);
    UUID dataProcess(FORM form);
    void deleteData(DeleteFormDto form);
}
