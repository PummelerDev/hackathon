<template>
	<select class="form-select w-25 my-4" role="button" v-model="selectedImg" @change="chooseImage(selectedImg)">
		<option selected disabled value="">Выбрать другую картинку</option>
		<option v-for="img in imgList" :value="img.id">{{ img.name }}</option>
	</select>
</template>

<script setup>
import api from '@/composables/api'
import { ref } from 'vue'
import useEventsBus from '@/composables/eventBus.js'
import { imgList } from '@/composables/data.js'

// const imgList = ref(null)
const gameImg = ref(null)
const selectedImg = ref('')

const { emit } = useEventsBus()

const chooseImage = (id) => {
	const imgSrc = imgList.find((el) => el.id === id).src
	emit('setNewGameImage', imgSrc)
}

// const getImages = async () => {
// 	try {
// 		const response = await api.get('/api/v1/images')
// 		imgList.value = response.data
// 	} catch (error) {
// 		console.error(error)
// 	}
// }

// getImages()

// const chooseImage = async (id) => {
// 	try {
// 		const response = await api.get(`/api/v1/images/bytes/${id}`, { responseType: 'arraybuffer' })
// 		const blob = new Blob([response.data], { type: 'image/jpeg' })
// 		const url = URL.createObjectURL(blob)
// 		gameImg.value = url
// 		emit('setNewGameImage', url)
// 	} catch (error) {
// 		console.error(error)
// 	}
// }
</script>
