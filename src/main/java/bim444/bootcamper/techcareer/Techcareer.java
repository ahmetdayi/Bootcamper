package bim444.bootcamper.techcareer;

import bim444.bootcamper.common.BaseModel;
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
public class Techcareer extends BaseModel {
    private String eventType;


}
