package bim444.bootcamper.basebootcamp.patika;

import bim444.bootcamper.basebootcamp.BaseBootcamp;
import bim444.bootcamper.language.Language;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Patika extends BaseBootcamp {

    private String location;
    private Boolean isDead= false;
    private String startDate;
    private String endDate;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private Language language;

}
