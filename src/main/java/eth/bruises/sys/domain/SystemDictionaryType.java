package eth.bruises.sys.domain;

import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统字典类型
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemDictionaryType extends BaseDomain {
    /**
     * 数据字典类型编码
     */
    private String sn;
    /**
     * 数据字典类型名称
     */
    private String name;
}
