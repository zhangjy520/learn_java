package cc.gukeer.open.persistence.entity;

import java.io.Serializable;

public class Accessories implements Serializable {
    private String id;

    private String identityPhoto;

    private String logo;

    private String licenceScan;

    private String worksScan;

    private String qualification;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIdentityPhoto() {
        return identityPhoto;
    }

    public void setIdentityPhoto(String identityPhoto) {
        this.identityPhoto = identityPhoto == null ? null : identityPhoto.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getLicenceScan() {
        return licenceScan;
    }

    public void setLicenceScan(String licenceScan) {
        this.licenceScan = licenceScan == null ? null : licenceScan.trim();
    }

    public String getWorksScan() {
        return worksScan;
    }

    public void setWorksScan(String worksScan) {
        this.worksScan = worksScan == null ? null : worksScan.trim();
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification == null ? null : qualification.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Accessories other = (Accessories) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIdentityPhoto() == null ? other.getIdentityPhoto() == null : this.getIdentityPhoto().equals(other.getIdentityPhoto()))
            && (this.getLogo() == null ? other.getLogo() == null : this.getLogo().equals(other.getLogo()))
            && (this.getLicenceScan() == null ? other.getLicenceScan() == null : this.getLicenceScan().equals(other.getLicenceScan()))
            && (this.getWorksScan() == null ? other.getWorksScan() == null : this.getWorksScan().equals(other.getWorksScan()))
            && (this.getQualification() == null ? other.getQualification() == null : this.getQualification().equals(other.getQualification()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIdentityPhoto() == null) ? 0 : getIdentityPhoto().hashCode());
        result = prime * result + ((getLogo() == null) ? 0 : getLogo().hashCode());
        result = prime * result + ((getLicenceScan() == null) ? 0 : getLicenceScan().hashCode());
        result = prime * result + ((getWorksScan() == null) ? 0 : getWorksScan().hashCode());
        result = prime * result + ((getQualification() == null) ? 0 : getQualification().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", identityPhoto=").append(identityPhoto);
        sb.append(", logo=").append(logo);
        sb.append(", licenceScan=").append(licenceScan);
        sb.append(", worksScan=").append(worksScan);
        sb.append(", qualification=").append(qualification);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}