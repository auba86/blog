package blog.service;

import blog.model.Record;
import blog.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public void saveNewRecord(Record record) {
        recordRepository.save(record);
    }

    public List<Record> getAllRecords() {
        return (List<Record>) recordRepository.findAll();
    }

    public void deleteRecordById(long recordId) {
        this.recordRepository.deleteById(recordId);
    }

    public Record getRecordById(long recordId) {
        Optional<Record> optional = recordRepository.findById(recordId);
        Record record = null;
        if (optional.isPresent()) {
            record = optional.get();
        } else {
            throw new RuntimeException("Record not found for id ::" + recordId);
        }
        return record;
    }

    public void saveRecord(Record record) {
        this.recordRepository.save(record);
    }

    public void updateRecord(Record postRecord) {
        Record databaseRecord = getRecordById(postRecord.getRecordId());
        databaseRecord.setRecordTitle(postRecord.getRecordTitle());
        databaseRecord.setRecordDescription(postRecord.getRecordDescription());
        saveRecord(databaseRecord);
    }
}
