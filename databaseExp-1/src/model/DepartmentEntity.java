package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department", schema = "databaseexp", catalog = "")
public class DepartmentEntity {
    private String did;
    private String dName;
    private String dOfficePlace;
    private String dTelephone;

    @Id
    @Column(name = "did")
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Basic
    @Column(name = "dName")
    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    @Basic
    @Column(name = "dOfficePlace")
    public String getdOfficePlace() {
        return dOfficePlace;
    }

    public void setdOfficePlace(String dOfficePlace) {
        this.dOfficePlace = dOfficePlace;
    }

    @Basic
    @Column(name = "dTelephone")
    public String getdTelephone() {
        return dTelephone;
    }

    public void setdTelephone(String dTelephone) {
        this.dTelephone = dTelephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(did, that.did) &&
                Objects.equals(dName, that.dName) &&
                Objects.equals(dOfficePlace, that.dOfficePlace) &&
                Objects.equals(dTelephone, that.dTelephone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(did, dName, dOfficePlace, dTelephone);
    }
}
