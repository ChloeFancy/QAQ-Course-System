package model;

import util.MD5;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher", schema = "databaseexp", catalog = "")
public class TeacherEntity {
    private String tid;
    private String tPassword;
    private String tName;
    private String tSex;
    private String tEducation;
    private String tTitle;
    private String did;

    @Id
    @Column(name = "tid", nullable = false, length = 8)
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "tPassword", nullable = false, length = 40)
    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }

    @Basic
    @Column(name = "tName", nullable = false, length = 10)
    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    @Basic
    @Column(name = "tSex", nullable = true, length = 20)
    public String gettSex() {
        return tSex;
    }

    public void settSex(String tSex) {
        this.tSex = tSex;
    }

    @Basic
    @Column(name = "tEducation", nullable = true, length = 10)
    public String gettEducation() {
        return tEducation;
    }

    public void settEducation(String tEducation) {
        this.tEducation = tEducation;
    }

    @Basic
    @Column(name = "tTitle", nullable = true, length = 10)
    public String gettTitle() {
        return tTitle;
    }

    public void settTitle(String tTitle) {
        this.tTitle = tTitle;
    }

    @Basic
    @Column(name = "did", nullable = true, length = 2)
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
        TeacherEntity that = (TeacherEntity) o;
        return Objects.equals(tid, that.tid) &&
                Objects.equals(tPassword,  MD5.getMD5(that.tPassword).toLowerCase())
//                &&
//                Objects.equals(tName, that.tName) &&
//                Objects.equals(tSex, that.tSex) &&
//                Objects.equals(tEducation, that.tEducation) &&
//                Objects.equals(tTitle, that.tTitle) &&
//                Objects.equals(did, that.did)
                ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(tid, tPassword, tName, tSex, tEducation, tTitle, did);
    }
}
