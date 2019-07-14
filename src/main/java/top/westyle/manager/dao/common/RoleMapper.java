package top.westyle.manager.dao.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.westyle.manager.entity.common.Role;

public interface RoleMapper extends BaseMapper<Role> {
    int deleteByPrimaryKey(String id);

    //int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}