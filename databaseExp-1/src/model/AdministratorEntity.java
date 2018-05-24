package model;

import util.MD5;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "administrator", schema = "databaseexp", catalog = "")
public class AdministratorEntity {
    private String aid;
    private String aPassword;
    private String aName;

    @Id
    @Column(name = "aid", nullable = false, length = 30)
    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "aPassword", nullable = false, length = 40)
    public String getaPassword() {
        return aPassword;
    }

    public void setaPassword(String aPassword) {
        this.aPassword = aPassword;
    }

    @Basic
    @Column(name = "aName", nullable = true, length = 8)
    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministratorEntity that = (AdministratorEntity) o;
        return Objects.equals(aid, that.aid) &&
                Objects.equals(aPassword, MD5.getMD5(that.aPassword).toLowerCase()) ;
//                &&
//                Objects.equals(aName, that.aName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(aid, aPassword, aName);
    }
}
