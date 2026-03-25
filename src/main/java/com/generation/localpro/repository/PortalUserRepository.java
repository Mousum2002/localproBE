package com.generation.localpro.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.localpro.model.PortalUser;



public interface PortalUserRepository extends JpaRepository<PortalUser, Integer>
{

    List<PortalUser> findByEmail(String email);
    List<PortalUser> findByCity(String city);
    List<PortalUser> findByAddress(String address);

    Optional<PortalUser> findByUserName(String userName);


}