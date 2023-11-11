<template>
	<Header/>
<!--	<PopupStart/>-->
		<section class="d-flex align-items-center flex-column">
			<h1>Теплые пятнашки</h1>
	    <GameField/>
		</section>
	<PopupWin/>
</template>

<script setup>
import {useCounterStore} from "@/stores/counter"
import PopupStart from "@/components/PopupStart.vue";
import Header from "@/components/Header.vue";
import PopupWin from "@/components/PopupWin.vue";



import GameField from "@/components/GameField.vue"

const store = useCounterStore()
</script>

<style scoped>
</style>

