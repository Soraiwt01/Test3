<script setup>
import { computed } from 'vue';
import { useAnnounces } from '../pinia';
const announces = useAnnounces()
const props = defineProps({totalPages: { required: true} ,totalElements: {  required: true} })
const emits = defineEmits(["fetch"])
const pages = (size = 10) => computed(() => {
  const show = Math.min(size, props.totalPages);
  const min = Math.min(announces.getPage() - Math.floor(show/2) - Math.floor(size/2.5) , props.totalPages - show + 1)
  const first = Math.max(min,1);
  return [...Array(show)].map((_, i) => i + first);

}).value
const next = () => {
    announces.addPage()
    fetchPage()
}
const previous = () => {
    announces.substractPage()
    fetchPage()
}
const number = (selectPage) => {
    announces.setPage(selectPage)
    fetchPage()
}
const fetchPage = () => emits('fetch',announces.getPage())
const selectedPage = (pageIndex) => computed(() => pageIndex === announces.getPage()).value

</script>
<template>
    <div class="btn-group mt-12" v-if="totalElements > 5">
        <button class="btn bg-[#C1A696] border-0 hover:bg-[#E4B79D] shadow-md text-gray-100 ann-page-prev disabled:bg-gray"
            :disabled="announces.getPage() === 1" @click="previous()">prev</button>
        <button class="btn bg-base-100 border-0 hover:bg-[#FFF0E7] shadow-md text-base-content w-12"
            v-for="[index, i] of pages().entries()" @click="number(i)"
            :class="`disabled:bg-[#C1A696] disabled:text-gray-100 ann-page-${index}`" :disabled="selectedPage(i)">{{ i }}
        </button>
        <button class="btn bg-[#C1A696] border-0 hover:bg-[#E4B79D] shadow-md text-gray-100 ann-page-next disabled:bg-gray"
            :disabled="announces.getPage() === totalPages" @click="next()">next</button>
    </div>
</template>