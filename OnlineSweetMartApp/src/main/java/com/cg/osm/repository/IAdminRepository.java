package com.cg.osm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.Admin;
@Repository
public interface IAdminRepository extends JpaRepository<Admin,Integer>{

}
