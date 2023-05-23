package eth.bruises.basic.domain;

import eth.bruises.basic.validation.validateGroups.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * BaseDomain
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDomain implements Serializable {
    @NotNull(groups = {Update.class}, message = "id不可为空")
    private Long id;
}
