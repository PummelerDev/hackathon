<script setup>
import useEventsBus from "@/composables/eventBus.js";

const {emit} = useEventsBus()
const restart = () => {
  emit('restartGame')
}
</script>

<template>
	<header>
		<nav class="navbar navbar-light bg-light">
			<div class="container d-flex justify-content-between">
				<button class="btn btn-warning" id="start-game" @click="restart">Начать заново</button>
				<div class="d-flex right-buttons">
					<h3 class="me-2"><span class="badge bg-secondary" id="score">Шагов: 0</span></h3>
					<h3><span class="badge bg-secondary" id="record">Рекорд: 0</span></h3>
				</div>
			</div>
		</nav>
	</header>
</template>

<style scoped>

</style>