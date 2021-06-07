package meo.store.services;

import java.util.List;
import java.util.UUID;

import meo.store.dto.RecordDto;

public interface RecordService {

	List<RecordDto> findAllRecords();

	List<RecordDto> findAllByUserId(UUID uuid);

	RecordDto findRecordById(UUID uuid);

	void deleteRecord(UUID uuid);

	RecordDto saveRecord(RecordDto recordDto);

	RecordDto updateRecord(RecordDto recordDto);

}
