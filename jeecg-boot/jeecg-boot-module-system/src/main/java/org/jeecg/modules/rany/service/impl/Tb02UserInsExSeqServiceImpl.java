package org.jeecg.modules.rany.service.impl;

import org.jeecg.modules.rany.entity.Tb02UserInsExSeq;
import org.jeecg.modules.rany.mapper.Tb02UserInsExSeqMapper;
import org.jeecg.modules.rany.service.ITb02UserInsExSeqService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: tb02_user_ins_ex_seq
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
@Service
public class Tb02UserInsExSeqServiceImpl extends ServiceImpl<Tb02UserInsExSeqMapper, Tb02UserInsExSeq> implements ITb02UserInsExSeqService {

    @Resource
    private Tb02UserInsExSeqMapper tb02UserInsExSeqMapper;

    @Override
    public List<Tb02UserInsExSeq> queryInsByToken(String token) {
        return tb02UserInsExSeqMapper.queryInsByToken(token);
    }
}
