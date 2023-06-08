package eth.bruises.user.vo;

import eth.bruises.sys.domain.Menu;
import eth.bruises.sys.domain.Permission;
import eth.bruises.user.domain.Logininfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * JWT鉴权的有效荷载Vo
 *
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayloadVo {
    private Logininfo logininfo;

    private List<String> permissions;

    private List<Menu> menus;
}
