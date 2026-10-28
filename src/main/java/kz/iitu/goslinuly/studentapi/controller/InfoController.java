package kz.iitu.goslinuly.studentapi.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Value("${app.student-name}")
    private String studentName;

    @Value("${app.group}")
    private String group;

    @Value("${app.student-id}")
    private String studentId;

    private final BuildProperties build;

    public InfoController(BuildProperties build) {
        this.build = build;
    }

    @GetMapping("/")
    public Map<String, String> info() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("application", "goslinuly-student-api");
        info.put("student", studentName);
        info.put("group", group);
        info.put("studentId", studentId);
        info.put("version", build.getVersion());
        return info;
    }
}
