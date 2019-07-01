package top.westyle.manager.service;

import top.westyle.manager.entity.common.Power;
import top.westyle.manager.entity.common.RolePower;

import java.util.List;

/**
 *权限service
 */
public interface PowerService {
    /**
     * 添加权限
     * @param power
     * @return
     */
    int addPower(Power power);

    /**
     * 更新权限
     * @param power
     * @return
     */
    int updatePower(Power power);

    /**
     * 查询权限
     * @param power
     * @return
     */
    List<Power> findPower(Power power);

    /**
     * 添加角色权限管理表信息
     * @param rolePower
     * @return
     */
    int addRolePower(RolePower rolePower);

    /**
     * 更新角色权限关联表信息
     * @param rolePower
     * @return
     */
    int updateRolePower(RolePower rolePower);

    /**
     * 查询角色权限管理表信息
     * @param rolePower
     * @return
     */
    List<RolePower> findRolePower(RolePower rolePower);
}
