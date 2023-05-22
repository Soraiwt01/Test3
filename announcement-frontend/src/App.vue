<script  setup>

</script>

<template>
  <div class="flex flex-col items-center font bg-base-200 bg-cover h-screen w-screen " style="background-image: url(https://media.discordapp.net/attachments/518037078731128832/1105737461621063733/bgF2.jpg?width=1920&height=960)" >
    <router-view></router-view>
  </div>
</template>

<style>

@font-face {
  font-family: "Kanit-Regular";
  src: url(./assets/font/Kanit-Regular.ttf);
}
@font-face {
  font-family: "Kanit-Light";
  src: url(./assets/font/Kanit-Light.ttf);
}
.font {
  font-family: "Kanit-Regular";
  letter-spacing: 0.05rem;
}
.kanit-light {
  font-family: "Kanit-Light";
  letter-spacing: 0.02rem;  
}

</style>
