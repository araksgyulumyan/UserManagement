package service.admin;

import model.admin.Admin;
import org.apache.commons.lang3.StringUtils;
import repository.admin.AdminRepository;
import repository.admin.AdminRepositoryImpl;
import service.common.exception.ServiceRuntimeException;

import java.util.List;

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
    public Admin getOrCreateAdmin(final String username) {
        assertUsernameNotEmpty(username);
        if (getAdminsCount() == 0) {
            adminRepository.createAdmin(username);
        }
        final List<Admin> admins = adminRepository.findAll();
        // todo admins get(0) may throw index out of bound exp
        return admins.get(0);
    }

    // Utility methods
    private void assertUsernameNotEmpty(final String username) {
        if(StringUtils.isEmpty(username)) {
            throw new ServiceRuntimeException("Admin username should not be empty");
        }
    }

    private Integer getAdminsCount() {
        return adminRepository.getAdminsCount();
    }
}
