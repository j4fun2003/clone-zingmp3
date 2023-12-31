package com.m2m.zing.repository;

import com.m2m.zing.model.History;
import com.m2m.zing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByUserOrderByListenDateDesc(User user);
}
