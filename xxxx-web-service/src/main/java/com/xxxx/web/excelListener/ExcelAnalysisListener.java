package com.xxxx.web.excelListener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelAnalysisListener<T> extends AnalysisEventListener<T> {


    List<T> dataList = new ArrayList<>();

    public List<T> getData() {
        return dataList;
    }


    @Override
    public void invoke(T t, AnalysisContext analysisContext) {

        dataList.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("excel提取数据量:{}", dataList.size());
    }
}
