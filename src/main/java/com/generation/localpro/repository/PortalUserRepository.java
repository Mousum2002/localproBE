package com.generation.localpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.localpro.model.PortalUser;
import com.generation.localpro.model.Role;


public interface PortalUserRepository extends JpaRepository<PortalUser, Integer>
{
    List<PortalUser> findByFirstName(String firstName);
    List<PortalUser> findByLastName(String lastName);
    List<PortalUser> findByEmail(String email);
    List<PortalUser> findByCity(String city);
    List<PortalUser> findByAddress(String address);
    List<PortalUser> findByRole(Role role);
    List<PortalUser> findByBio(String bio);
    List<PortalUser> findByX(int x);
    List<PortalUser> findByY(int y);    

}