package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository<Commit, Long> {

}
