package com.mhgjoker.education.system.file.export;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseAnalyzerListener<T , E> extends AnalysisEventListener<T> {
    protected final List<T> rows = new ArrayList<>();
    protected final List<E> result = new ArrayList<>();

    public abstract void invoke(T row, AnalysisContext context);

    public List<E> getData() {
        return result;
    }

    Integer parseInt(String num){
        try {
            if(StringUtils.hasText(num)){
                return Integer.parseInt(num);
            }
            return 0;
        }catch (Exception e){
            return 0;
        }
    }
}
