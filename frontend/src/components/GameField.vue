<template>
  <div id="slider">
    <form>
      <label>Легко</label>
      <input type="range" id="scale" value="4" min="3" max="5" step="1">
      <label>Сложно</label>
    </form>
    <br>
  </div>
  <div id="main" class="main">
    <canvas id="puzzle" width="480px" height="480px"></canvas>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import {randomIntFromInterval} from "@/composables/utils.js";

onMounted(() => {
  var canvas = document.getElementById("puzzle")
  canvas.width  = 480;
  canvas.height = 480;
  var context = canvas.getContext("2d")
  var img = new Image();
  img.src = '/src/assets/img/happy-resized.jpg';
  img.addEventListener('load', drawTiles, false);

  var boardSize = document.getElementById('puzzle').width;
  var tileCount = document.getElementById('scale').value;
  var tileSize = boardSize / tileCount;
  var boardParts = new Object;
  var clickLoc = new Object;
  clickLoc.x = 0;
  clickLoc.y = 0;

  var emptyLoc = new Object;
  emptyLoc.x = 0;
  emptyLoc.y = 0;
  var solved = false;
  setBoard();

  function setBoard() {
    var allPositions = [];
    for (var i = 0; i < tileCount; ++i) {
      for (var j = 0; j < tileCount; ++j) {
        allPositions.push({ x: i, y: j });
      }
    }
    allPositions.sort(() => Math.random() - 0.5);
    boardParts = new Array(tileCount);
    for (var i = 0; i < tileCount; ++i) {
      boardParts[i] = new Array(tileCount);
    }
    for (var i = 0; i < tileCount; ++i) {
      for (var j = 0; j < tileCount; ++j) {
        var idx = i * tileCount + j;
        boardParts[i][j] = allPositions[idx];
      }
    }
    emptyLoc.x = boardParts[tileCount - 1][tileCount - 1].x;
    emptyLoc.y = boardParts[tileCount - 1][tileCount - 1].y;
    solved = false;
  }

  function generateEmptyLoc() {
    const rndInt = randomIntFromInterval(1, tileCount)
    emptyLoc.x = boardParts[tileCount - rndInt][tileCount - rndInt].x;
    emptyLoc.y = boardParts[tileCount - rndInt][tileCount - rndInt].y;
  }

  document.getElementById('scale').onchange = function() {
    tileCount = this.value;
    tileSize = boardSize / tileCount;
    setBoard();
    drawTiles();
  };

  document.getElementById('puzzle').onmousemove = function(e) {
    clickLoc.x = Math.floor((e.pageX - this.offsetLeft) / tileSize);
    clickLoc.y = Math.floor((e.pageY - this.offsetTop) / tileSize);
  };

  document.getElementById('puzzle').onclick = function() {
    if (distance(clickLoc.x, clickLoc.y, emptyLoc.x, emptyLoc.y) == 1) {
      slideTile(emptyLoc, clickLoc);
      drawTiles();
    }
    if (solved) {
      setTimeout(function() {alert("You solved it!");}, 500);
    }
  };

  function distance(x1, y1, x2, y2) {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
  }

  function slideTile(toLoc, fromLoc) {
    if (!solved) {
      boardParts[toLoc.x][toLoc.y].x = boardParts[fromLoc.x][fromLoc.y].x;
      boardParts[toLoc.x][toLoc.y].y = boardParts[fromLoc.x][fromLoc.y].y;
      boardParts[fromLoc.x][fromLoc.y].x = tileCount - 1;
      boardParts[fromLoc.x][fromLoc.y].y = tileCount - 1;
      toLoc.x = fromLoc.x;
      toLoc.y = fromLoc.y;
      checkSolved();
    }
  }

  function checkSolved() {
    var flag = true;
    for (var i = 0; i < tileCount; ++i) {
      for (var j = 0; j < tileCount; ++j) {
        if (boardParts[i][j].x != i || boardParts[i][j].y != j) {
          flag = false;
        }
      }
    }
    solved = flag;
  }

  function drawTiles() {
    context.clearRect ( 0 , 0 , boardSize , boardSize );
    for (var i = 0; i < tileCount; ++i) {
      for (var j = 0; j < tileCount; ++j) {
        var x = boardParts[i][j].x;
        var y = boardParts[i][j].y;

        // const point1 = {x: boardParts[i][j].x,  y: boardParts[i][j].y};
        // const point2 = {x: tileSize, y: 0};
        // const point3 = {x: tileSize / 2, y: tileSize / 2 };
        // drawTriangle(context, point1, point2, point3, true);

        if(i != emptyLoc.x || j != emptyLoc.y || solved == true) {
          context.drawImage(img, x * tileSize, y * tileSize, tileSize, tileSize,
              i * tileSize, j * tileSize, tileSize, tileSize);
        }
      }
    }
  }

  function drawTriangle(context, point1, point2, point3, filled) {
    context.beginPath();
    context.moveTo(point1.x, point1.y);
    context.lineTo(point2.x, point2.y);
    context.lineTo(point3.x, point3.y);
    context.closePath();
    filled ? context.fill() : context.stroke();
  }
})
</script>