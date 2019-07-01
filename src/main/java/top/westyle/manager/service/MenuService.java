package top.westyle.manager.service;

import top.westyle.manager.entity.common.Menu;

import java.util.List;

/**
 * 菜单信息管理服务
 */
public interface MenuService {
    /**
     * 添加菜单信息
     * @param menu
     * @return
     */
    int addMenu(Menu menu);

    /**
     * 更新菜单信息
     * @param menu
     * @return
     */
    int updateMenu(Menu menu);

    /**
     * 批量插入菜单信息
     * @param menus
     * @return
     */
    int insertBatchMenu(List<Menu> menus);

    /**
     * 根据条件查询菜单信息
     * @param menu
     * @return
     */
    List<Menu> findByCondition(Menu menu);
}
