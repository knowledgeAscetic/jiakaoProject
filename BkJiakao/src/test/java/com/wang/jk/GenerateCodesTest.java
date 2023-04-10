package com.wang.jk;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class GenerateCodesTest {
    private static final String BASE_DIR = "F:\\CoderMJLee\\private\\Codes\\05_JX\\BkJiakao\\src\\main\\java";

    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");

        // 设置模板文件的存放目录
        File dir = new ClassPathResource("/").getFile();
        cfg.setDirectoryForTemplateLoading(dir);

        // 写
        write(cfg, "ExamPlaceCourse");
    }

    private static void write(Configuration cfg, String ...types) throws Exception {
        String srcDir = BASE_DIR + "/com/mj/jk/";
        for (String type : types) {
            write("Controller.ftl", srcDir + "controller/*Controller.java", type, cfg);
            write("Service.ftl", srcDir + "service/*Service.java", type, cfg);
            write("ServiceImpl.ftl", srcDir + "service/impl/*ServiceImpl.java", type, cfg);
            write("Mapper.ftl", srcDir + "mapper/*Mapper.java", type, cfg);
            write("Query.ftl", srcDir + "pojo/query/*Query.java", type, cfg);
        }
    }

    private static void write(String ftlName, String outputDir, String type, Configuration cfg)
            throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("type", type);
        outputDir = outputDir.replace("*", type);
        File destFile = new File(outputDir);
        if (destFile.exists()) {
            return;
        }
        try (Writer out = new FileWriter(destFile)) {
            // 获取模板文件
            Template temp = cfg.getTemplate(ftlName);
            // 合并数据到模板文件中
            temp.process(data, out);
        }
    }
}
