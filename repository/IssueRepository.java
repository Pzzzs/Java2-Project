package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Commit;
import com.example.mvcdemo.model.Issue;
import com.example.mvcdemo.model.Release;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}
