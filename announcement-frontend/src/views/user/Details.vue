<script setup>
import Header from "../../assets/components/Header.vue";
import { onBeforeMount, ref } from "vue";
import { useRoute } from "vue-router";
import { getAnnouncementById, isLoaded } from "../../assets/data/data-handler.js";
import { formatDate } from "../../assets/utils";
import { displays } from "../../assets/data/announcement";
const loaded = ref(false);
const announcement = ref("");
onBeforeMount(async () => {
    const route = useRoute();
    announcement.value = await getAnnouncementById(route.params.id,true);
    announcement.value = announcement.value?.announcementDisplay === displays.N ? null : announcement.value
    loaded.value = isLoaded(announcement.value,true)
})
</script>
<template>
    <div v-if="loaded" class="ann-item">
        <Header> User Detail</Header>
        <div class="overflow-hidden bg-base-100 shadow-lg">
            <p class="font-bold text-xl text-gray-100 py-5 w-96 px-4 bg-[#C1A696] ann-title">
                {{ announcement.announcementTitle }}
            </p>
            <p class="px-6 py-5 text-error">
                Closed on: <span class="ann-close-date">{{ formatDate(announcement.closeDate) }}</span>
            </p>
            <p  class="text-gray-500 mb-4 kanit-light px-6 ann-description" v-html="announcement.announcementDescription">
        
            </p>
            <div class="px-5 pt-4 pb-5 rounded-lg">
                <span class="inline-block px-3 py-2 text-sm mr-2 text-[#FAA497] bg-[#FAA497] bg-opacity-20 rounded-lg ann-category">
                    {{ announcement.announcementCategory }}
                </span>
                <router-link
                    class="inline-block btn-outline btn-error float-right px-3 py-[0.45rem] text-sm border rounded-lg ann-button"
                    to="/announcement">Back</router-link>
            </div>
        </div>
    </div>
</template>
<style scoped></style>
