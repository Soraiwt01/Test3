import { defineStore, acceptHMRUpdate } from 'pinia'
import { ref } from 'vue';

const modes = { ACTIVE: 'active', CLOSE: 'close', ADMIN: 'admin'}

export const useAnnounces = defineStore("announcer",() => {
   const mode = ref(modes.ACTIVE)
   const category = ref(0)
   const page = ref(1)
   const changeMode = () =>{
        if (mode.value === modes.ACTIVE) mode.value = modes.CLOSE
        else mode.value = modes.ACTIVE
   }
   const getMode = () => mode.value
   const getPage = () => page.value
   const addPage = () => page.value++
   const substractPage = () => page.value--
   const setPage = (newPage) => page.value = newPage

    return { changeMode,getMode,category
        ,getPage,addPage,substractPage,setPage }
})

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useAnnounces,import.meta.hot))
}
export { modes }