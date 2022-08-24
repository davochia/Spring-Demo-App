package com.example.Admin.service.AdminServiceImpl;

import com.example.Admin.dto.AdminDto;
import com.example.Admin.exception.AdminNotFoundException;
import com.example.Admin.model.Admin;
import com.example.Admin.repository.AdminRepository;
import com.example.Admin.service.AdminServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminServiceI {

    @Autowired
    private AdminRepository adminRepository;


    /**
     * @param adminDto
     * @return
     */
    @Override
    public AdminDto addAdmin(AdminDto adminDto) {
        if (adminDto == null)return null;
        Admin admin = AdminDto.getAdmin(adminDto);
        return AdminDto.getAdminDto(adminRepository.save(admin));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AdminDto findAdminById(Integer id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(AdminDto::getAdminDto).orElseThrow(() -> new AdminNotFoundException(id));
    }

    /**
     * @return
     */
    @Override
    public List<AdminDto> getAdmins() {
        List<Admin> admins =  adminRepository.findAll();
        List<AdminDto> adminDtos = new ArrayList<AdminDto>();
        admins.forEach(admin -> adminDtos.add(AdminDto.getAdminDto(admin)));

        return adminDtos;
    }

    /**
     * @param id
     * @param adminDto
     * @return
     */
    @Override
    public AdminDto modifyAdmin(Integer id, AdminDto adminDto) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if(!optionalAdmin.isPresent())return null;

        Admin admin = optionalAdmin.get();

        if(adminDto == null) return null;

        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setUsername(adminDto.getUsername());
        admin.setEmail(adminDto.getEmail());

        return AdminDto.getAdminDto(adminRepository.save(admin));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean removeAdmin(Integer id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (!optionalAdmin.isPresent())return false;

        Admin admin = optionalAdmin.get();
        adminRepository.delete(admin);

        return true;
    }
}
