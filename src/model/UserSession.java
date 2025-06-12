package model;

public class UserSession {
    private int userId;
    private String role;
    private Integer pelangganId;
    private Integer adminId;

    public UserSession(int userId, String role, Integer pelangganId, Integer adminId) {
        this.userId = userId;
        this.role = role;
        this.pelangganId = pelangganId;
        this.adminId = adminId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) { // ✅ Ditambahkan
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public Integer getPelangganId() {
        return pelangganId != null ? pelangganId : -1;
    }

    public void setPelangganId(Integer pelangganId) { // ✅ Ditambahkan
        this.pelangganId = pelangganId;
    }

    public Integer getAdminId() {
        return adminId != null ? adminId : -1;
    }

    public void setAdminId(Integer adminId) { // ✅ Ditambahkan
        this.adminId = adminId;
    }
}
