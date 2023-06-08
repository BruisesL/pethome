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
public class PetDetail extends BaseDomain{

    private Long petId;
    /**
     * 领养须知
     */
    private String adoptNotice;
    /**
     * 简介
     */
    private String intro;

}
