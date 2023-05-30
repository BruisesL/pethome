package eth.bruises.basic.controller;

import eth.bruises.org.service.IShopService;
import eth.bruises.org.vo.ShopStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * echarts报表controller
 *
 * @author bruises
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private IShopService shopService;

    @GetMapping("/shopStatistics")
    public ShopStatisticsVo shopStatistics() {
        return shopService.getStateStatistics();
    }
}
