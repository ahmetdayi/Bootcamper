package bim444.bootcamper.basebootcamp.techcareer;

import bim444.bootcamper.basebootcamp.BaseBootcamp;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
public class Techcareer extends BaseBootcamp {
    private String eventType;


}
