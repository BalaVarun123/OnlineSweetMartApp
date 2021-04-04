package com.cg.osm.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.osm.entity.User;

public interface IUserRepository  extends JpaRepository<User,Long>{

}
