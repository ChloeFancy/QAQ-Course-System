package model;

import util.MD5;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "databaseexp", catalog = "")
public class StudentEntity {
    private String sid;
    private String sPassword;
    private String sName;
    private String sClassName;
    private String sSex;
    private Date sBrithday;
    private String pid;

    @Id
    @Column(name = "sid", nullable = false, length = 10)
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "sPassword", nullable = false, length = 40)
    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    @Basic
    @Column(name = "sName", nullable = false, length = 10)
    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Basic
    @Column(name = "sClassName", nullable = false, length = 30)
    public String getsClassName() {
        return sClassName;
    }

    public void setsClassName(String sClassName) {
        this.sClassName = sClassName;
    }

    @Basic
    @Column(name = "sSex", nullable = true, length = 2)
    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    @Basic
    @Column(name = "sBrithday", nullable = true)
    public Date getsBrithday() {
        return sBrithday;
    }

    public void setsBrithday(Date sBrithday) {
        this.sBrithday = sBrithday;
    }

    @Basic
    @Column(name = "pid", nullable = true, length = 7)
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(sPassword,  MD5.getMD5(that.sPassword).toLowerCase())
//                &&
//                Objects.equals(sName, that.sName) &&
//                Objects.equals(sClassName, that.sClassName) &&
//                Objects.equals(sSex, that.sSex) &&
//                Objects.equals(sBrithday, that.sBrithday) &&
//                Objects.equals(pid, that.pid)
                ;
    }


    @Override
    public int hashCode() {

        return Objects.hash(sid, sPassword, sName, sClassName, sSex, sBrithday, pid);
    }
}
