import axios from 'axios'

const apiUrl = import.meta.env.DEV ? 'http://localhost:8080' : 'http://hackathon.twnsnd.online'

export default axios.create({
  baseURL: apiUrl,
  headers: {
    'Content-Type': 'application/json'
  }
})