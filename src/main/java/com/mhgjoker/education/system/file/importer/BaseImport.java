package com.mhgjoker.education.system.file.importer;

import com.alibaba.excel.EasyExcel;
import java.io.InputStream;
import java.util.List;

public abstract class BaseImport<T, E> {

    public String action;
    public abstract BaseAnalyzerListener<T, E> getListener();
    public abstract Class<T> getClazz();

    /**
     * Đọc file Excel, chạy listener, và trả về danh sách dữ liệu sau khi xử lý.
     */

    public List<E> importFile(InputStream file) throws Exception {
        BaseAnalyzerListener<T, E> listener = getListener();
        EasyExcel.read(file, getClazz(), listener)
                .sheet()
                .doRead();
        return listener.getData();
    }
}
