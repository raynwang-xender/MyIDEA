package texas.bean;

import lombok.Data;

@Data
public class Result {
    private String openid;
    private String chip;
    private String havebuy;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"openid\":\"")
                .append(openid).append('\"');
        sb.append(",\"chip\":\"")
                .append(chip).append('\"');
        sb.append(",\"havebuy\":\"")
                .append(havebuy).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
