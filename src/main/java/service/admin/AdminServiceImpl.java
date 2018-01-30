package service.admin;

import dataconnection.exception.IllegalOperationException;
import model.admin.Admin;
import repository.admin.AdminRepository;
import repository.admin.AdminRepositoryImpl;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 9:55 PM
 */
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl() {
        this.adminRepository = new AdminRepositoryImpl();
    }

    @Override
    public Admin getOrCreateAdmin(String username) {
        if (adminRepository.getAdminsCount() > 1) {
            throw new IllegalOperationException("Error occurred during creating admin");
        }
        return adminRepository.createAdmin("admin");
    }
}
