
import { formatDateString,getDateTime } from "../utils"

const categories = [ 'ทั่วไป','ทุนการศึกษา','หางาน','ฝึกงาน' ]
const displays = { Y:'Y' , N:'N' }

class Announcement {

    constructor(title,description,publishDate,publishTime,closeDate,closeTime,display=false,categoryId = 1,id) {
        this.id = id ?? 0
        this.title = title ?? ''
        this.description = description ?? ''
        this.publishDate = publishDate ?? ''
        this.publishTime = publishTime ?? '06:00'
        this.closeDate = closeDate ?? ''
        this.closeTime = closeTime ?? '18:00'
        this.display = display
        this.categoryId = categoryId
        this.viewCount = 0
    }

    getPublish() { return getDateTime(this.publishDate,this.publishTime) }
    getClose() { return getDateTime(this.closeDate,this.closeTime) }

    getUTCPublish() { return this.getPublish() ? this.getPublish().toISOString() : null }
    getUTCClose() { return this.getClose() ? this.getClose().toISOString() : null}

    fromJSON(announcement) {
        const publish = formatDateString(announcement.publishDate)?.split(' ')
        const close = formatDateString(announcement.closeDate)?.split(' ')
        this.id = announcement.id
        this.title = announcement.announcementTitle
        this.description = announcement.announcementDescription
        this.publishDate = publish?.[0] ?? null
        this.publishTime = publish?.[1] ?? null
        this.closeDate = close?.[0] ?? null
        this.closeTime = close?.[1] ?? null
        this.display = announcement.announcementDisplay === displays.Y ? true : false
        this.categoryId = announcement.categoryId
        this.viewCount = announcement.viewCount ? announcement.viewCount : 0
        return this
    }

    toJSON() {
        return {
            id: this.id,
            announcementTitle: this.title,
            announcementDescription: this.description,
            publishDate: this.getUTCPublish(),
            closeDate: this.getUTCClose(),
            announcementDisplay: this.display ? displays.Y : displays.N,
            categoryId: this.categoryId,
            viewCount: this.viewCount ? this.viewCount : 0
        }
    }
}
export default Announcement
export { categories,displays }