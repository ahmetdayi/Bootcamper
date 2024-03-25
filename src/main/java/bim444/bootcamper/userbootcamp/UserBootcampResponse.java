package bim444.bootcamper.userbootcamp;

import bim444.bootcamper.basebootcamp.BaseBootcampResponse;
import bim444.bootcamper.user.UserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UserBootcampResponse(

        String id,

        UserResponse user,
        BaseBootcampResponse baseBootcamp

) {
}
