package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "teaching", schema = "databaseexp", catalog = "")
@IdClass(TeachingEntityPK.class)
public class TeachingEntity implements Serializable {
    private String openTerm;
    private String cid;
    private String tid;
    private String ttime;

    @Id
    @Column(name = "openTerm")
    public String getOpenTerm() {
        return openTerm;
    }

    public void setOpenTerm(String openTerm) {
        this.openTerm = openTerm;
    }

    @Id
    @Column(name = "cid")
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Id
    @Column(name = "tid")
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "ttime")
    public String getTtime() {
        return ttime;
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachingEntity that = (TeachingEntity) o;
        return Objects.equals(openTerm, that.openTerm) &&
                Objects.equals(cid, that.cid) &&
                Objects.equals(tid, that.tid) &&
                Objects.equals(ttime, that.ttime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(openTerm, cid, tid, ttime);
    }
}
