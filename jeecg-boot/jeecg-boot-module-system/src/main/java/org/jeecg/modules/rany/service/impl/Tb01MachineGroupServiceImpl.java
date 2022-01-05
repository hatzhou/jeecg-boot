package org.jeecg.modules.rany.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.rany.entity.Tb01MachineGroup;
import org.jeecg.modules.rany.mapper.Tb01MachineGroupMapper;
import org.jeecg.modules.rany.service.ITb01MachineGroupService;
import org.springframework.stereotype.Service;

/**
 * @Description: tb01_machine_group
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Service
public class Tb01MachineGroupServiceImpl extends ServiceImpl<Tb01MachineGroupMapper, Tb01MachineGroup> implements ITb01MachineGroupService {

    /**
     * 获取组信息与机器数信息
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public IPage<Tb01MachineGroup> queryGroupByPage(int pageSize, int pageNo) {
        IPage<Tb01MachineGroup> pageList = null;
        Page<Tb01MachineGroup> page = new Page<Tb01MachineGroup>(pageNo, pageSize);
        pageList = this.baseMapper.queryGroupByPage(page);
        return pageList;
    }
}
