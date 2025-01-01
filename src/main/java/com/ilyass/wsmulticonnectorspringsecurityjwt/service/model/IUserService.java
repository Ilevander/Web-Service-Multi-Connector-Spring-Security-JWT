package com.ilyass.wsmulticonnectorspringsecurityjwt.service.model;

import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.user.PermissionVo;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.user.RoleVo;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.user.UserVo;

public interface IUserService {
    void save(UserVo user);
    void save(RoleVo role);
    void save(PermissionVo vo);
    RoleVo getRoleByName(String role);
    PermissionVo getPermissionByName(String authority);
}
