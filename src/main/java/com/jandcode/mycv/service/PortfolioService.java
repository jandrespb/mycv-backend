package com.jandcode.mycv.service;

import com.jandcode.mycv.entity.PortfolioResponse;

public interface PortfolioService {
    PortfolioResponse findContentByLanguage(String language);
}
