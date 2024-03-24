package bim444.bootcamper.coderspace;

import bim444.bootcamper.common.BaseModel;
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
public class Coderspace extends BaseModel {

    private String eventType;

    private String description;

    private String status;
    private Boolean isDead = false;

}
