package org.jeecg.modules.rany.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.rany.entity.Tb01MachineGroup;

/**
 * @Description: tb01_machine_group
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
public interface Tb01MachineGroupMapper extends BaseMapper<Tb01MachineGroup> {

    IPage<Tb01MachineGroup> queryGroupByPage(Page<Tb01MachineGroup> page);
}
