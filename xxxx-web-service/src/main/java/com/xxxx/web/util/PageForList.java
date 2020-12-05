package com.xxxx.web.util;


import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class PageForList<T> {


    private int current;

    private int pageSize;

    private int total;


    public PageForList(int current, int pageSize) {
        this.current = current;
        this.pageSize = pageSize;
    }


    public List<T> getRecords(List<T> list) {

        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        total = list.size();

        if (current < 1 || pageSize < 1) {
            return Collections.emptyList();
        }


        int startIndex = (current - 1) * pageSize;

        if (startIndex + 1 > list.size()) {
            return Collections.emptyList();
        }

        if (startIndex + 1 + pageSize > list.size()) {
            return list.subList(startIndex, list.size());
        }

        return list.subList(startIndex, startIndex + pageSize);
    }


    public static void main(String args[]) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        PageForList<Integer> page = new PageForList<>(1, 10);
        System.out.println(page.getRecords(list));


    }

}
