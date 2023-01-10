package com.soleap.cashbook.common.repository;

import com.soleap.cashbook.common.document.DocumentSnapshot;

import java.util.List;

public interface RepositoryListner {
    void onListed(List<DocumentSnapshot> documentSnapshots);
    void onViewed(DocumentSnapshot documentSnapshot);
    void onAdded(DocumentSnapshot documentSnapshot);
    void onChanged(DocumentSnapshot documentSnapshot);
    void onRemoved(DocumentSnapshot documentSnapshot);
}
