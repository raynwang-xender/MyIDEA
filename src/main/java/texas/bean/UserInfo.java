package texas.bean;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserInfo {
    private String openid;
    private String nickname;
    private String gender;
    private String avatarurl;
    private String country;
    private String city;
    private String province;
    private String language;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"openid\":\"")
                .append(openid).append('\"');
        sb.append(",\"nickname\":\"")
                .append(nickname).append('\"');
        sb.append(",\"gender\":\"")
                .append(gender).append('\"');
        sb.append(",\"avatarurl\":\"")
                .append(avatarurl).append('\"');
        sb.append(",\"country\":\"")
                .append(country).append('\"');
        sb.append(",\"city\":\"")
                .append(city).append('\"');
        sb.append(",\"province\":\"")
                .append(province).append('\"');
        sb.append(",\"language\":\"")
                .append(language).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
