package blog.controller;

import blog.model.Record;
import blog.model.User;
import blog.service.RecordService;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping("/")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/newRecordForm")
    public String newRecordForm(Model model) {
        Record record = new Record();
        model.addAttribute("record", record);
        return "addrecord";
    }

    @GetMapping("/testPage")
    public String testPage() {
        return "test";
    }

    @PostMapping("/saveNewRecord")
    public String saveNewRecord(@ModelAttribute(value="record") Record record){
        User user = new User();
        user.setUserId(1L);
        record.setUser(user);

        record.setRecordDate(LocalDateTime.now());

        recordService.saveNewRecord(record);
        return "redirect:/";
    }

    @GetMapping("/")
    public String viewAllRecords(Model model) {
        List<Record> recordsList = recordService.getAllRecords();
        model.addAttribute("recordsList", recordsList);
        return "index";
    }

    @GetMapping("/manageRecords")
    public String manageRecords(Model model){
        List<Record> recordsList = recordService.getAllRecords();
        model.addAttribute("recordsList", recordsList);
        return "managerecords";
    }

    @GetMapping("/deleteRecord/{recordId}")
    public String deleteRecord(@PathVariable(value = "recordId") long recordId) {
        this.recordService.deleteRecordById(recordId);
        return "redirect:/";
    }

    @GetMapping("/recordUpdateForm/{recordId}")
    public String recordUpdateForm(@PathVariable(value = "recordId") long recordId, Model model){
        Record record = recordService.getRecordById(recordId);
        model.addAttribute("record", record);
        return "updaterecord";
    }

    @PostMapping("/updateOldRecord")
    public String updateOldRecord(@ModelAttribute("record") Record postRecord){
        recordService.updateRecord(postRecord);
        return "redirect:/";
    }

}
