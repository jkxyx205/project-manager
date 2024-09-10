package com.rick.manager.sys.user.service;

import com.rick.common.http.exception.BizException;
import com.rick.db.service.support.Params;
import com.rick.manager.common.exception.ExceptionCodeEnum;
import com.rick.manager.sys.permission.entity.Permission;
import com.rick.manager.sys.role.entity.Role;
import com.rick.manager.sys.user.dao.UserDAO;
import com.rick.manager.sys.user.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.rick.manager.common.exception.ExceptionCodeEnum.USER_NOT_FOUND;

/**
 * @author Rick.Xu
 * @date 2023/5/28 15:04
 */
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Validated
public class UserService {

    PasswordEncoder passwordEncoder;

    UserDAO userDAO;

    public Optional<User> findByUsername(String username) {
        Optional<User> optional = userDAO.selectByCode(username);
        if (optional.isPresent()) {
            User user = optional.get();
            if (!user.getAvailable()) {
                throw new BizException(ExceptionCodeEnum.USER_NOT_AVAILABLE);
            }

            List<String> authorityList = new ArrayList<>();
            authorityList.add("USER_" + user.getCode());
            for (Role role : user.getRoleList()) {
                authorityList.add("ROLE_" + role.getCode());
                for (Permission permission : role.getPermissionList()) {
                    authorityList.add(permission.getCode());
                }
            }

            user.setAuthorityList(authorityList);
        }

        return optional;
    }

    public boolean checkPassword(Long userId, String password) {
        Optional<String> optional = userDAO.selectSingleValueById(userId, "password", String.class);
        return passwordEncoder.matches(password, optional.orElseThrow(() -> new BizException(USER_NOT_FOUND)));
    }

    public void updatePassword(Long userId, String password) {
        userDAO.update("password", Params.builder(2).pv("password", passwordEncoder.encode(password)).pv("id", userId).build(), "id = :id");
    }

    public Map<Long, String> getIdNameMapping() {
        return userDAO.selectByParamsAsMap(null, "id, name");
    }
}