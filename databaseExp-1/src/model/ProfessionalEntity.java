package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "professional", schema = "databaseexp", catalog = "")
public class ProfessionalEntity {
    private String pid;
    private String pName;
    private String did;

    @Id
    @Column(name = "pid")
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "pName")
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @Basic
    @Column(name = "did")
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessionalEntity that = (ProfessionalEntity) o;
        return Objects.equals(pid, that.pid) &&
                Objects.equals(pName, that.pName) &&
                Objects.equals(did, that.did);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pid, pName, did);
    }
}
