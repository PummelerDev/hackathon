<script setup>
import { ref } from 'vue'

const showModal = ref(true)

const closeModal = () => {
	showModal.value = false
}
</script>

<template>
	<transition name="modal-fade">
		<div v-if="showModal" class="modal-wrapper">
			<div
				class="modal fade show"
				id="staticBackdrop"
				data-bs-backdrop="static"
				data-bs-keyboard="false"
				tabindex="-1"
				aria-labelledby="staticBackdropLabel"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id="staticBackdropLabel">Игра пятнашки</h1>
						</div>
						<div class="modal-body">
							<ul class="list-group">
								<li class="list-group-item">1. Цель игры: собрать картинку из разных кусочков, перемещая их на правильные места.</li>
								<li class="list-group-item">2. В начале игры кусочки картинки перемешиваются и расставляются на поле в случайном порядке.</li>
								<li class="list-group-item">3. Пользуйся пустой ячейкой и двигай кусочки влево, вправо, вверх или вниз, чтобы они встали на свое место.</li>
								<li class="list-group-item">4. Игра закончится, когда все кусочки будут на своих местах, и картинка соберется полностью.</li>
							</ul>
						</div>
						<div class="modal-footer">
							<!--							<button type="button" class="btn btn-warning">Зарегистрироваться/Авторизоваться</button>-->
							<button type="button" class="btn btn-primary" @click="closeModal">Начать игру</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</transition>
</template>

<style scoped>
.modal {
	display: block;
}

.modal-fade-enter-active,
.modal-fade-leave-active {
	transition: opacity 0.5s;
}

.modal-fade-enter,
.modal-fade-leave-to {
	opacity: 0;
}

.modal-wrapper {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>
