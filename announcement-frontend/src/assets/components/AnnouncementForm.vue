<script setup>
import DateTimeForm from "./DateTimeForm.vue";
import { categories } from "../data/announcement.js";
import { ref, onMounted } from "vue";
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';

const quilEditor = ref('')
const validateDesc = ref(null)

const props = defineProps({
  announcement: { required: true },
  submitText: String,
  disabledSubmit: { type: Boolean, default: false },
});
const emits = defineEmits(["submit"])
const MAX_TITLE = 200;
const MAX_DESCRIPTION = 10000;
const validate = ref(false);
const validates = ref({
  title: true,
  description: true,
  publish: true,
  close: true
})
const errorText = ref({
  publish: '',
  close: '',
  title: '',
  description: ''
})

const submit = () => {
  validateText()
  validateDate()
  validate.value = Object.values(validates.value).every((item) => item);
  emits("submit", props.announcement, validate.value)
};
const sendErrorText = (type, text, byValidate) => {
  validates.value[type] = byValidate
  errorText.value[type] = byValidate ? "" : text
}
const validateText = () => {
  const title = props.announcement.title.trim()
  const description = validateDesc.value.trim()

  if (!title) sendErrorText("title", "Please fill the title", Boolean(title))
  else sendErrorText("title", "Title size must lower than 200", title.length <= 200)
  if (!description) sendErrorText("description", "Please fill the description", Boolean(description))
  else if (props.announcement.description) sendErrorText("description", "Description size must lower than 10000", props.announcement.description.length <= 10000)
}
const validateDate = () => {
  const current = new Date().getTime()

  const publish = props.announcement.getPublish()?.getTime()
  const close = props.announcement.getClose()?.getTime()
  if (publish) sendErrorText("publish", "Publish must be a future date", publish > current)
  if (close) sendErrorText("close", "Close must be a future date", close >= current)
  if (publish && close) sendErrorText("close", "Close must be later than publish", close > publish)
}

const changeText = (e) => validateDesc.value = quilEditor.value.getQuill().getText();

</script>
<template>
  <div class="space-y-1 w-1/4">
    <div class="-mt-6">
      <p>
        Title
        <span class="text-sm text-gray-500">({{ announcement.title?.length }}/{{ MAX_TITLE }})</span>
        <span v-show="!validates.title" class="text-sm text-red-500">{{ ` ${errorText.title}` }}</span>
      </p>
      <input @click="validates.title = true" type="text" :maxlength="MAX_TITLE" v-model="announcement.title"
        class="input input-bordered w-full ann-title placeholder:text-gray-500"
        :class="{ 'outline outline-red-500': !validates.title }" />
    </div>
    <div class="space-y-2">
      <p>Category</p>
      <select v-model="announcement.categoryId" class="select select-bordered w-full ann-category">
        <option v-for="(category, index) of categories" :value="index + 1">
          {{ category }}
        </option>
      </select>
    </div>
    <div class="space-y-2">
      <p>
        Description
        <span class="text-sm text-gray-500">({{ validateDesc?.trim()?.length ?? 0 }}/{{ MAX_DESCRIPTION }})</span>
        <span v-show="!validates.description" class="text-sm text-red-500">{{ ` ${errorText.description}` }}</span>

      </p>
      <div class="  ann-description rounded-xl " :class="{ 'outline outline-red-500': !validates.description }">
        <QuillEditor theme="snow" toolbar="full" @click="validates.description = true"
          v-model:content="announcement.description" class="rounded-b-xl bg-white h-32 ann-description" contentType="html"
          @text-change="changeText" ref="quilEditor" />
      </div>
      <!-- <textarea
    
        v-model="announcement.description"
        :maxlength="MAX_DESCRIPTION"
        class="input input-bordered w-full h-32 py-2 ann-description placeholder:text-gray-500 resize-none"
        placeholder="Your description details."
        :class="{ 'outline outline-red-500' : !validates.description }">
      </textarea> -->
    </div>

      <DateTimeForm   date-text="Publish Date" @date="(e) => (announcement.publishDate = e.target.value)"
      @time="(e) => (announcement.publishTime = e.target.value)" :date="announcement.publishDate"
      :time="announcement.publishTime" :max-date="announcement.closeDate" :time-publish="announcement.publishTime"
      :validate="validates.publish" :error-text="errorText.publish" />


    <DateTimeForm date-text="Close Date" :is-close="true" @date="(e) => (announcement.closeDate = e.target.value)"
      @time="(e) => (announcement.closeTime = e.target.value)" :date="announcement.closeDate"
      :time="announcement.closeTime" :min-date="announcement.publishDate" :time-close="announcement.closeTime"
      :validate="validates.close" :error-text="errorText.close" />
  

    <div class="space-y-s w-full ">
      <p c>Display</p>
      <div class="flex">
        <input type="checkbox" class="ann-display checkbox mr-2" v-model="announcement.display" />
        <h3>Check to show this announcement</h3>
      </div>
    </div>

    <div class="flex  w-full">
      <button class="btn border-0 bg-[#C1A696] ann-button text-gray-100 w-44 hover:bg-[#E4B79D] disabled:bg-base-100"
        @click="submit()" :disabled="disabledSubmit">
        {{ submitText }}
      </button>
      <router-link class="btn btn-error ann-button w-44 ml-10 hover:bg-red-500 border-0 text-gray-100"
        :to="{ name: 'Announcement' }">Back</router-link>
    </div>
  </div>  
  
</template>
<style scoped></style>
