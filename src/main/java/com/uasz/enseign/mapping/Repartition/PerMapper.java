package com.uasz.enseign.mapping.Repartition;
import com.uasz.enseign.dto.Repartition.PERDTO;
import com.uasz.enseign.model.Repartition.PER;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class PerMapper{
    public PERDTO fromPER(PER per){
        PERDTO perdto = new PERDTO();
        BeanUtils.copyProperties(per,perdto);
        return  perdto;
    }
    public PER fromPERDTO(PERDTO perdto){
        PER per = new PER();
        BeanUtils.copyProperties(perdto, per);
        return  per;
    }
}
