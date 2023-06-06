package eth.bruises.sys.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import eth.bruises.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 实体类：
 * @author bruises
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseDomain{

    private String name;
    private String component;
    private String url;
    private String icon;
    private Integer index;
    private Long parentId;
    private String intro;
    private Boolean state;

    private Menu parent;

    // 没有数据的集合不响应给前端
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Menu> children = new ArrayList<>();
}
