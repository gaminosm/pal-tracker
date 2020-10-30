package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeEntryController {
    TimeEntryRepository repo;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        repo = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry newEntry = repo.create(timeEntryToCreate);
        ResponseEntity<TimeEntry> body = ResponseEntity.created(null).body(newEntry);
        return body;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry entry = repo.find(id);
        ResponseEntity<TimeEntry> response;
        if (entry == null) {
            response = ResponseEntity.notFound().build();
        }
        else {
            response = ResponseEntity.ok().body(entry);

        }

        return response;
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> entries = repo.list();
        ResponseEntity<List<TimeEntry>> response = ResponseEntity.ok().body(entries);
        return response;
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry expected) {
        TimeEntry entry = repo.update(id, expected);
        ResponseEntity response;
        if(entry == null) {
            response = ResponseEntity.notFound().build();
        }
        else {
            response = ResponseEntity.ok().body(entry);
        }
        return response;
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        repo.delete(id);
        ResponseEntity response = ResponseEntity.noContent().build();
        return response;
    }
}
