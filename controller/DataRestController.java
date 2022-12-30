package com.example.mvcdemo.controller;

import com.example.mvcdemo.model.*;
import com.example.mvcdemo.service.DataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DataRestController {
    private final DataService dataService;

    public DataRestController(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("/api/contributors1")
    public List<Contributor> getContributors() {
        return dataService.getContributors();
    }

    @RequestMapping("/api/issues1")
    public List<Issue> getIssues() {
        return dataService.getIssues();
    }

    @RequestMapping("/api/releases1")
    public List<Release> getReleases() {
        return dataService.getReleases();
    }

    @RequestMapping("/api/commits1")
    public List<Commit> getCommits() {
        return dataService.getCommits();
    }

}