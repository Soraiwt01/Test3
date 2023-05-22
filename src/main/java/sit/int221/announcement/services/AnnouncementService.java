package sit.int221.announcement.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.announcement.dtos.AnnouncementDTO;
import sit.int221.announcement.dtos.AnnouncementRequestDTO;
import sit.int221.announcement.dtos.PageDTO;
import sit.int221.announcement.exceptions.list.ItemNotFoundException;
import sit.int221.announcement.models.Announcement;
import sit.int221.announcement.models.Category;
import sit.int221.announcement.repositories.AnnouncementRepository;
import sit.int221.announcement.utils.Display;
import sit.int221.announcement.utils.ListMapper;
import sit.int221.announcement.utils.Modes;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository repository;
    @Autowired
    private CategoryService categories;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ListMapper listMapper;

    public List<AnnouncementDTO> getAnnouncement(Modes mode) {
        Pageable pageable = Pageable.unpaged();
        List<Announcement> announcement = findAllByMode(pageable,mode,0).getContent();
        List<AnnouncementDTO> dtos = listMapper.mapList(announcement, AnnouncementDTO.class, mapper);
        Collections.reverse(dtos);
        return dtos;
    }
    public List<Announcement> getAll(){
        return repository.findAll();
    }

    public Announcement getAnnouncementById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("announcementId", id + " does not exists"));
    }
    public Announcement addAnnouncement(AnnouncementRequestDTO post){
        return updateAnnouncement(null,post);
    }

    public Announcement deleteAnnouncement(Integer id){
        Announcement announcement = getAnnouncementById(id);
        repository.delete(announcement);
        return announcement;
    }

    public Announcement updateAnnouncement(Integer id,AnnouncementRequestDTO post){
        Announcement announcement = mapper.map(post,Announcement.class);
        Category category = categories.getCategoryById(post.getCategoryId());
        announcement.setId(id);
        announcement.setCategory(category);
        System.out.println(announcement.getViewCount());
        return repository.saveAndFlush(announcement);
    }
    public PageDTO<AnnouncementDTO> getAnnouncementPage(int page, int size, Modes mode, int category){
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"id"));
        Page<Announcement> announcement = findAllByMode(pageable,mode,category);
        return listMapper.toPageDTO(announcement, AnnouncementDTO.class,mapper);
    }

    public Page<Announcement> findAllByMode(Pageable pageable, Modes mode, int category) {
        Page<Announcement> announcement = new PageImpl<>(new ArrayList<>(),pageable,0);
        Display show = Display.Y;
        switch (mode) {
            case admin -> announcement = repository.findAll(pageable);
            case active -> announcement = repository.findActive(show,category, ZonedDateTime.now(), pageable);
            case close -> announcement = repository.findClose(show,category, ZonedDateTime.now(),pageable);
        }
        return announcement;
    }
    public Announcement addView(Integer id) {
        Announcement announcement = getAnnouncementById(id);
        announcement.setViewCount(announcement.getViewCount()+1);
        repository.saveAndFlush(announcement);
        return announcement;
    }
}
