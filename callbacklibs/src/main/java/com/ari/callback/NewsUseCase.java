package com.ari.callback;

public interface NewsUseCase {

    void executeGetNewsAll(String keyword, String email, int page, int page_size, String type) throws Exception;
}
