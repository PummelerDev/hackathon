<script setup>
import useEventsBus from "@/composables/eventBus.js";
import {ref, watch} from "vue";

const {emit, bus} = useEventsBus()
const restart = () => {
  emit('restartGame')
}

const stepsCount = ref(0)
watch(() => bus.value.get('setSteps'), (data) => {
  const [val] = data
  stepsCount.value = val
})
</script>

<template>
	<header>
		<nav class="navbar navbar-light bg-light">
			<div class="container d-flex justify-content-between">
				<button class="btn btn-warning" id="start-game" @click="restart">Начать заново</button>
				<div class="d-flex right-buttons">
					<h3 class="me-2"><span class="badge bg-secondary" id="score">Шагов: {{stepsCount}}</span></h3>
					<h3><span class="badge bg-secondary" id="record">Рекорд: 0</span></h3>
				</div>
			</div>
		</nav>
	</header>
</template>

<style scoped>

</style>