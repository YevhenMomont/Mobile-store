package meo.store.utils.mapper;

import org.mapstruct.Mapper;

import meo.store.dto.RecordDto;
import meo.store.persistance.domain.Record;

@Mapper(componentModel = "spring")
public interface RecordMapper {

	Record dtoToRecord(RecordDto recordDto);

	RecordDto recordToDto(Record record);

}
