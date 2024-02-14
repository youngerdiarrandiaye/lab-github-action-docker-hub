package com.uasz.enseign.mapping.Repartition;
import com.uasz.enseign.dto.Repartition.RepartitionDTO;
import com.uasz.enseign.model.Repartition.Repartition;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class RepartitionMapper {
    public RepartitionDTO fromRepartition(Repartition repartition){
        RepartitionDTO repartitionDTO = new RepartitionDTO();
        BeanUtils.copyProperties(repartition,repartitionDTO);
        return  repartitionDTO;
    }
    public Repartition fromRepartitionDTO(RepartitionDTO repartitionDTO){
        Repartition repartition = new Repartition();
        BeanUtils.copyProperties(repartitionDTO, repartition);
        return  repartition;
    }
}
