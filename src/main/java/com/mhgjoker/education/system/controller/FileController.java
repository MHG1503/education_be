package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.system.integration.MinioChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/file")
@RequiredArgsConstructor
public class FileController {

    private final MinioChannel minioChannel;

    @PostMapping("/upload")
    public ResponseEntity<?> demoDetail(@RequestPart("bucket") String bucket,
                                        @RequestPart("folder") String folder,
                                        @RequestPart("file") MultipartFile file){
        String rs = minioChannel.upload(bucket,folder,file);
        return ResponseEntity.ok(rs);
    }
}
