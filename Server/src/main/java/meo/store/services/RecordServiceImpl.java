package meo.store.services;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import meo.store.dto.RecordDto;
import meo.store.persistance.domain.Record;
import meo.store.persistance.domain.Product;
import meo.store.persistance.repositories.RecordRepository;
import meo.store.persistance.repositories.ProductRepository;
import meo.store.utils.mapper.RecordMapper;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

	private final RecordRepository repository;

	private final ProductRepository productRepository;

	private final RecordMapper mapper;

	@Override
	public List<RecordDto> findAllRecords() {
		return null;
	}

	@Override
	public List<RecordDto> findAllByUserId(UUID uuid) {
		return repository.findAllByUserId(uuid)
				.stream()
				.map(Optional::orElseThrow)
				.map(mapper::recordToDto)
				.collect(Collectors.toList());
	}

	@Override
	public RecordDto findRecordById(UUID uuid) {
		return null;
	}

	@Override
	public void deleteRecord(UUID uuid) {

	}

	@Override
	public RecordDto saveRecord(RecordDto recordDto) {
		Record record = new Record();

		record.setId(UUID.randomUUID());
		record.setUserId(recordDto.getUserId());
		record.setProductId(recordDto.getProductId());
		record.setRatingByUser(recordDto.getRatingByUser());
		record.setCreatedAt(LocalDateTime.now());

		Product product = productRepository.findById(record.getProductId()).orElseThrow();
		product.setRating((product.getRating() + record.getRatingByUser()) / 2);
		productRepository.save(product);

		return mapper.recordToDto(repository.save(record));
	}

	@Override
	public RecordDto updateRecord(RecordDto recordDto) {
		return null;
	}
}
