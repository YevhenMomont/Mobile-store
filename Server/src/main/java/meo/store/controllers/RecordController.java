package meo.store.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import meo.store.controllers.url.RecordUrl;
import meo.store.dto.RecordDto;
import meo.store.services.RecordService;

@RestController
@RequiredArgsConstructor
public class RecordController {

	private final RecordService service;

	@GetMapping(value = RecordUrl.RESOURCE_NAME)
	public List<RecordDto> getRecords() {
		return service.findAllRecords();
	}

	@PostMapping(value = RecordUrl.CREATE)
	public RecordDto saveRecord(@Valid @RequestBody RecordDto RecordDto) {
		return service.saveRecord(RecordDto);
	}
}
