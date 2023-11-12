<template>
	<Header/>
		<PopupStart/>
		<section class="d-flex align-items-center flex-column mb-4">
			<h1 class="p-2">Пятнашки "Счастливая семья"</h1>
      <ChooseImage/>
	    <GameField/>
		</section>
	<PopupWin  v-if="isPuzzleSolved"/>
</template>

<script setup>
import {useCounterStore} from "@/stores/counter"
import PopupStart from "@/components/PopupStart.vue";
import Header from "@/components/Header.vue";
import PopupWin from "@/components/PopupWin.vue";
import {ref, watch} from 'vue';
import GameField from "@/components/GameField.vue"
import ChooseImage from "@/components/ChooseImage.vue";
import useEventsBus from "@/composables/eventBus.js";

const isPuzzleSolved = ref(false);
const { bus } = useEventsBus()

watch(() => bus.value.get("puzzle-solved"),
		() => {isPuzzleSolved.value = true;}
);

const store = useCounterStore()
</script>

<style scoped>
</style>

