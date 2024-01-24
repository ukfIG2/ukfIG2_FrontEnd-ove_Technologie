// store/userStore.ts
import { defineStore } from 'pinia';

export const useUserStore = defineStore({
  id: 'user',
  state: () => ({
    data: null,
  }),
  actions: {
    setUser(userData) {
      this.data = userData;
    },
  },
});