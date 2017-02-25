package com.example.database.dao;

import com.example.database.domain.SettingDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
@RestResource(exported=false)
public interface SettingDao extends JpaRepository<SettingDomain,Long> {


}
