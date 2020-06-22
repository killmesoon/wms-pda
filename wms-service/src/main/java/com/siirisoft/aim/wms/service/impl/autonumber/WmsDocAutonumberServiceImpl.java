package com.siirisoft.aim.wms.service.impl.autonumber;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.autonumber.WmsDocAutonumber;
import com.siirisoft.aim.wms.mapper.autonumber.WmsDocAutonumberMapper;
import com.siirisoft.aim.wms.service.autonumber.IWmsDocAutonumberService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 单据自动编码设置; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-06-09
 */
@Service
public class WmsDocAutonumberServiceImpl extends ServiceImpl<WmsDocAutonumberMapper, WmsDocAutonumber> implements IWmsDocAutonumberService {

}
