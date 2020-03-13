package truongtx.nws.weatherapp.application.roomDataBase.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class History {
    @PrimaryKey
    @NonNull
    public String giohientai;
    @ColumnInfo(name = "thanhpho")
    public String thanhpho;
    @ColumnInfo(name = "ngayhientai")
    public String ngayhientai;
    @ColumnInfo(name = "nhietDoTemp")
    public double nhietDoTemp;
    @ColumnInfo(name = "dogioDeg")
    public String dogioDeg;
    @ColumnInfo(name = "tocdogioSpeed")
    public String tocdogioSpeed;
    @ColumnInfo(name = "doamHumidity")
    public String doamHumidity;
    @ColumnInfo(name = "trangthaiDescription")
    public String trangthaiDescription;
    @ColumnInfo(name = "iconSql")
    public String iconSql;

    public String getGiohientai() {
        return giohientai;
    }

    public void setGiohientai(String giohientai) {
        this.giohientai = giohientai;
    }

    public String getThanhpho() {
        return thanhpho;
    }

    public void setThanhpho(String thanhpho) {
        this.thanhpho = thanhpho;
    }

    public String getNgayhientai() {
        return ngayhientai;
    }

    public void setNgayhientai(String ngayhientai) {
        this.ngayhientai = ngayhientai;
    }

    public double getNhietDoTemp() {
        return nhietDoTemp;
    }

    public void setNhietDoTemp(double nhietDoTemp) {
        this.nhietDoTemp = nhietDoTemp;
    }

    public String getDogioDeg() {
        return dogioDeg;
    }

    public void setDogioDeg(String dogioDeg) {
        this.dogioDeg = dogioDeg;
    }

    public String getTocdogioSpeed() {
        return tocdogioSpeed;
    }

    public void setTocdogioSpeed(String tocdogioSpeed) {
        this.tocdogioSpeed = tocdogioSpeed;
    }

    public String getDoamHumidity() {
        return doamHumidity;
    }

    public void setDoamHumidity(String doamHumidity) {
        this.doamHumidity = doamHumidity;
    }

    public String getTrangthaiDescription() {
        return trangthaiDescription;
    }

    public void setTrangthaiDescription(String trangthaiDescription) {
        this.trangthaiDescription = trangthaiDescription;
    }

    public String getIconSql() {
        return iconSql;
    }

    public void setIconSql(String iconSql) {
        this.iconSql = iconSql;
    }
}
