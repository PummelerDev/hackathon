<template>
	<Header/>
	<PopupStart/>
	<section>
		<h1>Пятнашки</h1>
    <GameField/>
	</section>
</template>

<script setup>
import {useCounterStore} from "@/stores/counter"
import PopupStart from "@/components/PopupStart.vue";
import Header from "@/components/Header.vue";

import GameField from "@/components/GameField.vue"

const store = useCounterStore()
</script>

<style scoped>
</style>

