package memberapi.model.authorization;


import lombok.Data;

@Data
public class CmpOauth2Response {

    State state;

    String accessToken;


    public enum State{
        OK("200"), NoContent("204"), BadRequest("400") ,Unauthorized("401");

        private String code;

        State(String code) {
            this.code = code;
        }
    }

}
