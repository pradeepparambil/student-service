package com.teksenz.studentservice.web.mapper;

import com.teksenz.studentservice.domain.Referee;
import com.teksenz.studentservice.web.model.RefereeDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface RefereeMapper {
    RefereeDto refereeToDto(Referee referee);
    Referee dtoToReferee(RefereeDto refereeDto);

}
