<template>
	<section>
		<h1>Пятнашки</h1>
    <GameField/>
	</section>
</template>

<script setup>
import {useCounterStore} from "@/stores/counter"
import GameField from "@/components/GameField.vue"

const store = useCounterStore()
</script>

