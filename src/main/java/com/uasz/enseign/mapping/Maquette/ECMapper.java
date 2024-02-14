package com.uasz.enseign.mapping.Maquette;
import com.uasz.enseign.dto.Maquette.ECDTO;
import com.uasz.enseign.model.Maquette.EC;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class ECMapper {
    public ECDTO fromEC(EC ec) {
        ECDTO ecDTO = new ECDTO();
        BeanUtils.copyProperties(ec, ecDTO);
        return ecDTO;
    }
    public EC fromECDTO(ECDTO ecDTO) {
        EC ec = new EC();
        BeanUtils.copyProperties(ecDTO, ec);
        return ec;
    }
}
