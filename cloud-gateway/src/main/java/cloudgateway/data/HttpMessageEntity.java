package cloudgateway.data;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;



import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Base64;


@Slf4j
@Entity
@Setter
@Getter
@Table(name="MEMBER_HTTP_MESSAGES", catalog="ECP_ADMIN")
public class HttpMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="HTTP_MESSAGE_ID")
    private Long id;

    @Column(name="HOST")
    private String host;

    @Enumerated(EnumType.STRING)
    @Column(name="DIVISION")
    private Division division;

    @Column(name="PATH")
    private String path;

    @Column(name="METHOD")
    private String method;

    @Column(name="STATUS")
    private String status;

    @Column(name="REMOTE_IP_ADDR")
    private String remoteIpAddr;

    @Column(name="USER_ID")
    private String userId;

    @Column(name="PERFORMANCE")
    private Long performance;

    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;

    public void setUserIdFromAuthorization(String authorization) {
        if(authorization == null){
            this.userId = "Not login";
            return;
        }
        String[] accessToken = authorization
                .replace("bearer ", "")
                .split("\\.");

        log.debug("accessToken:\n"
                +"header\n"
                + accessToken[0]+"\n"
                +"payload\n"
                +accessToken[1]);

        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(accessToken[1]));

        log.debug("decoded payload:  " + payload);

        JSONObject jsonObject= new JSONObject(payload);
        userId = (String)jsonObject.get("preferred_username");


    }

    public void setDivisionFromPath(String path) {

        if(path == null)
            return;

        if(path.contains("/admin/api"))
            division = Division.AdminApi;

        if(path.contains("/admin/app"))
            division = Division.AdminApp;

        if(path.contains("/user/api"))
            division = Division.UserApi;

        if(path.contains("/user/app"))
            division = Division.UserApp;

        if(path.contains("/dashboard/api"))
            division = Division.DashboardApi;

        if(path.contains("/dashboard/app"))
            division = Division.DashboardApp;

        if(path.contains("/openstack/api"))
            division = Division.OpenstackApi;

        if(path.contains("/openstack/app"))
            division = Division.OpenstackApp;

    }

    enum Division{
        AdminApi, AdminApp, UserApi, UserApp, DashboardApi, DashboardApp, OpenstackApi, OpenstackApp
    }


}
