<script setup>
import Header from "../assets/components/Header.vue";
import AnnouncementForm from "../assets/components/AnnouncementForm.vue";
import Announcement from "../assets/data/announcement.js"
import { ref, onBeforeMount, watch } from "vue";
import { getAnnouncementById, isLoaded, putAnnouncement } from "../assets/data/data-handler.js"
import { useRoute } from 'vue-router';

const announcement = ref('')
onBeforeMount(async () => {
    const route = useRoute()
    announcement.value = await getAnnouncementById(route.params.id)
    if (isLoaded(announcement.value)) {
        announcement.value = new Announcement().fromJSON(announcement.value)
    }
})
// EDITED
let edited = ref(-1)
watch([announcement], async () => { edited.value++ }, { deep: true })
const edit = (announcement,validate) => {
    if (validate) putAnnouncement(announcement.toJSON())
}
</script>
<template>
    <Header>Edit Announcement Detail</Header>
    <AnnouncementForm :announcement="announcement" @submit="edit" submit-text="Edit" :disabled-submit="edited <= 0"/>
</template>
 
<style scoped></style>