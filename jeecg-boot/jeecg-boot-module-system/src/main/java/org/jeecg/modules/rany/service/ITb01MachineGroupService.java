package org.jeecg.modules.rany.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.rany.entity.Tb01MachineGroup;

/**
 * @Description: tb01_machine_group
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
public interface ITb01MachineGroupService extends IService<Tb01MachineGroup> {

    /**
     * 获取设备组，并关联组内机器数信息，属于关联查询。
     * @param pageSize
     * @param pageNo
     * @return
     */
    IPage<Tb01MachineGroup> queryGroupByPage(int pageSize, int pageNo);
}
