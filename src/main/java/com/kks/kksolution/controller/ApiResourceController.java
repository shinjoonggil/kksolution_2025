package com.kks.kksolution.controller;

import com.kks.kksolution.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/resource")
@Slf4j
@RequiredArgsConstructor
public class ApiResourceController {
    private final ResourceService resourceService;

}
