<script setup>

</script>

<template>
  <main>
    <router-view />
  </main>
</template>

<<<<<<< HEAD

=======
<script setup>
import api from '@/composables/api'

const fetchUsers =  async () => {
  try {
    const response = await api.get('/api/v1/users')
    console.log('response', response)
  } catch(error) {
    console.error(error)
  }
}

// fetchUsers()

</script>
>>>>>>> 12392b8673ee7a8e2e2e824365e6c9e38d45a8fb

<style scoped>

</style>
