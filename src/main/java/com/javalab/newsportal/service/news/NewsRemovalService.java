package com.javalab.newsportal.service.news;

import java.util.List;

public interface NewsRemovalService {

    void remove(Long id);
    void removeList(List<Long> ids);
}
