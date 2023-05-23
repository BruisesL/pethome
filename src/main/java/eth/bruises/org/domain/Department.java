package eth.bruises.org.domain;

import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 部门domain
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseDomain {
    /**
     * 部门编码
     */
    @NotBlank(message = "部门编码不可为空")
    private String sn;
    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不可为空")
    private String name;
    /**
     * 部门层级
     */
    private String dirPath;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 部门经理ID
     */
    private Long managerId;
    /**
     * 上级部门ID
     */
    private Long parentId;

    /**
     * 部门经理
     */
    @Valid
    private Employee manager;
    /**
     * 上级部门
     */
    @Valid
    private Department parent;
    /**
     * 下级部门
     */
    @Valid
    private List<Department> children;
}
