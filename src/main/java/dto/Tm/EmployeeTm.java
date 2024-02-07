package dto.Tm;

public class EmployeeTm {
    private String emid;
    private String emname;
    private String ememail;
    private String empassword;

    public EmployeeTm(String emid, String emname, String ememail, String empassword) {
        this.emid = emid;
        this.emname = emname;
        this.ememail = ememail;
        this.empassword = empassword;
    }

    public String toString() {
        return "employee{" +
                "emid='" + emid + '\'' +
                "emname='" + emname + '\'' +
                "ememail='" + ememail + '\'' +
                ", empassword='" + empassword +
                '}';
    }

    public String getEmid() {
        return emid;
    }
    public void setEmid(String emid) {
        this.emid = emid;
    }

    public String getEmname() {
        return emname;
    }
    public void setEmname(String emname) {
        this.emname = emname;
    }

    public String getEmemail() {
        return ememail;
    }
    public void setEmemail(String ememail) {
        this.ememail = ememail;
    }

    public String getEmpassword() {
        return empassword;
    }
    public void setEmpassword(String empassword) {
        this.empassword = empassword;
    }
}
