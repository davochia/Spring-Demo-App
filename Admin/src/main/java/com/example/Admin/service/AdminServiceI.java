package com.example.Admin.service;

import com.example.Admin.dto.AdminDto;

import java.util.List;

public interface AdminServiceI {

    AdminDto addAdmin(AdminDto adminDto);
    AdminDto findAdminById(Integer id) ;
    List<AdminDto> getAdmins( );
    AdminDto modifyAdmin(Integer id, AdminDto adminDto);
    Boolean removeAdmin(Integer id) ;
}
