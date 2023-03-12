package com.example.vuebackend.service;

import com.example.vuebackend.controller.FindAllFarmQuery;
import com.example.vuebackend.pojo.Farm;
import com.example.vuebackend.repository.FarmRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    public Page<Farm> findAll(FindAllFarmQuery queryParams) {
        Pageable pageable = PageRequest.of(queryParams.getPage(), queryParams.getPageSize(), Sort.by("addDate").descending());
        Specification<Farm> specification = (root, query, builder) -> {
            String name = queryParams.getName();
            String startTime = queryParams.getStartTime();
            String endTime = queryParams.getEndTime();
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(name)) {
                predicates.add(builder.like(root.get("name"), "%" + name + "%"));
            }

            if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
                startTime = getCurrentMonth();
                endTime = getNextMonth();
            }

            predicates.add(builder.greaterThanOrEqualTo(root.get("date").as(Date.class), string2Date(startTime)));
            predicates.add(builder.lessThanOrEqualTo(root.get("date").as(Date.class), string2Date(endTime)));
            return builder.and(predicates.toArray(new Predicate[0]));
        };

        return farmRepository.findAll(specification, pageable);
    }

    public void edit(Farm farm) {
        if (StringUtils.isEmpty(farm.getId())) {
            farm.setId(UUID.randomUUID().toString());
            farm.setAddDate(new Date());
        }
        farm.setUpdateDate(new Date());
        farmRepository.save(farm);
    }

    public void delete(List<String> ids) {
        ids.forEach(id -> farmRepository.deleteById(id));
    }

    private Date string2Date(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            return new Date();
        }
    }


    private String getCurrentMonth() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(now.getTime());
    }

    private String getNextMonth() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + 1);
        now.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(now.getTime());
    }
}
