package com.sparta.schedulemanagement;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Collections;
import java.util.function.Function;

public interface Page<T> extends Slice<T> {

    static <T> Page<T> empty(Pageable pageable){
        return (Page<T>) new PageImpl<>(Collections.emptyList(), pageable, 0);
    }

    int getTotalPages();
    long getTotalElements();

    <U> Page<U> map(Function<? super T, ? extends U> converter);
}
