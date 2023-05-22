import { createRouter,createWebHistory } from "vue-router";
import Main from "../views/Main.vue"
import Details from "../views/Details.vue"
import PageNotFound from "../views/PageNotFound.vue"
import AddAnnouncement from "../views/AddAnnouncement.vue"
import EditAnnouncement from "../views/EditAnnouncement.vue"
import UserAnnouncement from "../views/user/Announcement.vue"
import UserDetails from "../views/user/Details.vue"
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes:[
        {
            path: '/',
            redirect: { name: 'UserAnnouncement' }
        },
        {
            path: '/announcement/',
            name: 'UserAnnouncement',
            component: UserAnnouncement
        },
        {
            path: '/announcement/:id',
            name: 'UserDetails',
            component: UserDetails
        },
        {
            path: '/admin/announcement/',
            name: 'Announcement',
            component: Main,
        },
        {
            path: '/admin/announcement/:id',
            name: 'AnnouncementDetails',
            component: Details,
        },
        {
            path: '/:notfoundpath(.*)',
            name: 'PageNotFound',
            component: PageNotFound        
        },
        {
            path: '/admin/announcement/add',
            name: 'AddAnnouncement',
            component: AddAnnouncement,
        },
        {
            path: '/admin/announcement/:id/edit',
            name: 'EditAnnouncement',
            component: EditAnnouncement,
        }
]
})
export default router