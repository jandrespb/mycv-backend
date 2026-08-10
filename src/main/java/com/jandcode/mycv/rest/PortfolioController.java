package com.jandcode.mycv.rest;

import com.jandcode.mycv.entity.PortfolioResponse;
import com.jandcode.mycv.service.PortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/content")
    public ResponseEntity<PortfolioResponse> getPortfolioContent(
            @RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
        return ResponseEntity.ok(portfolioService.findContentByLanguage(language));
    }
}
