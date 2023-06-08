package eth.bruises.pet.service;

import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.pet.domain.SearchMasterMsg;
import eth.bruises.basic.service.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Bruises
 * @since 2023-06-08
 */
public interface ISearchMasterMsgService extends BaseService<SearchMasterMsg> {

    /**
     * 发布寻主信息
     * @param msg
     * @return
     */
    void publish(SearchMasterMsg msg);
}
