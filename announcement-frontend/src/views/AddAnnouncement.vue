<script setup>
import Header from "../assets/components/Header.vue";
import AnnouncementForm from "../assets/components/AnnouncementForm.vue";
import Announcement from "../assets/data/announcement.js"
import { ref } from "vue";
import { createAnnouncement } from "../assets/data/data-handler.js"

const announcement = ref(new Announcement())
const create = (announcement,validate) => {
    if (validate) createAnnouncement(announcement.toJSON())
}

</script>

<template>
    <Header>Add Announcement Detail</Header>
    <AnnouncementForm :announcement="announcement" @submit="create" submit-text="Submit"/>
</template>
 
<style scoped></style>