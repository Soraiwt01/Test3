<script setup>
import { ref, onMounted, watch, computed } from 'vue';

import Header from '../../assets/components/Header.vue';
import { getUserAnnouncement } from '../../assets/data/data-handler';
import { categories } from '../../assets/data/announcement';
import { TIMEZONE,formatDate } from '../../assets/utils';
import { useAnnounces, modes } from '../../assets/pinia';
import Pagination from '../../assets/components/Pagination.vue';
import router from '../../router';

const user = useAnnounces()
const announcements = ref([])
const loaded = ref(false)
onMounted(async () => {
  await fetch()
  loaded.value = true
})
const fetch = async () => {
  announcements.value = await getUserAnnouncement(user.getMode(), user.getPage()  - 1, user.category)
}
const changeMode = async () => {
  user.changeMode()
  await fetch()
}
const changeCategory = async () => {
  user.setPage(1)
  await fetch()
}
const getButton = computed(() => user.getMode() === modes.CLOSE ? 'Active Announcements':'Closed Announcements')
const onClickDetails = (id) => router.push({ name: 'UserDetails', params: { id: id } })
</script>
 
<template>
  <Header>SIT Announcement System (SAS)</Header>
  <div class="max-w-[40rem]">
    <div class="flex items-center justify-between">
      <p class="text-xl font-bold text-[#C1A696] mr-12">
        Date/Time shown in Timezone: <span class="kanit-light text-base-content">{{ TIMEZONE }}</span>
      </p>
      <button @click="changeMode"
        class="text-md w-44 h-[4.5rem] border-0 btn-success text-white ann-button text-center uppercase shadow-md   rounded-md"
        :class="user.getMode() === modes.CLOSE ?
          'bg-[#C1A696] shadow-[#C1A696] hover:bg-[#E4B79D]' : 'bg-error shadow-error hover:bg-red-500'">
        {{ getButton }}
      </button>

    </div>
    <div class="flex w-full items-center justify-between my-6">
      <p class="text-lg">Category</p>
      <select v-model="user.category" class="select select-bordered w-4/5 ann-category-filter" @change="changeCategory()">
        <option :value="0" >ทั้งหมด</option>
        <option v-for="(category, index) of categories" :value="index + 1" >{{ category }}</option>
      </select>
    </div>
    <div v-show="loaded">
      <div class="flex flex-col items-center" v-if="announcements && announcements?.content?.length > 0">
        <table class="bg-base-100 text-base-content shadow-md w-full">
          <thead>
            <tr class="uppercase text-sm text-gray-100" :class="`bg-[#C1A696]`">
              <th class="py-5 w-16">No.</th>
              <th class="text-left">Title</th>
              <th class="text-center w-32">Close Date</th>
              <th class="text-center w-32">Category</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(announcement, index) in announcements.content" :key="announcement.id"
              class="hover border-base-300 border-b kanit-light ann-item hover:bg-base-300  cursor-pointer"
              @click="onClickDetails(announcement.id)">
              <th class="py-6">{{ index + 1 + (announcements.page * announcements.size) }}</th>
              <td class="ann-title py-1">
                {{ announcement.announcementTitle }}
              </td>
              <td class="ann-close-date px-3 text-center ">{{ formatDate(announcement.closeDate) }}</td>
                            
              <td class="ann-category text-center ">
                <span class="text-[#FAA497] bg-[#FAA497] bg-opacity-20 rounded-lg px-2 py-1 text-sm">
                  {{ announcement.announcementCategory }}
                </span>
              </td>
            </tr>
          </tbody>
        </table>
        <Pagination @fetch="fetch" :total-pages="announcements?.totalPages" :total-elements="announcements?.totalElements"/>
      </div>
      <div v-else class="flex items-center justify-center text-error text-lg">
        No Announcement
      </div>
    </div>
  </div>
</template>
 
<style scoped></style>