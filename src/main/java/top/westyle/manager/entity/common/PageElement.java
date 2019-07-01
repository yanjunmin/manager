package top.westyle.manager.entity.common;

import java.util.Date;

public class PageElement {
    private String id;

    private String pageElementName;

    private Date caretedTime;

    private Date updatedTime;

    private String createdBy;

    private String updatedBy;

    private String valid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPageElementName() {
        return pageElementName;
    }

    public void setPageElementName(String pageElementName) {
        this.pageElementName = pageElementName == null ? null : pageElementName.trim();
    }

    public Date getCaretedTime() {
        return caretedTime;
    }

    public void setCaretedTime(Date caretedTime) {
        this.caretedTime = caretedTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }
}