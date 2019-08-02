package top.westyle.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.westyle.manager.entity.common.*;

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
    List<Power> findPower(Power power, Page<Power> page);

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
     * 查询角色权限关联表信息
     * @param rolePower
     * @return
     */
    List<RolePower> findRolePower(RolePower rolePower, Page<RolePower> page);

    /**
     * 添加菜单权限关联表信息
     * @param powerMenu
     * @return
     */
    int addMenuPower(PowerMenu powerMenu);

    /**
     * 更新菜单权限关联表信息
     * @param powerMenu
     * @return
     */
    int updateMenuPower(PowerMenu powerMenu);

    /**
     * 查询菜单权限关联表信息
     * @param powerMenu
     * @return
     */
    List<PowerMenu> findMenuPower(PowerMenu powerMenu, Page<PowerMenu> page);

    /**
     * 添加页面元素关联权限表
     * @param powerPage
     * @return
     */
    int addPowerPage(PowerPage powerPage);

    /**
     * 更新页面元素关联权限表信息
     * @param powerPage
     * @return
     */
    int updatePowerPage(PowerPage powerPage);

    /**
     * \查询页面元素关联权限表信息
     * @param powerPage
     * @param page
     * @return
     */
    List<PowerPage> findPagePower(PowerPage powerPage, Page<PowerPage> page);

    /**
     * 添加文件权限关联表信息
     * @param powerFile
     * @return
     */
    int addPowerFile(PowerFile powerFile);

    /**
     * 更新文件权限关联表信息
     * @param powerFile
     * @return
     */
    int updatePowerFile(PowerFile powerFile);

    /**
     * 查询文件权限关联表信息
     * @param powerFile
     * @param page
     * @return
     */
    List<PowerFile> findFilePower(PowerFile powerFile, Page<PowerFile> page);

    /**
     * 添加权限功能操作关联表信息
     * @param powerOperation
     * @return
     */
    int addPowerOperator(PowerOperation powerOperation);

    /**
     * 添加权限功能操作关联表信息
     * @param powerOperation
     * @return
     */
    int updatePowerOperator(PowerOperation powerOperation);

    /**
     * 查询权限功能操作关联表信息
     * @param powerOperation
     * @param page
     * @return
     */
    List<PowerOperation> findPowerOperation(PowerOperation powerOperation, Page<PowerOperation> page);
}
