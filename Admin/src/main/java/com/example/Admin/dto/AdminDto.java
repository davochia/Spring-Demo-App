package com.example.Admin.dto;

import com.example.Admin.model.Admin;
import lombok.Data;

@Data
public class AdminDto {
    private Integer id;
    private String username;
    private String lastName;
    private String firstName;
    private String email;


    public static AdminDto getAdminDto(Admin admin){
        AdminDto adminDto = new AdminDto();

        adminDto.setId(admin.getId());
        adminDto.setUsername(admin.getUsername());
        adminDto.setFirstName(admin.getFirstName());
        adminDto.setLastName(admin.getLastName());
        adminDto.setEmail(admin.getEmail());

        return adminDto;
    }

    public static Admin getAdmin(AdminDto adminDto){
        Admin admin = new Admin();

        admin.setId(adminDto.getId());
        admin.setUsername(adminDto.getUsername());
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setEmail(adminDto.getEmail());

        return admin;
    }
}
