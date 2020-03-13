package truongtx.nws.weatherapp.presentation.ui.screen.history.mvp;

public class HistoryModel {
    String giohientai;
    String thanhpho;
    String ngayhientai;
    double nhietDoTemp;
    String dogioDeg;
    String tocdogioSpeed;
    String doamHumidity;
    String trangthaiDescription;
    String iconSql;

    public static final String TABLE_HISTORY_SQL = "historyCity";
    public static final String COLUMN_THANHPHOCHON = "thanhphochon";
    public static final String COLUMN_GIO = "giohientai";
    public static final String COLUMN_NGAY = "ngayhientai";
    public static final String COLUMN_NHIETDO = "nhietdo";
    public static final String COLUMN_DOAM = "doam";
    public static final String COLUMN_DOGIO = "dogio";
    public static final String COLUMN_TOCDOGIO = "tocdogio";
    public static final String COLUMN_TRANGTHAI = "trangthai";
    public static final String COLUMN_ICON = "icon";

    public static final String CREATE_TABLE_HISTORY = " CREATE TABLE " + TABLE_HISTORY_SQL + "("
            + COLUMN_GIO + " VARCHAR PRIMARY KEY, " + COLUMN_THANHPHOCHON + " VARCHAR, " + COLUMN_NGAY
            + " VARCHAR, " + COLUMN_NHIETDO + " VARCHAR, " + COLUMN_DOAM + " VARCHAR, " + COLUMN_DOGIO + "" +
            " VARCHAR, " + COLUMN_TOCDOGIO + " VARCHAR, " + COLUMN_TRANGTHAI + " VARCHAR, " + COLUMN_ICON + " VARCHAR) ";


}