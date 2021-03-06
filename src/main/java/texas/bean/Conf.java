package texas.bean;

import lombok.Data;

@Data
public class Conf {
    private String date;
    private String totalbuy;
    private String chip;
    private String money;
    private String times;
    private String enable;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"date\":\"")
                .append(date).append('\"');
        sb.append(",\"totalbuy\":\"")
                .append(totalbuy).append('\"');
        sb.append(",\"chip\":\"")
                .append(chip).append('\"');
        sb.append(",\"money\":\"")
                .append(money).append('\"');
        sb.append(",\"times\":\"")
                .append(times).append('\"');
        sb.append(",\"enable\":\"")
                .append(enable).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
