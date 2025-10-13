package com.mhgjoker.education.system.file.export;

import com.alibaba.excel.EasyExcel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public abstract class BaseImporter<T, E> {

    public abstract BaseAnalyzerListener<T, E> getListener();
    public abstract Class<T> getClazz();

    /**
     * Đọc file Excel, chạy listener, và trả về danh sách dữ liệu sau khi xử lý.
     */

    public List<E> importFile(MultipartFile file) throws Exception {
        BaseAnalyzerListener<T, E> listener = getListener();
        EasyExcel.read(file.getInputStream(), getClazz(), listener)
                .sheet()
                .doRead();
        return listener.getData();
    }
}
