package com.example.vuebackend.repository;

import com.example.vuebackend.pojo.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FarmRepository extends JpaRepository<Farm, String>, JpaSpecificationExecutor<Farm> {

    List<Farm> findAllByNameAndDate(String name, String date);

}
