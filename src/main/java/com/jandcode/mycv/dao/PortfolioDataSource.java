package com.jandcode.mycv.dao;

import com.jandcode.mycv.entity.PortfolioResponse;

public interface PortfolioDataSource {

    PortfolioResponse findContentByLanguage(String language);
}
