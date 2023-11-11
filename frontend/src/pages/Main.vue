<template>
	<Header/>
	<PopupStart/>
</template>

<script setup>
import {useCounterStore} from "@/stores/counter"
import PopupStart from "@/components/PopupStart.vue";
import Header from "@/components/Header.vue";
const store = useCounterStore()
</script>

<style scoped>
</style>

