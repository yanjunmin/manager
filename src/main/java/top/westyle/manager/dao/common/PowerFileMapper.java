package top.westyle.manager.dao.common;

import top.westyle.manager.entity.common.PowerFile;

public interface PowerFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(PowerFile record);

    int insertSelective(PowerFile record);

    PowerFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PowerFile record);

    int updateByPrimaryKey(PowerFile record);
}