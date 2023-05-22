const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
   specPattern: "cypress/sprint3/**/*.{cy,spec}.{js,jsx,ts,tsx}",
   baseUrl: "http://localhost:5173",
   experimentalSessionAndOrigin: true
  },
});
