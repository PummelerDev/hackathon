import axios from 'axios'

const apiUrl = 'http://hackathon.twnsnd.online'

export default axios.create({
	baseURL: apiUrl,
	headers: {
		'Content-Type': 'application/json'
	}
})
