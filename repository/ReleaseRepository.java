package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Release;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseRepository extends JpaRepository<Release, Long> {

}
