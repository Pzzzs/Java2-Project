package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Contributor;
import com.example.mvcdemo.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributorRepository extends JpaRepository<Contributor, Long> {

}
