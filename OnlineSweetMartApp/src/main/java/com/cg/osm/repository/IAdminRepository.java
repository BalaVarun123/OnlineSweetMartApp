package com.cg.osm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.osm.entity.Admin;

public interface IAdminRepository extends JpaRepository<Admin,Integer>{

}
