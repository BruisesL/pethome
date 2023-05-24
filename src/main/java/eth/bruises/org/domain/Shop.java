package eth.bruises.org.domain;

import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店铺domain
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop extends BaseDomain {
    /**
     * 店铺名称
     */
    private String name;
    /**
     * 店铺电话
     */
    private String tel;
    /**
     * 注册时间
     */
    private String registerTime;
    /**
     * 状态
     */
    private String state;
    /**
     * 店铺地址
     */
    private String address;
    /**
     * 店铺logo
     */
    private String logo;
    /**
     * 管理员ID
     */
    private String adminId;

}
