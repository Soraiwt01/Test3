<script setup>
import Header from '../assets/components/Header.vue';
import { onBeforeMount, ref, computed } from 'vue';
import { getAnnouncementById, isLoaded } from '../assets/data/data-handler.js';
import { formatDate } from '../assets/utils';
import { useRoute } from 'vue-router';
import { displays } from '../assets/data/announcement';

const announcement = ref('')
const loaded = ref(false)
onBeforeMount(async () => {
  const route = useRoute()
  announcement.value = await getAnnouncementById(route.params.id)
  if (isLoaded(announcement.value)) loaded.value = true
})
const computedDisplayColor = computed(() => announcement.value.announcementDisplay === displays.Y ? 'bg-success text-success' : 'bg-error text-error')

</script>
<template>
  <div v-if="loaded" class="ann-item">
    <Header>Announcement Detail</Header>

    <div class="max-w-lg overflow-hidden bg-base-100 shadow-lg">
      <div class="flex justify-between items-center font-bold text-xl text-gray-100 py-3 px-4 mb-4 bg-[#C1A696] ann-title">
        <p>{{ announcement.announcementTitle }}</p>
        
        <router-link
          class="btn bg-base-100 text-sm bg-opacity-30 text-gray-100 border-0 rounded-lg ann-button hover:bg-[#E4B79D]"
          :to="{ name: 'EditAnnouncement', params: { id: announcement.id } }">Edit
          <br> 👁‍🗨 :  {{ announcement.viewCount }}
        </router-link>
          
          
      </div>
      <p v-html="announcement.announcementDescription" class="text-gray-500 ql-editor mb-4 kanit-light px-6 ann-description"></p>
      <p class="px-6 ann-publish-date"><span class="text-[#C1A696]">Publish Date:</span> {{
        formatDate(announcement.publishDate) }}</p>
      <p class="px-6 ann-close-date"><span class="text-[#C1A696]">Close Date:</span> {{ formatDate(announcement.closeDate)
      }}</p>

      <div class="px-5 pt-4 pb-5 rounded-lg">
        <span span class="inline-block rounded-lg px-3 py-2 text-sm mr-2 bg-opacity-20" :class="computedDisplayColor">
          Display ( <span class="ann-display">{{ announcement.announcementDisplay }}</span> )</span>
        <span class="inline-block px-3 py-2 text-sm mr-2 text-[#FAA497] bg-[#FAA497] bg-opacity-20 rounded-lg ann-category">
          {{ announcement.announcementCategory }}
        </span>
        <router-link
          class="inline-block btn-outline btn-error float-right px-3 py-[0.45rem] text-sm border rounded-lg ann-button"
          to="/admin/announcement">Back</router-link>

      </div>
    </div>
  </div>
</template>
<style scoped></style>