package com.soleap.cashbook.common.document;

import java.util.List;

public class PagingRecyclerViewData {
    private int currentPage;
    private int totalPages;
    private int totalItems;

    public void setData(List<ViewDocumentSnapshot> data) {
        this.data = data;
    }

    private List<ViewDocumentSnapshot> data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<ViewDocumentSnapshot> getData() {
        return data;
    }
}
