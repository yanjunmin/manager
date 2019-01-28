package top.westyle.manager.entity;

import java.util.Date;

/**
 * 角色
 */
public class Role extends BaseEntity {
    private String id;
    private String parentId;//父角色ID
    private String name;//角色名称
    private String desc;//角色描述
    private Date ceatedTime;//创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCeatedTime() {
        return ceatedTime;
    }

    public void setCeatedTime(Date ceatedTime) {
        this.ceatedTime = ceatedTime;
    }
}
