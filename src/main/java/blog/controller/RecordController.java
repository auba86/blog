package blog.controller;

import blog.model.Rating;
import blog.model.Record;
import blog.model.User;
import blog.service.RatingService;
import blog.service.RecordService;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

@Controller
@RequestMapping("/")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private RatingService ratingService;

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

        Rating rating = new Rating();
        rating.setRatingValue(0);
        ratingService.saveRating(rating);
        record.setRating(rating);

        record.setRecordDate(LocalDateTime.now());

        recordService.saveNewRecord(record);
        return "redirect:/";
    }

    @GetMapping("/ratingPlus/{recordId}")
    public String ratingPlus(@PathVariable(value = "recordId") long recordId) {
        Record record = recordService.getRecordById(recordId);
        Rating rating = ratingService.getRatingById(record.getRating().getRatingId());
        rating.setRatingValue(rating.getRatingValue() + 1);
        this.ratingService.saveRating(rating);
        return "redirect:/";
    }

    @GetMapping("/ratingMinus/{recordId}")
    public String ratingMinus(@PathVariable(value = "recordId") long recordId) {
        Record record = recordService.getRecordById(recordId);
        Rating rating = ratingService.getRatingById(record.getRating().getRatingId());
        rating.setRatingValue(rating.getRatingValue() - 1);
        this.ratingService.saveRating(rating);
        return "redirect:/";
    }

    @GetMapping("/")
    public String viewFirstTwoRecords(Model model) {
        List<Record> recordsList = recordService.getAllRecords();
        //Collections.sort(recordsList, (left, right) -> (int) (left.getRecordId() - right.getRecordId()));
        Collections.sort(recordsList, new CustomComparator());
        model.addAttribute("recordsList", recordsList);
        return "index";
    }

    @GetMapping("/allRecordPage")
    public String viewAllRecords(Model model) {
        List<Record> recordsList = recordService.getAllRecords();
        Collections.sort(recordsList, new CustomComparator());
        model.addAttribute("recordsList", recordsList);
        return "allrecords";
    }

    @GetMapping("/manageRecords")
    public String manageRecords(Model model){
        List<Record> recordsList = recordService.getAllRecords();
        model.addAttribute("recordsList", recordsList);
        return "managerecords";
    }

    @GetMapping("/deleteRecord/{recordId}")
    public String deleteRecord(@PathVariable(value = "recordId") long recordId) {
        Record record = recordService.getRecordById(recordId);
        this.recordService.deleteRecordById(recordId);
        this.ratingService.deleteRatingById(record.getRating().getRatingId());
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

    public class CustomComparator implements Comparator<Record>
    {
        @Override
        public int compare(Record o1, Record o2) {
            return o2.getRecordId().compareTo(o1.getRecordId());
        }
    }

}
