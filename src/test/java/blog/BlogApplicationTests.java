package blog;

import blog.model.Record;
import blog.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static javafx.scene.input.KeyCode.L;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BlogApplicationTests {

	private RecordService recordService;

	@Test
	void contextLoads() {
	}

	@Test
	void getRecordById(){
		Record record = new Record();
		record.setRecordId(50L);
		record.setRecordTitle("Pirmas");
		record.setRecordDescription("Labai gerai");
		recordService.saveNewRecord(record);

		Record record1 = recordService.getRecordById(record.getRecordId());

		assertEquals("Pirmas", record1.getRecordTitle());

	}

}
