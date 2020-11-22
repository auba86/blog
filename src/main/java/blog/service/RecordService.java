package blog.service;

import blog.model.Record;
import blog.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
