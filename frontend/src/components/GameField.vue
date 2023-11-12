<template>
	<div id="slider">
		<form>
			<label>Легко</label>
			<input class="m-2" type="range" id="scale" value="4" min="3" max="5" step="1" />
			<label>Сложно</label>
		</form>
		<br />
	</div>
	<div id="main" class="main">
		<canvas id="puzzle" width="480px" height="480px"></canvas>
	</div>
	<button class="btn btn-primary mt-4" @click="solvePuzzle">Собрать картинку</button>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue'
import useEventsBus from '@/composables/eventBus.js'

const { bus } = useEventsBus()
const { emit } = useEventsBus()

const solvePuzzle = ref(null)
const stepsCount = ref(0)

const gameImg = ref(null)

watch(
	() => bus.value.get('restartGame'),
	() => {
		stepsCount.value = 0
		emit('setSteps', stepsCount.value)
		init()
	}
)

watch(
	() => bus.value.get('setNewGameImage'),
	(data) => {
		const [val] = data
		gameImg.value = val
		init()
	}
)

const init = () => {
	const canvas = document.getElementById('puzzle')
	canvas.width = 480
	canvas.height = 480

	const context = canvas.getContext('2d')
	const img = new Image()
	img.src = gameImg.value || 'https://i.postimg.cc/yNf4zSPB/happy-resized.jpg'
	img.addEventListener('load', drawTiles, false)

	const boardSize = document.getElementById('puzzle').width
	let tileCount = document.getElementById('scale').value
	let tileSize = boardSize / tileCount
	let boardParts = new Object()
	const clickLoc = new Object()
	clickLoc.x = 0
	clickLoc.y = 0

	const emptyLoc = new Object()
	emptyLoc.x = 0
	emptyLoc.y = 0
	let solved = false
	setBoard()

	function setBoard() {
		const allPositions = []
		for (let i = 0; i < tileCount; ++i) {
			for (let j = 0; j < tileCount; ++j) {
				allPositions.push({ x: i, y: j })
			}
		}
		allPositions.sort(() => Math.random() - 0.5)
		boardParts = new Array(tileCount)
		for (let i = 0; i < tileCount; ++i) {
			boardParts[i] = new Array(tileCount)
		}
		for (let i = 0; i < tileCount; ++i) {
			for (let j = 0; j < tileCount; ++j) {
				let idx = i * tileCount + j
				boardParts[i][j] = allPositions[idx]
			}
		}
		emptyLoc.x = boardParts[tileCount - 1][tileCount - 1].x
		emptyLoc.y = boardParts[tileCount - 1][tileCount - 1].y
		solved = false
	}

	document.getElementById('scale').onchange = function () {
		tileCount = this.value
		tileSize = boardSize / tileCount
		setBoard()
		drawTiles()
	}

	document.getElementById('puzzle').onmousemove = function (e) {
		clickLoc.x = Math.floor((e.pageX - this.offsetLeft) / tileSize)
		clickLoc.y = Math.floor((e.pageY - this.offsetTop) / tileSize)
	}

	document.getElementById('puzzle').onclick = function () {
		if (distance(clickLoc.x, clickLoc.y, emptyLoc.x, emptyLoc.y) == 1) {
			slideTile(emptyLoc, clickLoc)
			drawTiles()
			emit('setSteps', ++stepsCount.value)
		}
	}

	function distance(x1, y1, x2, y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2)
	}

	function slideTile(toLoc, fromLoc) {
		boardParts[toLoc.x][toLoc.y].x = boardParts[fromLoc.x][fromLoc.y].x
		boardParts[toLoc.x][toLoc.y].y = boardParts[fromLoc.x][fromLoc.y].y
		boardParts[fromLoc.x][fromLoc.y].x = tileCount - 1
		boardParts[fromLoc.x][fromLoc.y].y = tileCount - 1
		toLoc.x = fromLoc.x
		toLoc.y = fromLoc.y
		checkSolved()
	}

	function checkSolved() {
		let flag = true
		for (let i = 0; i < tileCount; ++i) {
			for (let j = 0; j < tileCount; ++j) {
				if (boardParts[i][j].x != i || boardParts[i][j].y != j) {
					flag = false
				}
			}
		}
		solved = flag

		if (solved) {
			emit('puzzle-solved')
		}
	}

	function drawMoveOptions() {
		if (emptyLoc.y > 0) {
			let x = emptyLoc.x * tileSize + tileSize / 2
			let y = (emptyLoc.y - 1) * tileSize + tileSize / 2
			drawArrow(x, y, 'down')
		}

		if (emptyLoc.y < tileCount - 1) {
			let x = emptyLoc.x * tileSize + tileSize / 2
			let y = (emptyLoc.y + 1) * tileSize + tileSize / 2
			drawArrow(x, y, 'up')
		}

		if (emptyLoc.x > 0) {
			let x = (emptyLoc.x - 1) * tileSize + tileSize / 2
			let y = emptyLoc.y * tileSize + tileSize / 2
			drawArrow(x, y, 'right')
		}

		if (emptyLoc.x < tileCount - 1) {
			let x = (emptyLoc.x + 1) * tileSize + tileSize / 2
			let y = emptyLoc.y * tileSize + tileSize / 2
			drawArrow(x, y, 'left')
		}
	}

	function drawArrow(x, y, direction) {
		context.beginPath()
		context.moveTo(x, y)
		switch (direction) {
			case 'up':
				context.lineTo(x, y - 10)
				context.lineTo(x - 5, y - 5)
				context.moveTo(x, y - 10)
				context.lineTo(x + 5, y - 5)
				break
			case 'down':
				context.lineTo(x, y + 10)
				context.lineTo(x - 5, y + 5)
				context.moveTo(x, y + 10)
				context.lineTo(x + 5, y + 5)
				break
			case 'left':
				context.lineTo(x - 10, y)
				context.lineTo(x - 5, y - 5)
				context.moveTo(x - 10, y)
				context.lineTo(x - 5, y + 5)
				break
			case 'right':
				context.lineTo(x + 10, y)
				context.lineTo(x + 5, y - 5)
				context.moveTo(x + 10, y)
				context.lineTo(x + 5, y + 5)
				break
		}
		context.strokeStyle = 'white'
		context.lineWidth = 3
		context.strokeRect(x - 7, y - 7, 14, 14)
		context.strokeStyle = 'blue'
		context.stroke()
	}

	function drawTiles() {
		context.clearRect(0, 0, boardSize, boardSize)
		for (let i = 0; i < tileCount; ++i) {
			for (let j = 0; j < tileCount; ++j) {
				let x = boardParts[i][j].x
				let y = boardParts[i][j].y

				if (i != emptyLoc.x || j != emptyLoc.y || solved == true) {
					context.drawImage(img, x * tileSize, y * tileSize, tileSize, tileSize, i * tileSize, j * tileSize, tileSize, tileSize)
				}
			}
		}
		drawMoveOptions()
	}

	solvePuzzle.value = () => {
		// Устанавливаем правильное местоположение всех плиток
		for (let i = 0; i < tileCount; ++i) {
			for (let j = 0; j < tileCount; ++j) {
				boardParts[i][j].x = i
				boardParts[i][j].y = j
			}
		}

		// Устанавливаем пустую плитку в правый нижний угол
		emptyLoc.x = tileCount - 1
		emptyLoc.y = tileCount - 1

		drawTiles()
		checkSolved()
	}
}

onMounted(() => {
	init()
})
</script>
