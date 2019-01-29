package top.westyle.manager.entity;

/**
 * 权限实体
 * @author yjm
 * @date 2019-1-29 21:50:30
 */
public class Permission extends BaseEntity {
    private String id;
    private String name;
    private String parentId;
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
