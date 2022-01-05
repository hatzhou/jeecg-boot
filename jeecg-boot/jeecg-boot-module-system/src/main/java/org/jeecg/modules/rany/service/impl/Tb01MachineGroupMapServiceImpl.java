package org.jeecg.modules.rany.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.rany.entity.Tb01MachineGroupMap;
import org.jeecg.modules.rany.mapper.Tb01MachineGroupMapMapper;
import org.jeecg.modules.rany.service.ITb01MachineGroupMapService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: tb01_machine_group_map
 * @Author: jeecg-boot
 * @Date:   2021-12-29
 * @Version: V1.0
 */
@Service
public class Tb01MachineGroupMapServiceImpl extends ServiceImpl<Tb01MachineGroupMapMapper, Tb01MachineGroupMap> implements ITb01MachineGroupMapService {

    @Override
    public List<String> getMahineIdsByGroupId(String groupId) {
        return this.baseMapper.getMachineByGroup(groupId);
    }
}
