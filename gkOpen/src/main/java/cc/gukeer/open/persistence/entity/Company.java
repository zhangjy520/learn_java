package cc.gukeer.open.persistence.entity;

import java.io.Serializable;

public class Company implements Serializable {
    private String id;

    private String businessName;

    private String licenceNum;

    private String licenceSite;

    private String corporateName;

    private String corporateIdentity;

    private String capital;

    private String address;

    private String companyPhone;

    private String developerName;

    private String developerPhone;

    private String accessoriesId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }

    public String getLicenceNum() {
        return licenceNum;
    }

    public void setLicenceNum(String licenceNum) {
        this.licenceNum = licenceNum == null ? null : licenceNum.trim();
    }

    public String getLicenceSite() {
        return licenceSite;
    }

    public void setLicenceSite(String licenceSite) {
        this.licenceSite = licenceSite == null ? null : licenceSite.trim();
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName == null ? null : corporateName.trim();
    }

    public String getCorporateIdentity() {
        return corporateIdentity;
    }

    public void setCorporateIdentity(String corporateIdentity) {
        this.corporateIdentity = corporateIdentity == null ? null : corporateIdentity.trim();
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital == null ? null : capital.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone == null ? null : companyPhone.trim();
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName == null ? null : developerName.trim();
    }

    public String getDeveloperPhone() {
        return developerPhone;
    }

    public void setDeveloperPhone(String developerPhone) {
        this.developerPhone = developerPhone == null ? null : developerPhone.trim();
    }

    public String getAccessoriesId() {
        return accessoriesId;
    }

    public void setAccessoriesId(String accessoriesId) {
        this.accessoriesId = accessoriesId == null ? null : accessoriesId.trim();
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
        Company other = (Company) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBusinessName() == null ? other.getBusinessName() == null : this.getBusinessName().equals(other.getBusinessName()))
            && (this.getLicenceNum() == null ? other.getLicenceNum() == null : this.getLicenceNum().equals(other.getLicenceNum()))
            && (this.getLicenceSite() == null ? other.getLicenceSite() == null : this.getLicenceSite().equals(other.getLicenceSite()))
            && (this.getCorporateName() == null ? other.getCorporateName() == null : this.getCorporateName().equals(other.getCorporateName()))
            && (this.getCorporateIdentity() == null ? other.getCorporateIdentity() == null : this.getCorporateIdentity().equals(other.getCorporateIdentity()))
            && (this.getCapital() == null ? other.getCapital() == null : this.getCapital().equals(other.getCapital()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getCompanyPhone() == null ? other.getCompanyPhone() == null : this.getCompanyPhone().equals(other.getCompanyPhone()))
            && (this.getDeveloperName() == null ? other.getDeveloperName() == null : this.getDeveloperName().equals(other.getDeveloperName()))
            && (this.getDeveloperPhone() == null ? other.getDeveloperPhone() == null : this.getDeveloperPhone().equals(other.getDeveloperPhone()))
            && (this.getAccessoriesId() == null ? other.getAccessoriesId() == null : this.getAccessoriesId().equals(other.getAccessoriesId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBusinessName() == null) ? 0 : getBusinessName().hashCode());
        result = prime * result + ((getLicenceNum() == null) ? 0 : getLicenceNum().hashCode());
        result = prime * result + ((getLicenceSite() == null) ? 0 : getLicenceSite().hashCode());
        result = prime * result + ((getCorporateName() == null) ? 0 : getCorporateName().hashCode());
        result = prime * result + ((getCorporateIdentity() == null) ? 0 : getCorporateIdentity().hashCode());
        result = prime * result + ((getCapital() == null) ? 0 : getCapital().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getCompanyPhone() == null) ? 0 : getCompanyPhone().hashCode());
        result = prime * result + ((getDeveloperName() == null) ? 0 : getDeveloperName().hashCode());
        result = prime * result + ((getDeveloperPhone() == null) ? 0 : getDeveloperPhone().hashCode());
        result = prime * result + ((getAccessoriesId() == null) ? 0 : getAccessoriesId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", businessName=").append(businessName);
        sb.append(", licenceNum=").append(licenceNum);
        sb.append(", licenceSite=").append(licenceSite);
        sb.append(", corporateName=").append(corporateName);
        sb.append(", corporateIdentity=").append(corporateIdentity);
        sb.append(", capital=").append(capital);
        sb.append(", address=").append(address);
        sb.append(", companyPhone=").append(companyPhone);
        sb.append(", developerName=").append(developerName);
        sb.append(", developerPhone=").append(developerPhone);
        sb.append(", accessoriesId=").append(accessoriesId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}