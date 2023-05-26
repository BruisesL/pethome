package eth.bruises.sys.domain;

import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统字典明细
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemDictionaryDetail extends BaseDomain {
    /**
     * 数据字典明细名称
     */
    private String name;

    /**
     * 数据字典明细的类型Id，类型表主键
     */
    private Long typesId;

    /**
     * 对应的数据字典类型
     */
    private SystemDictionaryType sdt;
}
