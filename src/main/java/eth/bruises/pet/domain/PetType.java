package eth.bruises.pet.domain;

import java.math.BigDecimal;
import java.util.Date;
import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetType extends BaseDomain{

    private String name;
    private String description;
    /**
     * 父类型id
     */
    private Long parentId;


}
