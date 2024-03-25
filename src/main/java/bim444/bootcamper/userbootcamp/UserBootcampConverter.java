package bim444.bootcamper.userbootcamp;

import bim444.bootcamper.basebootcamp.BaseBootcampConverter;
import bim444.bootcamper.user.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserBootcampConverter {
    private final UserConverter userConverter;
    private final BaseBootcampConverter baseBootcampConverter;

    public List<UserBootcampResponse> convert(List<UserBootcamp> fromlist){
        return fromlist.stream()
                .map(from -> new UserBootcampResponse(
                        from.getId(),
                        userConverter.convert(from.getUser()),
                        baseBootcampConverter.convert(from.getBaseBootcamp())))
                .toList();
    }
}
