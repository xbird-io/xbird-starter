package com.github.zhycn.id.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.zhycn.id.domain.LeafSegmentEntity;

@Repository
public interface LeafSegmentRepository extends JpaRepository<LeafSegmentEntity, String> {

}
