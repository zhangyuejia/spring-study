package com.zhangyj.spring.business.newcontroller.controller;

import cn.hutool.core.io.FileUtil;
import com.zhangyj.spring.common.util.StrUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Map;

@RestController
public class DocumentController {

    @PostMapping("/accept")
    public String acceptDocument(@RequestBody byte[] documentData) {
        try {
            // 找到分隔符 0x01 的位置
            int separatorIndex = -1;
            for (int i = 0; i < documentData.length; i++) {
                if (documentData[i] == 0x01) {
                    separatorIndex = i;
                    break;
                }
            }

            if (separatorIndex == -1) {
                return "Invalid document data format";
            }

            // 提取文件名和文档内容
            byte[] docNameBytes = Arrays.copyOfRange(documentData, 0, separatorIndex);
            byte[] docContentBytes = Arrays.copyOfRange(documentData, separatorIndex + 1, documentData.length);

            // 解码 Base64 编码的文件名
            String docNameBase64 = new String(docNameBytes, StandardCharsets.UTF_8);
            String docName = new String(Base64.getDecoder().decode(docNameBase64), StandardCharsets.UTF_8);

            String uploadDir = "your-upload-directory"; // 上传目录，根据实际情况修改
            new File(uploadDir).mkdirs();
            File file = new File(uploadDir, docName);
            System.out.println(file.getAbsolutePath());
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(docContentBytes);
                return "Document saved successfully";
            } catch (IOException e) {
                e.printStackTrace();
                return "Error saving document";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing document data";
        }
    }

    @PostMapping("/acceptExcel")
    public String acceptExcel(@RequestBody Map<String, Object> map) {
        try {
            String docName = (String) map.get("fileName");

            String uploadDir = "your-upload-directory"; // 上传目录，根据实际情况修改
            new File(uploadDir).mkdirs();

            File file = new File(uploadDir, docName);
            System.out.println(file.getAbsolutePath());
            String xmlContent = (String) map.get("xmlContent");
            FileUtil.writeString(xmlContent, file, Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing document data";
        }
        return "Document saved successfully";
    }

    @PostMapping("/acceptDir")
    public String acceptDir(@RequestBody Map<String, Object> map) {
        try {
            byte[] n = StrUtils.convertListToByteArray((List<Integer>) map.get("n"));
            String fileName = StrUtils.decompressString(n);
            String uploadDir = "C:\\Projects\\one-click-executor"; // 上传目录，根据实际情况修改
            new File(uploadDir).mkdirs();

            File file = new File(uploadDir, fileName);
            System.out.println(file.getAbsolutePath());
            byte[] c = StrUtils.convertListToByteArray((List<Integer>) map.get("c"));
            String content = StrUtils.decompressString(c);
            FileUtil.writeString(content, file, Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing document data";
        }
        return "Document saved successfully";
    }

}