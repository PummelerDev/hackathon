import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useGameDataStore = defineStore('gameData', () => {
	const stepsCount = ref(0)

	return { stepsCount }
})
