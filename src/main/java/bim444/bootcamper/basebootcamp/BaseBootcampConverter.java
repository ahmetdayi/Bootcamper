package bim444.bootcamper.basebootcamp;

import org.springframework.stereotype.Component;

@Component
public class BaseBootcampConverter {

    public BaseBootcampResponse convert(BaseBootcamp from){
        return new BaseBootcampResponse(
                from.getId(),
                from.getName(),
                from.getImgUrl(),
                from.getLink(),
                from.getDeadline());
    }
}
