package bim444.bootcamper.patika;

import bim444.bootcamper.common.BaseModel;
import bim444.bootcamper.language.Language;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Patika extends BaseModel {

    private String location;
    private Boolean isDead= false;
    private String startDate;
    private String endDate;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn
    private Language language;

}
