package com.generation.localpro.Service;

import java.util.List;

import com.generation.localpro.model.PortalUser;
import com.generation.localpro.model.Role;

public interface PortalUserService {

    PortalUser create(PortalUser portalUser);

    PortalUser update(Integer id, PortalUser portalUser);

    PortalUser getById(Integer id);

    List<PortalUser> getAll();

    List<PortalUser> getByRole(Role role);

    void delete(Integer id);
}
