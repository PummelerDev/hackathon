<template>
<div>Hello!</div>
</template>

<script setup>
import HelloWorld from './components/HelloWorld.vue'
import api from './composables/api'

const fetchUsers =  async () => {
  try {
    const response = await api.get('/api/v1/users')
    console.log('response', response)
  } catch(error) {
    console.error(error)
  }
}

fetchUsers()

</script>

<style scoped>

</style>
