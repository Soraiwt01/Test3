package sit.int221.announcement.dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import sit.int221.announcement.exceptions.validator.CloseAfterPublish;
import sit.int221.announcement.exceptions.validator.EnumValidator;
import sit.int221.announcement.utils.Display;

import java.time.ZonedDateTime;
@Getter @Setter
@CloseAfterPublish
public class AnnouncementRequestDTO {

    @NotBlank
    @Size(min = 1, max = 200)
    private String announcementTitle;

    @NotBlank
    @Size(min = 1, max = 10000)
    private String announcementDescription;
    @FutureOrPresent
    private ZonedDateTime publishDate;
    @Future
    private ZonedDateTime closeDate;

    @EnumValidator(enumClass = Display.class,message = "must be either 'Y' or 'N'")
    private String announcementDisplay;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer viewCount;

    public Display getAnnouncementDisplay() {
        return announcementDisplay == null ? Display.N : Display.valueOf(announcementDisplay);
    }
}
