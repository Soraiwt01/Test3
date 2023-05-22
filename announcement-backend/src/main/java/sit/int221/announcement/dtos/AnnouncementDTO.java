package sit.int221.announcement.dtos;

import lombok.Getter;
import lombok.Setter;
import sit.int221.announcement.utils.Display;

import java.time.ZonedDateTime;

@Getter @Setter
public class AnnouncementDTO {
    private Integer id;
    private String announcementTitle;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private Display announcementDisplay;
    private String announcementCategory;
    private Integer viewCount;

}
