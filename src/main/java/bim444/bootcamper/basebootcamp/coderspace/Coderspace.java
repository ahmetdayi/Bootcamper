package bim444.bootcamper.basebootcamp.coderspace;

import bim444.bootcamper.basebootcamp.BaseBootcamp;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Coderspace extends BaseBootcamp {

    private String eventType;

    private String description;

    private String status;
    private Boolean isDead = false;

}
