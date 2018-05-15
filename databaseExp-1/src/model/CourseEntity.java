package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course", schema = "databaseexp", catalog = "")
public class CourseEntity {
    private String cid;
    private String cName;
    private Integer cCredit;
    private Integer cTotalHours;

    @Id
    @Column(name = "cid")
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "cName")
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "cCredit")
    public Integer getcCredit() {
        return cCredit;
    }

    public void setcCredit(Integer cCredit) {
        this.cCredit = cCredit;
    }

    @Basic
    @Column(name = "cTotalHours")
    public Integer getcTotalHours() {
        return cTotalHours;
    }

    public void setcTotalHours(Integer cTotalHours) {
        this.cTotalHours = cTotalHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return Objects.equals(cid, that.cid) &&
                Objects.equals(cName, that.cName) &&
                Objects.equals(cCredit, that.cCredit) &&
                Objects.equals(cTotalHours, that.cTotalHours);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cid, cName, cCredit, cTotalHours);
    }
}
