package eth.bruises.sys.service.impl;

import eth.bruises.sys.domain.Menu;
import eth.bruises.sys.mapper.MenuMapper;
import eth.bruises.sys.service.IMenuService;
import eth.bruises.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 业务实现类：
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询菜单无限级树数据
     * @return
     */
    @Override
    public List<Menu> menuTree() {
        // 1.查询所有
        List<Menu> menus = menuMapper.selectAll();
        // 2.转换为map，为了方便取值
        Map<Long, Menu> menuMap = menus.stream().collect(Collectors.toMap(Menu::getId, m -> m));

        // 3.创建一个新的集合，用于响应
        List<Menu> menuTree = new ArrayList<>();

        // 4.组装数据到新集合中
        for (Menu menu : menus) {
            if (menu.getParentId() == null){
                menuTree.add(menu);
            }else {
                Menu parent = menuMap.get(menu.getParentId());
                if (parent != null){
                    parent.getChildren().add(menu);
                }
            }
        }
        return menuTree;
    }
}
