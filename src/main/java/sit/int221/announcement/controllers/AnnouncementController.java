package sit.int221.announcement.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.announcement.dtos.AnnouncementDTO;
import sit.int221.announcement.dtos.AnnouncementRequestDTO;
import sit.int221.announcement.dtos.PageDTO;
import sit.int221.announcement.models.Announcement;
import sit.int221.announcement.services.AnnouncementService;
import sit.int221.announcement.utils.Modes;

import java.util.List;


@CrossOrigin(origins={"http://localhost:5173","http://intproj22.sit.kmutt.ac.th/kp1","http://25.38.200.142:5173"})
@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService service;

    @GetMapping("")
    public List<AnnouncementDTO> getAnnouncement(@RequestParam(defaultValue = "admin") Modes mode) {
        return service.getAnnouncement(mode);
    }
    @GetMapping("/all")
    public List<Announcement> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Announcement getAnnouncementById(@PathVariable Integer id,@RequestParam (defaultValue = "false") boolean count) {
        return count ? service.addView(id): service.getAnnouncementById(id)  ;
    }
    @PostMapping("")
    public Announcement addAnnouncement(@Valid @RequestBody AnnouncementRequestDTO announcement) {
        return service.addAnnouncement(announcement);
    }
    @DeleteMapping("/{id}")
    public Announcement deleteAnnouncement(@PathVariable Integer id){
        return service.deleteAnnouncement(id);
    }
    @PutMapping("/{id}")
    public Announcement updateAnnouncement(@PathVariable Integer id,@Valid @RequestBody AnnouncementRequestDTO announcement) {
        return service.updateAnnouncement(id,announcement);
    }
    @GetMapping("/pages")
    public PageDTO<AnnouncementDTO> getAnnouncementpage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "admin") Modes mode,
            @RequestParam(defaultValue = "0") int category
    ){
        return service.getAnnouncementPage(page,size,mode,category);
    }

}
