package com.my_ecommerce.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum StatusOrder {

    CREATED,
    FINISHED(CREATED),
    CANCELLED(CREATED);

    private final List<StatusOrder> beforeStatus;

    StatusOrder(StatusOrder... beforeStatus){
        this.beforeStatus = Arrays.asList(beforeStatus);
    }

    public boolean notAllowStatusChangeTo(StatusOrder newStatus) {
        return !newStatus.beforeStatus.contains(this);
    }
}
