package com.cg.osm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.User;
@Repository
public interface IUserRepository  extends JpaRepository<User,Long>{

}
