package com.example.enumpathbugdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DemoController {

    @GetMapping("/{demoEnum}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> demoEnumError(@PathVariable Enum demoEnum) {
        return ResponseEntity.ok().build();
    }

    public enum Enum {
        TYPE_1
    }
}
