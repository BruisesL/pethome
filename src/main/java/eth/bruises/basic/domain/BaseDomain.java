package eth.bruises.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * BaseDomain
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDomain implements Serializable {
    private Long id;
}
